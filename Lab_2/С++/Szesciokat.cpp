#include "Szesciokat.h"

    Szesciokat::Szesciokat (double bok) : bok(bok) { }

    double Szesciokat::count_Pole()
    {
        pole = (3 * pow(bok, 2) * sqrt(3)) / 2;
        pole = round(pole * 10.0) / 10.0;
        return pole;
    }

    double Szesciokat::count_Obwod()
    {
        obwod = bok * 6;
        return obwod;
    }