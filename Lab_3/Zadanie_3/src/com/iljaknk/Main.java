package com.iljaknk;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {


        Frame okno = new Frame("Wiersz trojkata Pascala");

        Button button = new Button("Pokaz wiersz");
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

            if (n <= 0)
            {
                wynik.setText(n + " - Zly rozmiar tablicy!");
                dane.setText("");
            }

            else
            {
                try
                {

                    System.out.println("Creating Process...");
                    Process p = Runtime.getRuntime().exec("E://Programming//Kurs_Programowania//Lab_3//Zadanie_3//src//com//iljaknk//Program.exe " + n);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null)
                    {
                        wynik.setText(line);
                    }

                    reader.close();

                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
        catch (NumberFormatException ex)
        {
            wynik.setText(input + " - nie jest liczba calkowita! Nie mozemy stworzyc tablicy!");
            dane.setText("");
        }

    }
}



