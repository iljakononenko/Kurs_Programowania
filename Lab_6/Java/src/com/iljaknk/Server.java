package com.iljaknk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Klasa Serwera przechowujaca cala strukture danych
 * Drzewa BST
 * @param <T> - typ generyczny
 */

public class Server <T extends Comparable<T>>
{
    ServerSocket server = null;
    Socket client = null;
    BufferedReader in = null;
    PrintWriter out = null;
    String line = "";
    BST<T> bst_tree;
    String tree_type = "";
    boolean send_to_user = false;

    Server ()
    {
        bst_tree = new BST<T>();

        try
        {
            server = new ServerSocket(2476);
        }
        catch (IOException e)
        {
            System.out.println("Could not listen on port 2476");
            System.exit(-1);
        }
    }

    /**
     * Metoda do uruchomienia serwera
     */

    public void listen_Socket()
    {
        try
        {
            client = server.accept();
        }
        catch (IOException e)
        {
            System.out.println("Accept failed: 2476");
            System.exit(-1);
        }

        try
        {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        }
        catch (IOException e)
        {
            System.out.println("Accept failed: 2476");
            System.exit(-1);
        }

        /**
         * petla do otrzymania request Stringa z poleceniami
         * @see #operate_command(String) - metoda do obslugi Stringa z poleceniem
         */

        while (line != null)
        {
            try
            {
                line = in.readLine();

                //System.out.println(line);

                operate_command(line);
            }
            catch (IOException e)
            {
                System.out.println("Read failed");
                System.exit(-1);
            }
        }

    }

    /**
     * Metoda do zakonczenia dzialania serwera
     */

    protected void finalize()
    {
        try
        {
            in.close();
            out.close();
            client.close();
            server.close();
        }
        catch (IOException e)
        {
            System.out.println("Could not close.");
            System.exit(-1);
        }
    }

    /**
     * Metoda uruchamiajaca caly serwer
     * @param args
     */

    public static void main(String[] args)
    {
        Server server = new Server();
        server.listen_Socket();
    }

    /**
     * Metoda do obslugi String z podanym od uzytkownika poleceniem
     * Odzielamy podany String na dwie czesci dzieki znakowi ";"
     * @param given_string - podany String z poleceniem
     */

    public void operate_command (String given_string)
    {
        String[] command_arguments = given_string.split(";");
        String response = "";

        if (command_arguments.length == 2)
        {
            System.out.println(command_arguments[0] + " " + command_arguments[1]);

            String given_number = command_arguments[1];

            System.out.println(tree_type);

            try
            {
                /**
                 * Wybieramy dzialanie wedlug podanego polecenia
                 */

                switch (command_arguments[0])
                {
                    case "Insert" ->
                            {

                                bst_tree.insert((T)convert_given(given_number));

                                response = bst_tree.draw(bst_tree.root);

                                response = response.replaceAll("\n", "/");

                                send_to_user = true;
                            }
                    case "Delete" ->
                            {
                                bst_tree.delete((T)convert_given(given_number));

                                response = bst_tree.draw(bst_tree.root);

                                response = response.replaceAll("\n", "/");

                                send_to_user = true;
                            }
                    case "Search" ->
                            {

                                Element<T> found_element = bst_tree.find(bst_tree.root, (T)convert_given(given_number));

                                if (found_element != null)
                                {
                                    response = "Element found! element data is: " + found_element.data;
                                }
                                else
                                {
                                    response = "Element was not found!";
                                }
                                send_to_user = true;
                            }
                    case "Draw" ->
                            {
                                response = bst_tree.draw(bst_tree.root);

                                System.out.println(response);

                                response = response.replaceAll("\n", "/");

                                send_to_user = true;
                            }
                    case "Select type" ->
                            {
                                set_tree_type(command_arguments[1]);
                                System.out.println(command_arguments[1]);
                                response = "New type of a tree selected!";
                                send_to_user = true;
                            }
                }
            }
            catch (NumberFormatException e)
            {
                response = "Wrong type!";
                send_to_user = true;
            }

            /**
             * Wysylamy odpowiedz do uzytkownika
             */

            if (send_to_user)
            {
                out.println(response);
                send_to_user = false;
            }
            else
            {
                out.println("Something went wrong!");
            }
        }
    }

    /**
     * Metoda do konwertacji argumentu polecenia uzytkownika
     * @param given_value - String wartosci do konwertacji
     * @return - nowa wartosc z typem odpowiednim do wybranego typu drzewa
     */

    public Object convert_given (String given_value)
    {
        Object new_value = null;

        switch (tree_type)
        {
            case "Integer" ->
                    {
                        new_value = Integer.parseInt(given_value);
                    }
            case "Double" ->
                    {
                        new_value = Double.parseDouble(given_value);
                    }
            case "String" ->
                    {
                        new_value = given_value;
                    }
        }

        return new_value;
    }

    /**
     * Metoda do ustalania typu drzewa
     * @param given_string - String zawierajacy typ, wedlug ktorego zmieniamy typ drzewa
     */

    public void set_tree_type (String given_string)
    {
        tree_type = given_string;
        System.out.println(tree_type + " type of a tree");
        bst_tree = new BST<>();
    }

}
