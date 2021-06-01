#ifndef Kwadrat_h
#define Kwadrat_h

class Kwadrat : public Czworokat
{
public:
    Kwadrat(double bok);

    double count_Pole();

    string get_name()
    {
        return "Kwadrat";
    }
};

#endif