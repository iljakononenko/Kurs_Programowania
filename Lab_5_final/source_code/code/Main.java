package com.iljaknk;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Klasa Main w ktorej sie znajduje metoda main uruchamiajaca program
 * @see Main#main(String[])
 */

public class Main {

    static my_Frame main_frame;

    /**
     * Glowna metoda main, uruchamiajaca caly program
     * @param args - parametry podane do programu
     * Oczekujemy nastepujece parametry:
     * -m *liczba wierszy* (oczekujemy wartosc od 0.0)
     * -n *liczba kolumn* (oczekujemy wartosc od 0.0)
     * -k *wspolczynnik czasu oczekiwania zmiany koloru* (oczekujemy wartosc od 0.1)
     * -p *prawdopodobienstwo zmiany koloru* (oczekujemy wartosc od 0.0 do 1.0)
     */

    public static void main(String[] args) {

        int m_rows = 0, n_columns = 0, k_szybkosc_dzialania = 0;
        double p_prawdopodobienstwo = 0.0;

        if (args.length < 2)
        {
            System.out.println("Bad parameters.. Sorry..");
            System.exit(-1);
        }

        /**
         * Odczyt wartości parametrów
         */

        for(int i = 0; i < args.length; i++)
        {
            if(args[i].length() ==2 && args[i].charAt(0) == '-')
            {
                char param = args[i].charAt(1);

                switch (param)
                {
                    case 'm' -> m_rows = Integer.parseInt(args[i+1]);
                    case 'n' -> n_columns = Integer.parseInt(args[i+1]);
                    case 'k' -> k_szybkosc_dzialania = Integer.parseInt(args[i+1]);
                    case 'p' -> p_prawdopodobienstwo = Double.parseDouble(args[i+1]);
                }

            }
        }

        /**
         * Sprawdzenie, czy te wartości odpowiadają wymaganiam
         */

        if ( m_rows <= 0 || n_columns <= 0 || k_szybkosc_dzialania <= 0 || p_prawdopodobienstwo < 0 || p_prawdopodobienstwo > 1)
        {
            System.out.println("Bad parameters.. Sorry..");
            System.exit(-1);
        }

        /**
         * Tworzenie glownego okna i uruchomienie glownej czesci programu
         * @see my_Frame
         * Pozniej uruchamiamy wszystkie watki
         * @see my_Frame#run_panels()
         */

        my_Frame frame = new my_Frame(m_rows,n_columns, k_szybkosc_dzialania, p_prawdopodobienstwo);

        main_frame = frame;

        main_frame.addMouseListener(new Thread_stopper());

        main_frame.setVisible(true);

        main_frame.run_panels();


    }

    /**
     * Klasa do kontroli naciskania mysza
     * Jest stworzona w Main poniewaz potrzebujemy
     * znalezc aktualny rozmiar okna
     */

    static class Thread_stopper extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            Dimension windows_size = main_frame.getSize();
            main_frame.mouseClicked(e, windows_size.width, windows_size.height);
        }
    }
}
