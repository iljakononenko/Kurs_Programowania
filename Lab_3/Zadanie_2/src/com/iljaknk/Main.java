package com.iljaknk;

import java.awt.*;
import java.awt.event.*;

public class Main {

    public static void main(String[] args) {

        Frame okno = new Frame("Trojkat Pascala");

        Button button = new Button("Pokaz trojkat");
        TextArea wynik = new TextArea();
        TextField dane = new TextField(40);

        Panel info_panel = new Panel();
        info_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        info_panel.add(dane);
        info_panel.add(button);

        button.addActionListener(e -> onClick_event(dane, wynik));

        okno.setLayout(new BorderLayout());
        okno.setBounds(100,100,1024,768);
        okno.add(info_panel, BorderLayout.NORTH);
        okno.add(wynik, BorderLayout.CENTER);

        okno.addWindowListener(new My_Window_Adapter());

        okno.setVisible(true);

    }

    public static void onClick_event (TextField dane, TextArea wynik)
    {
        String input = dane.getText();
        try
        {
            int n = Integer.parseInt(input);
            dane.setText("");
            wynik.setText("");

            try
            {
                Wiersz_Trojkata_Pascala trojkat = new Wiersz_Trojkata_Pascala(1);
                String string = trojkat.print_caly_Trojkat(n);
                wynik.setText(string);

                    /*
                    for (int i = 0; i <= n; i++)
                    {
                        Wiersz_Trojkata_Pascala wiersz = new Wiersz_Trojkata_Pascala(i);
                        String string = wiersz.print_caly_wiersz();
                        string = "\n" + string;
                        wynik.setText(wynik.getText() + string);
                    }
                    */

            }
            catch (Wrong_array_size error)
            {
                //System.out.println(n + " - Zly rozmiar tablicy!");
                wynik.setText(n + " - Zly rozmiar tablicy!");
                dane.setText("");
            }

        }
        catch (NumberFormatException ex)
        {
            //System.out.println(args[0] + " - nie jest liczba calkowita! Nie mozemy stworzyc tablicy!");
            wynik.setText(input + " - nie jest liczba calkowita! Nie mozemy stworzyc tablicy!");
            dane.setText("");
        }

    }
}



