#ifndef Szesciokat_h
#define Szesciokat_h

class Szesciokat : public Figura
{
public:
    double bok;

    Szesciokat (double bok);

    double count_Pole();

    double count_Obwod();

    string get_name()
    {
        return "Szesciokat";
    }
};

#endif