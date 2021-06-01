package com.iljaknk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Adapter do zamkniecia okna
 */

class WindowAdapter extends java.awt.event.WindowAdapter
{
    public void windowClosing(WindowEvent e) { System.exit(0);}
}

/**
 * klasa ktora tworzy caly interfejs programu
 */

public class my_Frame extends JFrame
{

    /**
     * Pola do obslugi zmiany koloru paneli
     */

    Random random;
    float r,g,b;
    int rows, columns;
    Color random_color;
    ArrayList<Thread> threads_panel = new ArrayList<>();
    my_JPanel[][] panels;

    /**
     * konstruktor glownej klasy UI
     */

    public my_Frame(int rows, int columns, int k_szybkosc_dzialania, double p_prawdopodobienstwo)
    {
        this.rows = rows;
        this.columns = columns;

        random = new Random();

        setTitle("Random tiles switching colors");
        setSize(762, 568);

        addWindowListener(new WindowAdapter());

        panels = new my_JPanel[rows][columns];

        GridLayout gridLayout = new GridLayout(rows, columns);

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                /**
                 * Dla kazdego wiersza tworzymy odpowiedna liczbe kolumn
                 * I wpisujemy panele, ktore implementuja interface Runnable
                 * @see Runnable
                 * @see my_JPanel
                 * @see my_JPanel#run()
                 * Dodajemy je do Arraylist Watkow, zeby pozniej je uruchomic metoda 'run_panels()'
                 * @see my_Frame#run_panels()
                 */

                r = random.nextFloat();
                g = random.nextFloat();
                b = random.nextFloat();
                random_color = new Color(r,g,b);

                my_JPanel new_panel = new my_JPanel(random_color, this, k_szybkosc_dzialania, p_prawdopodobienstwo);

                Thread new_thread = new Thread(new_panel);
                threads_panel.add(new_thread);

                new_panel.set_Panel_thread(new_thread);

                panels[i][j] = new_panel;

                add(new_panel);
            }
        }

        set_neighbours(rows, columns);

        setLayout(gridLayout);
    }

    /**
     * Metoda uruchamiajaca wszystkie watki
     * Jest wywolana w metodzie main klasy Main
     */

    public void run_panels ()
    {
        for (Thread panel : threads_panel)
        {
            panel.start();
        }
    }

    /**
     * Metoda ustalajaca dla kazdego panelu sasiadow
     * @param rows - liczba wierszy
     * @param columns - liczba kolumn
     * Ustalamy dla panelu przez metode 'set_Neighbours(my_JPanel[] new_neighbours)'
     * @see my_JPanel#set_Neighbours(my_JPanel[]) 
     */
    
    public void set_neighbours (int rows, int columns)
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                my_JPanel[] new_neighbours = new my_JPanel[4];

                // neighbour panels:
                // above
                if (i > 0)
                {
                    new_neighbours[0] = panels[i-1][j];
                }
                else
                {
                    new_neighbours[0] = null;
                }
                // left
                if (j > 0)
                {
                    new_neighbours[1] = panels[i][j-1];
                }
                else
                {
                    new_neighbours[1] = null;
                }
                // under
                if (i+1 < rows)
                {
                    new_neighbours[2] = panels[i+1][j];
                }
                else
                {
                    new_neighbours[2] = null;
                }
                // right
                if (j+1 < columns)
                {
                    new_neighbours[3] = panels[i][j+1];
                }
                else
                {
                    new_neighbours[3] = null;
                }


                panels[i][j].set_Neighbours(new_neighbours);
            }
        }
    }

    /**
     * Metoda do generacji nowego koloru, wywolana przez kazdy panel
     * @see my_JPanel
     * @see my_JPanel#run()
     * @return
     */

    public double[] get_values ()
    {
        double[] values = new double[5];

        for (int i = 0; i < 5; i++)
        {
            values[i] = random.nextDouble();
        }

        return values;
    }

    /**
     * Metoda do hamowania i znawiania watku po kliknieciu myszy
     * @param e - obiekt majacy informacje o kliknieciu myszy
     * @param width - aktualna szerokosc okna
     * @param height - aktualna wysokosc okna
     */

    public synchronized void mouseClicked(MouseEvent e, float width, float height)
    {

        //System.out.println(width + " " + height);

        float panel_width = width / columns;
        float panel_height = height / rows;

        //System.out.println(panel_width + " " + panel_height);

        float x = e.getX();
        float y = e.getY();

        //System.out.println(x + " " + y);

        int clicked_panel_row = rows, clicked_panel_column = columns;

        /**
         * Szukamy jaki dokladnie panel byl naciszniety
         */

        for (int i = 0; i < columns; i++)
        {
            if (i * panel_width > x)
            {
                //System.out.println("Clicked on " + x + " " + y);
                //System.out.println(i*panel_width + " " + i*panel_height);
                clicked_panel_column = i;
                break;
            }
        }

        for (int i = 0; i < rows; i++)
        {
            if (i * panel_height > y)
            {
                //System.out.println("Clicked on " + x + " " + y);
                //System.out.println(i*panel_width + " " + i*panel_height);
                clicked_panel_row = i;
                break;
            }
        }

        System.out.println(clicked_panel_row + " row and " + clicked_panel_column + " column was clicked!");

        my_JPanel clicked_panel = panels[clicked_panel_row-1][clicked_panel_column-1];

        clicked_panel.paused = !clicked_panel.paused;

        if (!clicked_panel.paused)
        {
            clicked_panel.resume();
        }
    }
}
