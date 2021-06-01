#ifndef Kolo_h
#define Kolo_h

class Kolo : public Figura
{
public:
    double promien;

    Kolo (double promien);

    
    double count_Pole();

    double count_Obwod();

    string get_name()
    {
        return "Kolo";
    }
};

#endif