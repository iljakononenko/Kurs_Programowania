#include <iostream>
#include <string>
#include <cmath>
#include <list>
#include <vector>
#include <sstream>
/*
#include "Figura.h"
#include "Czworokat.h"
#include "Pieciokat.h"
#include "Szesciokat.h"
#include "Kolo.h"
#include "Kwadrat.h"
#include "Prostokat.h"
#include "Romb.h"*/


using namespace std;

// ---------------------------------------------------------------------------------------------

class Figura
{
    public:
    double pole, obwod;

    virtual double count_Pole() = 0;
    virtual double count_Obwod() = 0;
    virtual string get_name() = 0;

    void print_info();
    
};

// ---------------------------------------------------------------------------------------------

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

// ---------------------------------------------------------------------------------------------

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

// ---------------------------------------------------------------------------------------------

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

// ---------------------------------------------------------------------------------------------

class Czworokat : public Figura
{
    public:
        double bok1, bok2, bok3, bok4, kat;

    Czworokat (double bok1, double bok2, double bok3, double bok4, double kat);

    double count_Obwod();

    static Czworokat *decide_czworokat (double* boki, double kat) throw (string);
};

// ---------------------------------------------------------------------------------------------

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

// ---------------------------------------------------------------------------------------------

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

// ---------------------------------------------------------------------------------------------

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

// ---------------------------------------------------------------------------------------------

    Szesciokat::Szesciokat (double bok) : bok(bok) { }

    double Szesciokat::count_Pole()
    {
        pole = (3 * pow(bok, 2) * sqrt(3)) / 2;
        pole = round(pole * 10.0) / 10.0;
        return pole;
    }

    double Szesciokat::count_Obwod()
    {
        obwod = bok * 6;
        return obwod;
    }

// ---------------------------------------------------------------------------------------------

   Pieciokat::Pieciokat (double bok) : bok(bok) { }

    double Pieciokat::count_Pole()
    {
        pole = (pow(bok, 2) / 4) * sqrt(25 + (10 * sqrt(5)));
        pole = round(pole * 10.0) / 10.0;
        return pole;
    }

    double Pieciokat::count_Obwod()
    {
        obwod = bok * 5;
        return obwod;
    }

// ---------------------------------------------------------------------------------------------

        Prostokat::Prostokat(double bok1, double bok2) : Czworokat (bok1, bok1, bok2, bok2, 90) { }

    double Prostokat::count_Pole()
    {
        pole = bok1 * bok3;
        pole = round(pole * 10.0) / 10.0;
        return pole;
    }

// ---------------------------------------------------------------------------------------------
    Romb::Romb(double bok1, double kat) : Czworokat (bok1, bok1, bok1, bok1, kat) { }

    double Romb::count_Pole()
    {
        pole = pow(bok1, 2) * sin(kat*3.14159/180);
        pole = round(pole * 10.0) / 10.0;
        return pole;
    }
// ---------------------------------------------------------------------------------------------
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

// ---------------------------------------------------------------------------------------------
    
    Czworokat *decide_czworokat (double* boki, double kat) throw (string)
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
// ---------------------------------------------------------------------------------------------
    void Figura::print_info()
    {
        cout << "--------------\n" << get_name() << "\nPole = " << pole << "\nObwod = " << obwod << endl;
    }
// ---------------------------------------------------------------------------------------------
    Kolo::Kolo (double promien) : promien (promien) { }

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
// ---------------------------------------------------------------------------------------------
    Kwadrat::Kwadrat(double bok) : Czworokat (bok, bok, bok, bok, 90) { }

    double Kwadrat::count_Pole()
    {
        pole = pow(bok1, 2);
        pole = round(pole * 10.0) / 10.0;
        return pole;
    }
// ---------------------------------------------------------------------------------------------

int main(int argc, char const *argv[])
{
    
    string rodzaje_podanych_figur;

    if (argc > 1)
    {

            int j = 2;

            // wpisujemy dane o figurach

            rodzaje_podanych_figur = argv[1];

            istringstream iss (rodzaje_podanych_figur);

            Figura* figury[rodzaje_podanych_figur.length()];

            for (int i = 0; i < rodzaje_podanych_figur.length(); i++)
            {
                char litera = rodzaje_podanych_figur[i];

                // Zapewniamy ze wystarczy nam argumentow

                if (j >= argc || ( ( (j+4) >= argc) && (litera == 'c') ))
                {
                    cout << "Niewystarczajaca ilosc podanych argumentow!" << endl;
                    break;
                }

                switch (litera)
                {

                    // Kolo

                    case 'o':
                    {
                        double promien = atof(argv[j]);
                        j++;

                        if (promien <= 0.0)
                        {
                            cout << promien << " - Niedodatnia wartosc promienia!" << endl;
                        }

                        else
                        {
                            figury[i] = new Kolo(promien);
                        }

                        break;
                    }

                    // Czworokat

                    case 'c':
                    {
                        bool flag = true;
                        double boki [4];
                        double kat;
                        for (int k = 0; k < 4; k++)
                        {
                            boki[k] = atof(argv[j]);
                            if (boki[k] <= 0.0)
                            {
                                flag = false;
                            }
                            j++;
                        }
                        kat = atof(argv[j]);
                        j++;

                        // jak jakis bok jest niedodatni

                        if (flag != true)
                        {
                            cout << "Zle rozmiary bokow czworokata!\nCzworokat o bokach: "
                                    << boki[0]  << " " << boki[1] << " " << boki[2] <<  " " << boki[3] << " oraz kat o stopniach: " << kat
                            << " ma ujemna wartosc jednego (lub wiecej) boku (bokow)"<< endl;
                        }

                        // jak wszystko jest w porzadku z wartosciami bokow

                        else
                        {
                            try
                            {
                                figury[i] = decide_czworokat(boki, kat);
                            }

                            catch (string e)
                            {
                                cout << "Czworokat o bokach: "
                                        << boki[0] << " " << boki[1] << " " << boki[2] << " " << boki[3] << " oraz kacie o " << kat
                                        << " stopniach nie jest zaimplementowany w systemie!\n" <<
                                        "Czworokaty dostepne do zaimplementowania to:\nKwadrat, Romb, Prostokat" << endl;
                            }

                            catch (string e)
                            {
                                cout << "Podany zly kat!\n" << "Czworokat o bokach: "
                                        << boki[0]  << " " << boki[1] << " " << boki[2] << " " << boki[3] << " ma kat o wartosci " << kat
                                        << " stopni (jest mniejszy od 0 lub wiekszy od 180 stopni!" << endl;
                            }
                        }

                        break;
                    }

                    // Pieciokat

                    case 'p':
                    {
                        double bok = atof(argv[j]);
                        j++;
                        if (bok <= 0)
                        {
                            cout << bok << " - Niedodatnia wartosc boku!" << endl;
                        }
                        else
                        {
                            figury[i] = new Pieciokat(bok);
                        }
                        break;
                    }

                    // Szesciokat

                    case 's':
                    {
                        double bok = atof(argv[j]);
                        j++;
                        if (bok <= 0)
                        {
                            cout << bok << " - Niedodatnia wartosc boku!" << endl;
                        }
                        else
                        {
                            figury[i] = new Szesciokat(bok);
                        }
                        break;
                    }

                    default:
                    {
                        cout << litera << "- litera nie odpowiada zadanym figurom" << endl;
                    }
                }
            }

            for (int i = 0; i < j; i++)
            {
                Figura *figura = figury[i];
                figura->count_Obwod();
                figura->count_Pole();
                figura->print_info();
            }
    
    }
    else
    {
        cout << "Program wywoluje sie z argumentami!" << endl;
    }
	return 0;
}
