package com.iljaknk;

public class One_parametr_figure extends Figura
{
    one_parametr_figures type_of_figure;

    double parametr;

    One_parametr_figure (one_parametr_figures option, double parametr)
    {
        type_of_figure = option;
        this.parametr = parametr;
    }

    public double Count_Pole ()
    {

        switch (type_of_figure)
        {
            case OKRAG:
                pole = Math.pow(parametr, 2) * Math.PI;
                pole = Math.round(pole * 10.0) / 10.0;
                break;
            case PIECIOKAT:
                pole = (Math.pow(parametr, 2) / 4) * Math.sqrt(25 + (10 * Math.sqrt(5)));
                pole = Math.round(pole * 10.0) / 10.0;
                break;
            case SZESCIOKAT:
                pole = (3 * Math.pow(parametr, 2) * Math.sqrt(3)) / 2;
                pole = Math.round(pole * 10.0) / 10.0;
                break;
            case KWADRAT:
                pole = Math.pow(parametr, 2);
                pole = Math.round(pole * 10.0) / 10.0;
                break;
        }


        return pole;
    }

    public double Count_Obwod ()
    {

        switch (type_of_figure)
        {
            case OKRAG:
                obwod = 2 * Math.PI * parametr;
                obwod = Math.round(obwod * 10.0) / 10.0;
                break;
            case KWADRAT:
                obwod = 4 * parametr;
                break;
            case PIECIOKAT:
                obwod = 5 * parametr;
                break;
            case SZESCIOKAT:
                obwod = 6 * parametr;
                break;
        }


        return obwod;
    }

    public String get_name()
    {
        name_of_figure = "figura";
        switch (type_of_figure)
        {
            case KWADRAT:
                name_of_figure = "Kwadrat";
                break;
            case OKRAG:
                name_of_figure = "Okrag";
                break;
            case PIECIOKAT:
                name_of_figure = "Pieciokat";
                break;
            case SZESCIOKAT:
                name_of_figure = "Szesciokat";
                break;
        }
        return name_of_figure;
    }
}
