#include "Czworokat.h"

    Czworokat::Czworokat (double bok1, double bok2, double bok3, double bok4, double kat)
    {
        this -> bok1 = bok1;
        this -> bok2 = bok2;
        this -> bok3 = bok3;
        this -> bok4 = bok4;
        this -> kat = kat;
    }

    double Czworokat::count_Obwod()
    {
        obwod = bok1 + bok2 + bok3 + bok4;
        return obwod;
    }

    static Czworokat::Czworokat *decide_czworokat (double* boki, double kat) throw (string)
    {

        // sprawdzamy czy kat jest poprawny

        if (kat <= 0.0 || kat >= 180.0)
        {
            throw (string) "Zly kat czworokata";
        }

        // wszystkie boki sa rowne

        if (boki[0] == boki[1] && boki[1] == boki[2] && boki[2] == boki[3])
        {
            if (kat == 90)
            {
                //Kwadrat kwadrat(boki[0]);
                return new Kwadrat(boki[0]);
            }
            else
            {
                //Romb romb(boki[0], kat);
                return new Romb(boki[0], kat);
            }
        }

        // czy jest prostokatem

        else if (boki[0] == boki[1] && boki[2] == boki[3])
        {
            //Prostokat prostokat(boki[0], boki[2]);
            return new Prostokat(boki[0], boki[2]);
        }

        else if (boki[0] == boki[3] && boki[1] == boki[2])
        {
            //Prostokat prostokat(boki[0], boki[2]);
            return new Prostokat(boki[0], boki[2]);
        }

        else if (boki[0] == boki[2] && boki[1] == boki[3])
        {
            //Prostokat prostokat(boki[0], boki[3]);
            return new Prostokat(boki[0], boki[3]);
        }
       

        throw (string) "Zle boki czworokata!";
    }