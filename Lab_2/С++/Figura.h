#ifndef Figura_h
#define Figura_h

class Figura
{
    public:
    double pole, obwod;

	virtual double count_Pole() = 0;
    virtual double count_Obwod() = 0;
    virtual string get_name() = 0;

    void print_info();
    
};

#endif