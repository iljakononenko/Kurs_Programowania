package com.iljaknk;

/**
 * Klasa do stworzenia zwyklego drzewa binarnego BST
 * @param <T> - typ generyczny klasy
 * Po wszystkie wyjasnienia co do implementacji
 * odsylam do ksiazki Cormena "Introduction to algorithms"
 */

public class BST<T extends Comparable<T>>
{

    Element<T> root = null;

    /**
     * Metoda do wstawienia nowego elementu do struktury danych (BST)
     * @param data - argument nowego elementu
     */

    public void insert(T data)
    {
        Element<T> y = null;
        Element<T> x = root;

        while (x != null)
        {
            y = x;
            if (data.compareTo(x.data) < 0)
            {
                x = x.left_child;
            }
            else
            {
                x = x.right_child;
            }
        }

        Element<T> new_element = new Element(y, null, null, data);

        if (y == null)
        {
            System.out.println("Root is set!");
            root = new_element;
        }
        else if (new_element.data.compareTo(y.data) < 0)
        {
            y.left_child = new_element;
        }
        else
        {
            y.right_child = new_element;
        }

    }

    /**
     * Metoda do usuwania elementu o podanej wartosci
     * @param data - wartosci elementu do usuniecia
     */

    public void delete(T data)
    {
        Element<T> element_to_delete = find(root, data);

        if (element_to_delete != null)
        {

            /***** Warunek 1 *****/

            if (element_to_delete.left_child == null)
            {
                transplant(element_to_delete, element_to_delete.right_child);
            }

            /***** Warunek 2 *****/

            else if (element_to_delete.right_child == null)
            {
                transplant(element_to_delete, element_to_delete.left_child);
            }

            /***** Warunek 3 *****/

            else
            {
                Element<T> y = min(element_to_delete.left_child);

                if (y.parent != element_to_delete)
                {

                    transplant(y, y.right_child);
                    y.right_child = element_to_delete.right_child;
                    y.right_child.parent = y;

                }

                transplant(element_to_delete, y);

                y.left_child = element_to_delete.left_child;

                y.left_child.parent = y;

            }


        }

    }

    /**
     * Metoda do znajdowania elementu o podanej wartosci (podanym argumencie)
     * @param element_given - element od ktorego mamy zaczac szukac (zazwyczaj to jest root)
     * @param data - argument elementu ktory musimy znalesc
     * @return - zwracamy znaleziony element do dalszych operacji
     */

    public Element<T> find(Element<T> element_given, T data)
    {

        /***** recursive method *****/

        Element<T> element_to_find = element_given;

        if (element_to_find == null || element_given.data.equals(data))
        {
            return element_to_find;
        }

        if (data.compareTo(element_to_find.data) < 0)
        {
            return find(element_to_find.left_child, data);
        }

        else return find(element_to_find.right_child, data);


    }

    /**
     * Metoda do znajdowania minimalnej wartosci w drzewie
     * @param element - element od ktorego zaczynamy
     * @return - zwracamy element o najmniejszym argumencie
     */

    public Element<T> min(Element<T> element)
    {

        if (element == null)
            return new Element<T>(null, null, null, null);

        while (element.left_child != null)
        {
            element = element.left_child;
        }
        return element;
    }

    /**
     * Metoda do znajdowania maksymalnej wartosci w drzewie
     * @param element - element od ktorego zaczynamy
     * @return - zwracamy element o najwiekszym argumencie
     */

    public Element<T> max(Element<T> element)
    {

        if (element == null)
            return new Element<T>(null, null, null, null);

        while (element.right_child != null)
        {
            element = element.right_child;
        }
        return element;
    }

    /**
     * Metoda do znajdowania nastepnika elementu o podanym argumencie
     * @param data - argument elementu, ktorego nastepnik mamy znalezc
     * @return - zwracamy element, ktory jest nastepnikiem elementu o podanym argumencie
     */

    public Element<T> successor(T data)
    {

        Element<T> element = find(root, data);

        if (element.right_child != null)
        {
            return min(element.right_child);
        }

        Element<T> y = element.parent;

        while (y != null && element == y.right_child)
        {
            element = y;
            y = y.parent;
        }

        return y;
    }

    /**
     * Metoda do wydrukowania calego drzewa w kolejnosci rosnacej
     * @param element - element od ktorego zaczynamy (zazwyczaj to jest root)
     */

    public void inorder(Element<T> element)
    {
        if (element != null)
        {
            inorder(element.left_child);
            System.out.print("|" + element.data + "| ");
            inorder(element.right_child);
        }

    }

    /**
     * Metoda do wydrukowania calego drzewa bardziej graficznie
     * @param element - element od ktorego zaczynamy (zazwyczaj to jest root)
     * @return - zracany String do dalszego przekazywania
     */

    public String draw(Element<T> element)
    {
        String line = "";
        if (element != null)
        {
            line += draw(element.left_child);
            System.out.print("               |" + element.data + "| ");

            line += "|" + element.data + "| \n";

            System.out.print("\n");

            if (element.left_child != null)
            {
                System.out.print("left: |" + element.left_child.data + "|");
                line += "left: |" + element.left_child.data + "|";
            }
            else
            {
                System.out.print("left: |NULL|");
                line += "left: |NULL|";
            }

            if (element.right_child != null)
            {
                System.out.print("           right: |" + element.right_child.data + "| \n");
                line += "           right: |" + element.right_child.data + "| \n";
            }
            else
            {
                System.out.print("           right: |NULL| \n");
                line += "           right: |NULL| \n";
            }
            System.out.println("--------------------------------------\n");
            line += "--------------------------------------\n";
            line += draw(element.right_child);
        }
        return line;

    }

    /**
     * Metoda do odpowiedniego przestawienia elementow po usunieciu z drzewa
     * @param element_from - element ktory usuwamy
     * @param element_to - element - zamiana na usuwany element
     */

    public void transplant(Element<T> element_from, Element<T> element_to)
    {
        if (element_from.parent == null)
        {
            set_root(element_to);
        }

        else if (element_from == element_from.parent.left_child)
        {
            element_from.parent.left_child = element_to;
        }

        else
        {
            element_from.parent.right_child = element_to;
        }

        if (element_to != null)
        {
            element_to.parent = element_from.parent;
        }

    }

    /**
     * Metoda do ustalenia nowego roota
     * @param new_root - element ktory bedzie nowym rootem (korzeniem)
     */

    public void set_root (Element<T> new_root)
    {
        root = new_root;
    }

    /**
     * Metoda do odzyskania demonstracji pojedynczego elementu
     * @param elem - podany element do odebrania informacji
     * @return - String z graficzna demonstracja elementu oraz jego dzieci
     */

    public String to_String(Element<T> elem)
    {
        String return_value = "()";
        if (elem != null)
        {
            return_value = "("+ elem.data + ":"+ to_String (elem.left_child) + " :"+ to_String (elem.right_child)+ ")";
        }
        return return_value;
    }
}
