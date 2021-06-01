#ifndef Czworokat_h
#define Czworokat_h

class Czworokat : public Figura
{
    public:
        double bok1, bok2, bok3, bok4, kat;

    Czworokat (double bok1, double bok2, double bok3, double bok4, double kat);

    double count_Obwod();

    static Czworokat *decide_czworokat (double* boki, double kat) throw (string);
};

#endif