#include <Romb.h>

    Romb::Romb(double bok1, double kat) : Czworokat (bok1, bok1, bok1, bok1, kat) { }

    double Romb::count_Pole()
    {
        pole = pow(bok1, 2) * sin(kat*3.14159/180);
        pole = round(pole * 10.0) / 10.0;
        return pole;
    }

