package com.iljaknk;

class Zle_boki_czworokata extends Exception {};
class Zly_kat_czworokata extends Exception {};

abstract public class Figura implements Figure_operations
{
    double pole, obwod;
    String name_of_figure;

    public enum one_parametr_figures
    {
        OKRAG, PIECIOKAT, SZESCIOKAT, KWADRAT
    }

    public enum multiple_parametr_figures
    {
        PROSTOKAT, ROMB
    }

    public void print_info()
    {
        System.out.println("--------------\n" + get_name() + "\nPole = " + pole + "\nObwod = " + obwod);
    }

    public static Figura decide_czworokat (double[] boki, double kat) throws Zle_boki_czworokata, Zly_kat_czworokata
    {

        // sprawdzamy czy kat jest poprawny

        if (kat <= 0.0 || kat >= 180.0)
        {
            throw new Zly_kat_czworokata();
        }

        // wszystkie boki sa rowne

        if (boki[0] == boki[1] && boki[1] == boki[2] && boki[2] == boki[3])
        {
            if (kat == 90)
            {
                return new One_parametr_figure(one_parametr_figures.KWADRAT, boki[0]);
            }
            else
            {
                return new Multiple_parametr_figure(multiple_parametr_figures.ROMB, boki[0], kat);
            }
        }

        // czy jest prostokatem

        else if (boki[0] == boki[1] && boki[2] == boki[3])
        {
            return new Multiple_parametr_figure(multiple_parametr_figures.PROSTOKAT, boki[0], boki[2]);
        }

        else if (boki[0] == boki[3] && boki[1] == boki[2])
        {
            return new Multiple_parametr_figure(multiple_parametr_figures.PROSTOKAT, boki[0], boki[2]);
        }

        else if (boki[0] == boki[2] && boki[1] == boki[3])
        {
            return new Multiple_parametr_figure(multiple_parametr_figures.PROSTOKAT, boki[0], boki[3]);
        }

        throw new Zle_boki_czworokata();
    }

}
