package com.iljaknk;

/**
 * Klasa z typem generycznym reprezentujaca element w strukturze danych BST
 * @param <T> - typ generyczny
 */

public class Element <T extends Comparable<T>>
{

    Element<T> parent;
    Element<T> left_child;
    Element<T> right_child;
    T data;

    /**
     * Konstruktor do utworzenia nowego elementu
     * @param parent
     * @param left_child
     * @param right_child
     * @param data
     */

    Element(Element parent, Element left_child, Element right_child, T data)
    {
        this.parent = parent;
        this.left_child = left_child;
        this.right_child = right_child;
        this.data = data;
    }

}
