#include "Kolo.h"

    Kolo::Kolo (double promien) : promien (promien)

    
    double Kolo::count_Pole()
    {
        pole = pow(promien, 2) * M_PI;
        pole = round(pole * 10.0) / 10.0;
        return pole;
    }

    double Kolo::count_Obwod()
    {
        obwod = 2 * M_PI * promien;
        obwod = round(obwod * 10.0) / 10.0;
        return obwod;
    }
