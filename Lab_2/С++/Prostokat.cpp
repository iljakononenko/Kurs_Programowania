#include "Prostokat.h"
    Prostokat::Prostokat(double bok1, double bok2) : Czworokat (bok1, bok1, bok2, bok2, 90)

    double Prostokat::count_Pole()
    {
        pole = bok1 * bok3;
        pole = round(pole * 10.0) / 10.0;
        return pole;
    }

