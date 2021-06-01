#ifndef Pieciokat_h
#define Pieciokat_h

class Pieciokat : public Figura
{
public:
    double bok;

    Pieciokat (double bok);

    double count_Pole();

    double count_Obwod();

    string get_name()
    {
        return "Pieciokat";
    }
};

#endif