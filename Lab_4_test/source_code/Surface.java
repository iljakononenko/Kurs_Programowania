import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Klasa do stworzenia UI i rysowania figur
 */

public class Surface extends JPanel
{
    /**
     * pola do manipulacja figurami
     */
    private Graphics2D g2d;

    Color custom_color = Color.BLACK;

    /**
     * 3 Arraylisty dla przechowywania figur (jeden Arraylist osobno dla kazdej figury)
     */

    ArrayList<my_Ellipse> kola = new ArrayList<>();
    ArrayList<my_Rectangle> prostokaty = new ArrayList<>();
    ArrayList<my_Triangle> trojkaty = new ArrayList<>();

    /**
     * Pola do manipulacji wybrana, ruchajaca oraz nowostworzana figura
     */

    my_Rectangle moving_rectangle, chosen_rectangle, new_rectangle;
    my_Ellipse moving_ellipse, chosen_ellipse, new_ellipse;
    my_Triangle moving_triangle, chosen_triangle, new_triangle;

    boolean figure_chosen_for_moving = false;

    /**
     * Konstruktor Klasy Surface
     * @see Surface
     */

    public Surface ()
    {
        Moving_Adapter moving_adapter = new Moving_Adapter();

        addMouseListener(moving_adapter);
        addMouseMotionListener(moving_adapter);

        addMouseWheelListener(new Scale_Handler());

    }

    /**
     * Metoda zwracajaca ciag znakow do wypisania do pliku
     * @see my_Frame
     * metoda jest wywolana przez metode 'save_to_file()' klasy my_Frame
     * @return String - zwraca ciag znakow
     */

    public String get_figures_data()
    {
        StringBuilder data = new StringBuilder();

        /**
         * Trzy petli for do wypisania z kazdego Arraylist trzech figur wszystkich figur
         * do Stringbuilder data
         */

        for (my_Ellipse kolo : kola)
        {
            data.append(
                    "kolo|" + kolo.x + "|"
                            + kolo.y + "|"
                            + kolo.height + "|"
                            + kolo.width + "|"
                            + kolo.color_figure.getRGB() + "\n");
        }

        for (my_Rectangle prostokat : prostokaty)
        {
            data.append(
                    "prostokat|" + prostokat.x + "|"
                            + prostokat.y + "|"
                            + prostokat.height + "|"
                            + prostokat.width + "|"
                            + prostokat.color_figure.getRGB() + "\n"
            );
        }

        for (my_Triangle triangle : trojkaty)
        {
            data.append(
                    "trojkat|" + triangle.x + "|"
                            + triangle.y + "|"
                            + triangle.height + "|"
                            + triangle.width + "|"
                            + triangle.color_figure.getRGB() + "\n");
        }

        return data.toString();
    }

    /**
     * Metoda importujaca figury z pliku
     * @param data - String, ktory opisuje figury do importu
     * @see my_Triangle
     * @see my_Ellipse
     * @see my_Rectangle
     */

    public void add_figures_from_file(String data)
    {
        try
        {
            /**
             * Usuwamy wszystkie stworzone wczesniej figury
             */
            //System.out.println(data);
            BufferedReader bufferedReader = new BufferedReader(new StringReader(data));
            String line = null;

            kola = new ArrayList<>();
            prostokaty = new ArrayList<>();
            trojkaty = new ArrayList<>();

            /**
             * Krecimy petle poki sa kolejne linie figur
             * Kazda czesc danych jest oddzielona znakiem '|'
             * Decydujemy ktora figure dodac patrac na pierwsze slowo (switch - case)
             * (zapisywalismy go przy metodzie 'get_figures_data')
             * Dalej wpisujemy po kolej dane figury (x, y, width, height, color)
             * Na koncu odnawiamy rysenek za pomoca repaint()
             */

            while ( (line = bufferedReader.readLine() ) != null)
            {
                String[] figure = line.split("\\|");
                switch (figure[0])
                {
                    case "kolo" -> kola.add (
                            new my_Ellipse(
                                    Float.parseFloat(figure[1]),
                                    Float.parseFloat(figure[2]),
                                    Float.parseFloat(figure[3]),
                                    Float.parseFloat(figure[4]),
                                    new Color(Integer.parseInt(figure[5]))
                            ) );

                    case "prostokat" -> prostokaty.add (
                            new my_Rectangle(
                                    Float.parseFloat(figure[1]),
                                    Float.parseFloat(figure[2]),
                                    Float.parseFloat(figure[3]),
                                    Float.parseFloat(figure[4]),
                                    new Color(Integer.parseInt(figure[5]))
                            ) );

                    case "trojkat" -> trojkaty.add (
                            new my_Triangle(
                                    Float.parseFloat(figure[1]),
                                    Float.parseFloat(figure[2]),
                                    Float.parseFloat(figure[3]),
                                    Float.parseFloat(figure[4]),
                                    new Color(Integer.parseInt(figure[5]))
                            )  );
                }

            }

            repaint();
        }
        catch (IOException e)
        {
            System.out.println("Smth with file!");
        }

    }

    /**
     *  Rysujemy figury pobrane z trzech Arraylistow
     * @see my_Triangle
     * @see my_Ellipse
     * @see my_Rectangle
     */

    private void draw () {

        for (my_Ellipse kolo : kola)
        {
            g2d.setPaint(kolo.color_figure);
            g2d.fill( kolo );
        }

        for (my_Rectangle prostokat : prostokaty)
        {
            g2d.setPaint(prostokat.color_figure);
            g2d.fill( prostokat );
        }

        for (my_Triangle triangle : trojkaty)
        {
            g2d.setPaint(triangle.color_figure);
            g2d.fill(triangle.triangle_path);

            g2d.dispose();
        }

    }

    /**
     * Metoda do tworzenia figury i zapisywania do pola 'new_...' figura
     * Pozniej wstawimy ja przez klik myszy
     * @see Moving_Adapter#mouseClicked(MouseEvent)
     * @param figure_type
     */

    public void add_new_figure (my_Frame.Figura figure_type)
    {
        custom_color = Color.BLACK;

        switch (figure_type)
        {
            case PROSTOKAT -> new_rectangle = new my_Rectangle(50, 50, 50,50, Color.BLACK);

            case OKRAG -> new_ellipse = new my_Ellipse(150, 150, 50,50, Color.BLACK);

            case TROJKAT -> new_triangle = new my_Triangle(0, 0, 50, 50, Color.BLACK);

        }

    }

    /**
     * paintComponent rysujacy figury
     * @param g
     */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;

        g2d.setPaint(custom_color);

        draw();

    }

    /**
     * Klasy figur
     */

    /**
     * Klasa my_Ellipse
     * @see Ellipse2D.Float
     */

    class my_Ellipse extends Ellipse2D.Float
    {
        Color color_figure;

        public my_Ellipse(float x, float y, float width, float height, Color color)
        {
            setFrame(x, y, width, height);
            color_figure = color;
        }

        /**
         * metoda do sprawdzenia czy figura jest 'pod' mysza
         * @param x - x myszy
         * @param y - y myszy
         * @return - zwraca wartosc 'true' lub 'false' w zaleznosci
         * czy mysz znajduje sie nad figura
         */

        public boolean is_Hit (float x, float y)
        {
            return getBounds2D().contains(x, y);
        }

        public void add_X (float x)
        {
            this.x += x;
        }

        public void add_Y (float y)
        {
            this.y += y;
        }

        public void add_Width (float width)
        {
            this.width += width;
        }

        public void add_Height (float height)
        {
            this.height += height;
        }

        public void set_location (float x, float y)
        {
            this.x = x;
            this.y = y;
        }

        public void set_color (Color new_color)
        {
            this.color_figure = new_color;
        }
    }

    /**
     * Klasa my_Rectangle
     * @see Rectangle2D.Float
     */

    class my_Rectangle extends Rectangle2D.Float
    {
        Color color_figure;

        public my_Rectangle(float x, float y, float width, float height, Color color)
        {
            setRect(x, y, width, height);
            color_figure = color;
        }

        /**
         * metoda do sprawdzenia czy figura jest 'pod' mysza
         * @param x - x myszy
         * @param y - y myszy
         * @return - zwraca wartosc 'true' lub 'false' w zaleznosci
         * czy mysz znajduje sie nad figura
         */

        public boolean is_Hit (float x, float y)
        {
            return getBounds2D().contains(x, y);
        }

        public void add_X (float x)
        {
            this.x += x;
        }

        public void add_Y (float y)
        {
            this.y += y;
        }

        public void add_Width (float width)
        {
            this.width += width;
        }

        public void add_Height (float height)
        {
            this.height += height;
        }

        public void set_location (float x, float y)
        {
            this.x = x;
            this.y = y;
        }

        public void set_color (Color new_color)
        {
            this.color_figure = new_color;
        }
    }

    /**
     * Klasa my_Triangle
     * @see Path2D.Float
     */

    class my_Triangle extends Path2D.Float
    {

        float x; float y; float width; float height;
        Color color_figure;
        GeneralPath triangle_path;

        private float points[][];

        public my_Triangle(float x, float y, float width, float height, Color color)
        {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            color_figure = color;

            points = new float[3][2];

            set_points_and_lines();

        }

        /**
         * metoda do polaczenia wierzcholkow trojkata
         */

        private void set_points_and_lines()
        {
            points[0][0] = x - width;
            points[0][1] = y + height;

            points[1][0] = x + width;
            points[1][1] = y + height;

            points[2][0] = x;
            points[2][1] = y - height;

            triangle_path = new GeneralPath();

            triangle_path.moveTo(points[0][0], points[0][1]);

            for (int k = 1; k < points.length; k++)
            {
                triangle_path.lineTo( points[k][0], points[k][1] );
            }

            triangle_path.closePath();
        }

        /**
         * metoda do sprawdzenia czy figura jest 'pod' mysza
         * @param x - x myszy
         * @param y - y myszy
         * @return - zwraca wartosc 'true' lub 'false' w zaleznosci
         * czy mysz znajduje sie nad figura
         */

        public boolean is_Hit (float x, float y)
        {
            if ( x >= this.x - width && x <= this.x + width)
            {
                if ( y >= this.y - height && y <= this.y + height)
                {
                    return true;
                }
            }
            return false;
        }

        public void add_X (float x)
        {
            this.x += x;
            set_points_and_lines();
        }

        public void add_Y (float y)
        {
            this.y += y;
            set_points_and_lines();
        }

        public void add_Width (float width)
        {
            this.width += width;
            set_points_and_lines();
        }

        public void add_Height (float height)
        {
            this.height += height;
            set_points_and_lines();
        }

        public void set_location (float x, float y)
        {
            this.x = x;
            this.y = y;
            set_points_and_lines();
        }

        public void set_color (Color new_color)
        {
            this.color_figure = new_color;
        }
    }

    /**
     * Klasa do obslugi myszy
     * @see #mouseClicked(MouseEvent)
     * @see #mouseDragged(MouseEvent)
     * @see #mousePressed(MouseEvent)
     */

    class Moving_Adapter extends MouseAdapter
    {
        private int x, y;

        /**
         * Jak przyciskamy i trzymamy mysz, mozemy ruszac figura pod mysza
         * Figura jest znajdowana i aktywowana przez metode 'find_figure_under_mouse(float, float)'
         * @see #find_figure_under_mouse(float, float)
         * flaga 'figure_chosen_for_moving' odziela manipulacje powiazane z ruszaniem figury oraz
         * innymi
         * @see #mouseDragged(MouseEvent)
         * @see #do_Move(MouseEvent)
         * @param e - informacje o myszy (x, y) itd.
         */

        @Override
        public void mousePressed(MouseEvent e)
        {
            if (SwingUtilities.isLeftMouseButton(e))
            {
                x = e.getX();
                y = e.getY();

                figure_chosen_for_moving = true;

                find_figure_under_mouse(x, y);
            }

        }

        /**
         * Metoda ktora uruchamia ruszanie sie figura dzieki metodzie 'do_Move'
         * @see #do_Move(MouseEvent)
         * @param e - informacje o myszy (x, y) itd.
         */

        @Override
        public void mouseDragged(MouseEvent e)
        {
            if (figure_chosen_for_moving)
            {
                do_Move(e);
            }
        }

        /**
         * Metoda ktora:
         *  - tworzy figure (jesli zostala wybrana w menu)
         *  - wybiera figure dla dalszych manipulacji (zwiekszenia, pomniejszenia)
         *  - otwiera menu do zmiany koloru figury (jak prawy przycisk myszy jest aktywowany)
         * @see my_PopUp
         * @param e
         */

        @Override
        public void mouseClicked (MouseEvent e)
        {
            if (SwingUtilities.isRightMouseButton(e))
            {
                open_color_menu(e);
            }

            else if (SwingUtilities.isLeftMouseButton(e))
            {
                x = e.getX();
                y = e.getY();

                if (new_ellipse != null)
                {
                    new_ellipse.set_location(x - (new_ellipse.width / 2) , y - (new_ellipse.height / 2) );
                    kola.add(new_ellipse);
                    new_ellipse = null;
                    repaint();
                }

                if (new_rectangle != null)
                {
                    new_rectangle.set_location(x - (new_rectangle.width / 2), y - (new_rectangle.height / 2) );
                    prostokaty.add(new_rectangle);
                    new_rectangle = null;
                    repaint();
                }

                if (new_triangle != null)
                {
                    new_triangle.set_location(x - (new_triangle.width / 2), y - (new_triangle.height / 2) );
                    trojkaty.add(new_triangle);
                    new_triangle = null;
                    repaint();
                }

                find_figure_under_mouse(x, y);
            }
        }

        /**
         * Metoda ktora usuwa flage do ruszania sie myszy (jak przycisk jest odpuszczony)
         * @param e
         * @see #mousePressed(MouseEvent)
         */

        @Override
        public void mouseReleased(MouseEvent e)
        {
            figure_chosen_for_moving = false;
        }

        /**
         * Metoda przesuwajaca przycisneta figure
         * @see #mouseDragged(MouseEvent)
         * @see #mousePressed(MouseEvent)
         * Patrzymy ktora figura jest wybrana (przez 3 ify)
         * i dodajemy do niej 'x' i 'y'
         * @param e
         */

        private void do_Move(MouseEvent e)
        {

            int dx = e.getX() - x;
            int dy = e.getY() - y;

            if (moving_ellipse != null)
            {
                moving_ellipse.add_X(dx);
                moving_ellipse.add_Y(dy);
                repaint();
            }

            if (moving_rectangle != null)
            {
                moving_rectangle.add_X(dx);
                moving_rectangle.add_Y(dy);
                repaint();
            }

            if (moving_triangle != null)
            {
                moving_triangle.add_X(dx);
                moving_triangle.add_Y(dy);
                repaint();
            }

            x += dx;
            y += dy;

        }
    }

    /**
     * Klasa obslugujaca scroll myszy (do zwiekszenia/zmniejszenia figury)
     */

    class Scale_Handler implements MouseWheelListener
    {

        /**
         * Metoda wywolana przy ruszaniu scroll
         * @param e
         */

        @Override
        public void mouseWheelMoved(MouseWheelEvent e)
        {
            do_Scale(e);
        }

        /**
         * Metoda do zmiany rozmiaru wybranej figury
         * @param e
         */

        private void do_Scale(MouseWheelEvent e)
        {
            /**
             * Sprawdzamy czy byl nacisnety scroll myszy
             * Jesli tak, sprawdzamy ktora figura jest aktywna (kliknieta wczesniej)
             * @see Moving_Adapter#mouseClicked(MouseEvent)
             */
            if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL)
            {

                if (chosen_ellipse != null)
                {
                    float amount = e.getWheelRotation() * 5f;
                    chosen_ellipse.add_Width(amount);
                    chosen_ellipse.add_Height(amount);
                }

                if (chosen_rectangle != null)
                {
                    float amount = e.getWheelRotation() * 5f;
                    chosen_rectangle.add_Width(amount);
                    chosen_rectangle.add_Height(amount);
                }

                if (chosen_triangle != null)
                {
                    float amount = e.getWheelRotation() * 5f;
                    chosen_triangle.add_Width(amount);
                    chosen_triangle.add_Height(amount);
                }

                repaint();

            }
        }
    }

    /**
     * Metoda znajdujaca i wybierajaca figure ktora jest pod wybranymi koordynatami
     *
     * @param x - koordynata 'x'
     * @param y - koordynata 'y'
     */

    public void find_figure_under_mouse (float x, float y)
    {
        /**
         * Zerujemy wszystkie wybrane figury do ruszania sie
         * oraz innych manipulacji
         */

        chosen_ellipse = null;
        chosen_rectangle = null;
        chosen_triangle = null;

        moving_ellipse = null;
        moving_rectangle = null;
        moving_triangle = null;

        /**
         * Uzywamy 3 petli, zeby przejsc przez wszystkie figury
         * wszystkich Arraylist odpowiednich figur
         * @see my_Ellipse
         * @see my_Ellipse#is_Hit(float, float)
         * @see my_Triangle
         * @see my_Triangle#is_Hit(float, float)
         * @see my_Rectangle
         * @see my_Rectangle#is_Hit(float, float)
         */

        for (my_Ellipse kolo : kola)
        {
            if (kolo.is_Hit(x, y))
            {
                moving_ellipse = kolo;
                chosen_ellipse = kolo;
            }
        }

        if (chosen_ellipse == null)
        {
            for (my_Rectangle prostokat : prostokaty)
            {
                if (prostokat.is_Hit(x, y))
                {
                    moving_rectangle = prostokat;
                    chosen_rectangle = prostokat;
                }
            }
        }
        if (chosen_rectangle == null && chosen_ellipse == null)
        {
            for (my_Triangle triangle : trojkaty)
            {
                if (triangle.is_Hit(x, y))
                {
                    moving_triangle = triangle;
                    chosen_triangle = triangle;
                }
            }
        }
    }

    /**
     * metoda do otwierania menu wyboru koloru aktywnej figury
     * @see my_PopUp
     * @see my_PopUp#actionPerformed(ActionEvent)
     * @param e - dane o przycisku myszy
     * @see #open_color_menu#show()  - sluzy do pokazania menu wyboru koloru
     * e.getX(), e.getY() - przekazuja w ktorym miejscu musi pojawic sie menu
     */

    private void open_color_menu(MouseEvent e)
    {
        my_PopUp choose_color_menu = new my_PopUp(this);

        choose_color_menu.show(e.getComponent(), e.getX(), e.getY());

    }

    /**
     * Metoda do zmiany koloru
     * Wywolana przez obiekt my_PopUp
     * @see my_PopUp
     * @see my_PopUp#actionPerformed(ActionEvent)
     * @param color - String wybranego przycisku
     * dzieki 'switch - case' wybieramy kolor klasy 'Color'
     * Na koncu sprawdzamy ktora figura jest aktywna i zmieniamy jej kolor
     */

    public void change_selected_figure_color (String color)
    {
        Color new_color = Color.CYAN;

        switch (color)
        {
            case "Red" -> new_color = Color.RED;
            case "Blue" -> new_color = Color.BLUE;
            case "Black" -> new_color = Color.BLACK;
            case "White" -> new_color = Color.WHITE;
            case "Green" -> new_color = Color.GREEN;
            case "Yellow" -> new_color = Color.YELLOW;
        }

        if (chosen_ellipse != null)
        {
            chosen_ellipse.set_color(new_color);
        }

        if (chosen_rectangle != null)
        {
            chosen_rectangle.set_color(new_color);
        }

        if (chosen_triangle != null)
        {
            chosen_triangle.set_color(new_color);
        }

        repaint();
    }

}
