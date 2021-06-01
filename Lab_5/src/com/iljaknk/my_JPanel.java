package com.iljaknk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Klasa ktora rozszerza klase JPanel, dla osiagniecia wlasnych cel
 * Klasa implementuje Runnable, by mogla byc uruchomiona w innym threadzie
 * @see Runnable
 * @see JPanel
 * @see my_Frame#run_panels()
 */

public class my_JPanel extends JPanel implements Runnable
{

    /**
     * Pola do obslugi zmiany koloru
     */

    my_Frame parent_frame;
    int k_szybkosc_dzialania;
    double p_prawdobodobienstwo;
    float r,g,b;
    Color panel_color;
    my_JPanel[] neighbours;
    boolean paused = false;
    Thread panel_thread;
    private final Object pauseLock = new Object();

    /**
     * Konstruktor panelu
     * @param given_color - poczatkowy kolor wygenerowany przy utworzeniu
     * @param given_parent_frame - glowne okno (glowny watek) jest potrzebny do wywolania metody 'get_values()' dla pobrania nowego koloru
     * @param given_k - podany i zapisany wspolczynnik czasu do oczekiwania przed zmiana koloru
     * @param given_p - podane i zapisane prawdopodobienstwo zmiany koloru
     */

    public my_JPanel(Color given_color, my_Frame given_parent_frame, int given_k, double given_p)
    {
        panel_color = given_color;
        setBackground(given_color);
        parent_frame = given_parent_frame;
        k_szybkosc_dzialania = given_k;
        p_prawdobodobienstwo = given_p;

    }

    /**
     * Glowna metoda uruchamiajaca zmiane koloru
     * @see Runnable
     * @see my_Frame
     * @see my_Frame#get_values() - metoda do pobrania array liczb double do wygenerowania nowego losowego koloru oraz wyniku prawdopodobienstwa
     * Pierwsza liczbe uzywamy do decydowania czasu zatrzymania watku
     * Nastepne 3 liczby uzywamy do generacji nowego koloru
     * Ostatnia liczbe uzywamy do decydowania czy kolor sie zmieni
     *
     */

    @Override
    public void run()
    {
        while (true)
        {
            /*
                czesc odpowiadajaca za chamowanie watku
             */
            synchronized (pauseLock)
            {
                if (paused)
                {
                    try
                    {
                        synchronized (pauseLock)
                        {
                            pauseLock.wait();
                        }
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            try
            {

                double[] values = parent_frame.get_values();

            /*
                losowo wybrana liczba milisekund pomnozona przez k
                values[0] - to liczba od 0 do 1
                jak dodajemy ja do 0,5 wychodzi nam
                losowa liczba milisekund w przedziale [0.5 , 1.5]
             */

                long sleep_time = (long) ((0.5 + values[0]) * k_szybkosc_dzialania);

                Thread.sleep(sleep_time);

                r = (float) values[1];
                g = (float) values[2];
                b = (float) values[3];

                Color new_color = new Color(r, g, b);

                if (values[4] <= p_prawdobodobienstwo)
                {
                    panel_color = new_color;
                    setBackground(new_color);
                } else
                {
                    set_average_neighbours_color();
                }
            }
            catch (InterruptedException e)
            {

            }
        }



    }

    /**
     * Metoda do pobrania kolorow od sasiadow
     * Oraz obliczenia sredniej wartosci koloru
     * @see my_JPanel#neighbours
     * @see my_Frame#set_neighbours(int, int)
     */

    private void set_average_neighbours_color ()
    {
        float suma_r = 0;
        float suma_g = 0;
        float suma_b = 0;
        float suma_alpha = 0;
        int num_of_neighbours = 0;

        for (int i = 0; i < 4; i++)
        {
            if (neighbours[i] != null && !neighbours[i].paused)
            {
                num_of_neighbours++;
                suma_r += neighbours[i].panel_color.getRed();
                suma_g += neighbours[i].panel_color.getGreen();
                suma_b += neighbours[i].panel_color.getBlue();
                suma_alpha += neighbours[i].panel_color.getAlpha();
            }
        }

        if (num_of_neighbours > 0)
        {
            int r = (int) suma_r / num_of_neighbours;
            int g = (int) suma_g / num_of_neighbours;
            int b = (int) suma_b / num_of_neighbours;
            int a = (int) suma_alpha / num_of_neighbours;

            panel_color = new Color(r, g, b, a);
            setBackground(panel_color);
            //System.out.println("Changed color to neighbours!" + panel_color.toString());

        }

    }

    /**
     * Metoda panelu do ustalania sasiadow
     * Podstawiamy pole (tablice) neighbours podana tablica od metody 'set_neighbours()'
     * Znajdujacej sie w my_Frame
     * @param new_neighbours - tablica sasiadow wokol panelu
     * @see my_Frame#set_neighbours(int, int)
     */

    public void set_Neighbours (my_JPanel[] new_neighbours)
    {
        neighbours = new_neighbours;
    }

    /**
     * Metoda ustalajaca watek dla danego panelu,
     * zeby w przyszlosci hamowac oraz wznawiac dzialanie watku
     * @param given_thread
     */

    public void set_Panel_thread (Thread given_thread)
    {
        panel_thread = given_thread;
    }

    /**
     * Metoda do uruchomienia watku w normalnym trybie po
     * jego hamowaniu
     */

    public void resume ()
    {
        synchronized (pauseLock)
        {
            pauseLock.notifyAll();
        }
    }


}


