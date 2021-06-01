#include "Pieciokat.h"

    Pieciokat::Pieciokat (double bok) : bok(bok) { }

    double Pieciokat::count_Pole()
    {
        pole = (pow(bok, 2) / 4) * sqrt(25 + (10 * sqrt(5)));
        pole = round(pole * 10.0) / 10.0;
        return pole;
    }

    double Pieciokat::count_Obwod()
    {
        obwod = bok * 5;
        return obwod;
    }