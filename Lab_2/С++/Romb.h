#ifndef Romb_h
#define Romb_h

class Romb : public Czworokat
{
public:
    Romb(double bok1, double kat);
    double count_Pole();

    string get_name()
    {
        return "Romb";
    }
};

#endif