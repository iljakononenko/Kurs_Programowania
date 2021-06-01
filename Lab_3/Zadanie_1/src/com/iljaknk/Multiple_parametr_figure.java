package com.iljaknk;

public class Multiple_parametr_figure extends Figura
{
    multiple_parametr_figures type_of_figure;

    double bok, parametr_2;

    Multiple_parametr_figure (multiple_parametr_figures option, double bok, double parametr_2)
    {
        type_of_figure = option;
        this.bok = bok;
        this.parametr_2 = parametr_2;
    }

    public double Count_Pole ()
    {

        switch (type_of_figure)
        {
            case PROSTOKAT:
                pole = bok * parametr_2;
                pole = Math.round(pole * 10.0) / 10.0;
                break;
            case ROMB:
                pole = Math.pow(bok, 2) * Math.sin(Math.toRadians(parametr_2));
                pole = Math.round(pole * 10.0) / 10.0;
                break;
        }


        return pole;
    }

    public double Count_Obwod ()
    {

        switch (type_of_figure)
        {
            case PROSTOKAT:
                obwod = bok + parametr_2;
                break;
            case ROMB:
                obwod = 4 * bok;
                break;
        }


        return obwod;
    }

    public String get_name()
    {
        name_of_figure = "figura";
        switch (type_of_figure)
        {
            case PROSTOKAT:
                name_of_figure = "Prostokat";
                break;
            case ROMB:
                name_of_figure = "Romb";
                break;
        }
        return name_of_figure;
    }

}
