#include "Kwadrat.h"

    Kwadrat::Kwadrat(double bok) : Czworokat (bok, bok, bok, bok, 90) { }

    double Kwadrat::count_Pole()
    {
        pole = pow(bok1, 2);
        pole = round(pole * 10.0) / 10.0;
        return pole;
    }

