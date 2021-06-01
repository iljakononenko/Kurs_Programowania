package com.iljaknk;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa do obslugi prawego przycisku myszy
 * @see JPopupMenu
 */

public class my_PopUp extends JPopupMenu implements ActionListener
{
    /**
     * tworzymy kolory do wyboru oraz dodajemy do menu
     * ktore sie wyswietla gdy wybralismy figure, oraz przyciskamy
     * prawym przyciskiem myszy
     * @see Surface
     * @see Surface#change_selected_figure_color(String) - metoda do zmiany koloru wybranej figury
     * (przekazuje String wybranego przycisku)
     */
    JMenuItem[] colors = new JMenuItem[6];
    Surface parent;

    /**
     * Konstruktor klasy my_PopUp
     * @see my_PopUp
     * @see JPopupMenu
     * @param surface - parametr, ktory mamy do przekazania do niego wybranego koloru
     */

    public my_PopUp (Surface surface)
    {
        parent = surface;

        colors[0] = new JMenuItem("Red");
        colors[1] = new JMenuItem("Blue");
        colors[2] = new JMenuItem("Black");
        colors[3] = new JMenuItem("White");
        colors[4] = new JMenuItem("Green");
        colors[5] = new JMenuItem("Yellow");

        for (JMenuItem color : colors)
        {
            add(color);
            color.addActionListener(this);
        }

    }

    /**
     * Listener ktory uruchamia metode 'change_selected_figure_color' obiektu surface
     * @see Surface
     * @see Surface#change_selected_figure_color(String)
     * @param e - wybrany button menu
     */

    @Override
    public void actionPerformed(ActionEvent e)
    {
        parent.change_selected_figure_color(e.getActionCommand());
    }
}
