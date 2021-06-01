import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

/**
 * Adapter do zamkniecia okna
 */

class WindowAdapter extends java.awt.event.WindowAdapter
{
    public void windowClosing(WindowEvent e) { System.exit(0); }
}

/**
 * klasa ktora tworzy caly interfejs programu
 */

public class my_Frame extends JFrame implements ActionListener
{

    /**
     * Pola do UI oraz String do opisu programu i instrukcjii do niego
     */

    my_Dialog dialog_instruction, dialog_info;

    Surface surface;

    String info = "\nProgram name: Figure canvas\n\n" +
            "Purpose: to draw and arrange figures on a canvas\n\n" +
            "Creator: Illia Kononenko\n";

    String manual = "\nUser manual\n\n" +
            "Click on a first menu button called 'Figury' and select a figure to draw\n\n" +
            "Then click on a canvas to draw a chosen figure\n\n" +
            "You can also:\n  - move a figure by pressing it and then dragging\n" +
            "  - rescale it by holding left mouse button and scrolling mouse wheel\n" +
            "  - choose different color by clicking right mouse button of a figure";

    /**
     * Pole do dalszego uruchomienia okna open/save pliku
     */

    private final JFileChooser open_file_chooser;

    /**
     * enum dla przeliczenia mozliwych figur programu
     */

    public enum Figura
    {
        OKRAG, PROSTOKAT, TROJKAT;
    }

    /**
     * konstruktor glownej klasy UI
     */

    public my_Frame()
    {

        dialog_info = new my_Dialog(this, info, "O programie");

        dialog_instruction = new my_Dialog(this, manual, "User manual");

        surface = new Surface();

        add(surface);

        setTitle("Basic shapes");

        setSize(768, 562);
        add_menu();

        addWindowListener( new WindowAdapter() );

        open_file_chooser = new JFileChooser();

        //open_file_chooser.setFileFilter(new FileNameExtensionFilter("txt"));
    }

    /**
     * metoda do dodania figury do panelu (canvasu)
     * @param figure_name - nazwa figury zapisana w roznych podmenu
     * @see Surface - klasa, do obiektu ktorego dodajemy figure, zeby w przeszlosci ja narysowac
     * @see my_Frame#actionPerformed(ActionEvent) - metoda ktora wywoluje 'add_new_figure'
     */

    private void add_new_figure (String figure_name)
    {
        Figura figure_type = null;

        switch (figure_name)
        {
            case "Okrag" -> figure_type = Figura.OKRAG;
            case "Prostokat" -> figure_type = Figura.PROSTOKAT;
            case "Trojkat" -> figure_type = Figura.TROJKAT;
        }

        surface.add_new_figure(figure_type);

    }

    /**
     * metoda dodajaca menu do glownego UI
     */

    private void add_menu ()
    {
        JMenuBar myMenu = new JMenuBar();
        JMenu menu1 = new JMenu("Figury");
        JMenu menu2 = new JMenu("Informacje");
        JMenu menu3 = new JMenu("Save/Load");
        myMenu.add(menu1);
        myMenu.add(menu2);
        myMenu.add(menu3);


        JMenuItem i1 = new JMenuItem("Okrag");
        i1.addActionListener(this);

        JMenuItem i2 = new JMenuItem("Prostokat");
        i2.addActionListener(this);

        JMenuItem i3 = new JMenuItem("Trojkat");
        i3.addActionListener(this);

        JMenuItem i4 = new JMenuItem("O programie");
        i4.addActionListener(this);

        JMenuItem i5 = new JMenuItem("Instrukcja");
        i5.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);

        JMenuItem i6 = new JMenuItem("Save file");
        i6.addActionListener(this);

        JMenuItem i7 = new JMenuItem("Load file");
        i7.addActionListener(this);

        menu1.add( i1 );
        menu1.add( i2 );
        menu1.add( i3 );
        menu1.addSeparator();
        menu1.add( exit );

        menu2.add( i4 );
        menu2.add( i5 );

        menu3.add( i6 );
        menu3.add( i7 );

        setJMenuBar(myMenu);
    }

    /**
     * metoda otwierajaca plik i wpisujaca figury z pliku do panelu (canvasu)
     * @see Surface#add_figures_from_file(String)
     */

    private void open_file ()
    {
        int return_value = open_file_chooser.showOpenDialog(this);

        if (return_value == JFileChooser.APPROVE_OPTION)
        {
            try
            {

                /**
                 * Probujemy otworzyc plik oraz odczytac dane
                 * Jak sie powiedzie, wpisujemy do String dane oraz przekazujemy je
                 * przez metode 'add_figures_from_file' obiektu surface klasy Surface
                 */

                File file = open_file_chooser.getSelectedFile();

                Scanner scanner = new Scanner(file);

                StringBuilder data = new StringBuilder();

                while (scanner.hasNextLine())
                {
                    data.append(scanner.nextLine() + "\n");
                }

                surface.add_figures_from_file(data.toString());
            }
            catch (IOException e)
            {
                System.out.println("Smth went wrong!");
            }
        }

        else
        {
            System.out.println("No file chosen!");
        }

    }

    /**
     * metoda zapisujaca do pliku figury z panelu
     * @see Surface#get_figures_data()
     */

    private void save_to_file ()
    {
        int return_value = open_file_chooser.showSaveDialog(this);

        if (return_value == JFileChooser.APPROVE_OPTION)
        {
            /**
             * Probujemy stworzyc plik oraz wpisac dane
             * Wpisujemy do String dane pobrane przez metode 'get_figures_data'
             * obiektu surface klasy Surface
             */

            try
            {
                File file = open_file_chooser.getSelectedFile();
                FileWriter fileWriter = new FileWriter(file, false);

                String data = surface.get_figures_data();

                fileWriter.write(data);

                fileWriter.close();

            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        else
        {
            System.out.println("No file chosen!");
        }
    }

    /**
     * Listener do obslugi przycisku czegos w menu
     * @param e - wybrany przycisk
     * @see my_Frame#add_new_figure(String)
     */

    @Override
    public void actionPerformed(ActionEvent e)
    {

        switch (e.getActionCommand())
        {
            case "Exit":
                System.exit(0);
                break;
            case "O programie":
                dialog_info.setVisible(true);
                break;
            case "Instrukcja":
                dialog_instruction.setVisible(true);
                break;
            case "Save file":
                save_to_file();
                break;
            case "Load file":
                open_file();
                break;
            default:
                add_new_figure(e.getActionCommand());
        }

    }
}
