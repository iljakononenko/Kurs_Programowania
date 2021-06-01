#ifndef Prostokat_h
#define Prostokat_h

class Prostokat : public Czworokat
{
public:
    Prostokat(double bok1, double bok2);

    double count_Pole();

    string get_name()
    {
        return "Prostokat";
    }
};

#endif