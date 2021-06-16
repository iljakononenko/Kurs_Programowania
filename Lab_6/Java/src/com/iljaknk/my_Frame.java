package com.iljaknk;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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

public class my_Frame<T extends Comparable<T>> extends JFrame implements ActionListener
{
    /**
     * Pola klasy do globalnego uzycia
     * Stworzenie czesci UI w konstruktorze
     */
    TextField insert_input, delete_input, search_input;
    JComboBox<String> tree_types_list;

    JTextPane bst_draw;

    Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    public my_Frame()
    {

        setTitle("BST tree");
        setSize(762, 568);

        /**
         * Stworzenie i dodanie lewej czesci UI
         * z przyciskami i polami tekstowymi do wprowadzania danych
         */

        Panel actions_panel = new Panel();
        create_actions(actions_panel);

        /**
         * Stworzenie panelu do wyswietlenia odpowiedzi serwera
         * oraz stylizacja tego panelu
         */

        bst_draw = new JTextPane();

        StyledDocument doc = bst_draw.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        bst_draw.setText("Test text");

        bst_draw.setEditable(false);

        /**
         * Dodanie wszystkich elementow do glownego layout
         */

        BorderLayout main_layout = new BorderLayout();
        setLayout(main_layout);

        add(bst_draw, BorderLayout.CENTER);
        add(actions_panel, BorderLayout.WEST);

        setVisible(true);
        addWindowListener(new WindowAdapter());
        listen_Socket();
    }

    /**
     * Metoda do podlaczenia sie do serwera
     * port: 2476
     * host: localhost
     */

    public void listen_Socket()
    {
        try
        {
            socket = new Socket("localhost", 2476);

            out = new PrintWriter(socket.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (UnknownHostException e)
        {
            System.out.println("Unknown host: localhost");
            System.exit(1);
        }
        catch (IOException e)
        {
            System.out.println("No I/O");
            System.exit(1);
        }
    }

    /**
     * Metoda do obslugi przycisku myszy na przyciski
     * @param e
     */

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String given_string = "";
        String given_command = "";
        String data_to_send = "";
        boolean send_to_server = false;

        /**
         * Znajdujemy nacisniety przycisk oraz zapisujemy request do serwera
         */

        try
        {
            switch (e.getActionCommand())
            {
                case "Insert" ->
                        {
                            given_command = "Insert";
                            given_string = insert_input.getText();

                            send_to_server = true;
                        }
                case "Delete" ->
                        {
                            given_command = "Delete";
                            given_string = delete_input.getText();

                            send_to_server = true;
                        }
                case "Search" ->
                        {
                            given_command = "Search";
                            given_string = search_input.getText();

                            send_to_server = true;
                        }
                case "Draw" ->
                        {
                            given_command = "Draw";
                            given_string = "0";

                            send_to_server = true;
                        }
                case "Select type" ->
                        {
                            given_command = "Select type";
                            given_string = tree_types_list.getSelectedItem().toString();

                            bst_draw.setText("New type of a tree selected!");
                            send_to_server = true;
                        }
            }
        }
        catch (NullPointerException error)
        {
            bst_draw.setText("Select tree type!");
        }

        /**
         * Probujemy wyslac do serwera request String
         * oraz czekamy na odpowiedz by wydrokowac
         * otrzymana odpowiedz
         */

        if (send_to_server)
        {
            data_to_send = given_command + ";" + given_string;

            // wysylamy komande oraz dane do serwera odzielone
            // srednikiem

            out.println(data_to_send);

            // odbieramy odpowiedz

            try
            {
                bst_draw.setText("");

                String response = in.readLine();

                response = response.replaceAll("/", "\n");

                bst_draw.setText(response);
            }
            catch (IOException error)
            {
                System.out.println("Read failed");
                System.exit(1);
            }

            given_string = "";
            given_command = "";
            data_to_send = "";
            send_to_server = false;
        }

    }

    /**
     * Tworzenie wszystkich przyciskow i pol tekstowych na lewej czesci UI
     * @param given_panel - Caly lewy panel
     */

    public void create_actions (Panel given_panel)
    {
        GridLayout actions_layout = new GridLayout(5,2);
        given_panel.setLayout(actions_layout);

        Button insert = new Button("Insert");
        Button delete = new Button("Delete");
        Button search = new Button("Search");
        Button draw = new Button("Draw");
        Button select_tree_type = new Button("Select type");

        String[] tree_types_strings = {"Integer", "Double", "String"};

        tree_types_list = new JComboBox<>(tree_types_strings);
        tree_types_list.setSelectedIndex(0);
        tree_types_list.addActionListener(this);

        insert.addActionListener(this);
        delete.addActionListener(this);
        search.addActionListener(this);
        draw.addActionListener(this);
        select_tree_type.addActionListener(this);

        insert_input = new TextField(20);
        delete_input = new TextField(20);
        search_input = new TextField(20);

        Panel empty_panel = new Panel();

        /**
         * Dodanie do panelu wszystkich stworzonych elementow
         */

        given_panel.add(insert);
        given_panel.add(insert_input);
        given_panel.add(delete);
        given_panel.add(delete_input);
        given_panel.add(search);
        given_panel.add(search_input);
        given_panel.add(draw);
        given_panel.add(empty_panel);
        given_panel.add(tree_types_list);
        given_panel.add(select_tree_type);

    }

}
