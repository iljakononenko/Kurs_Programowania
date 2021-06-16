#include <iostream>

using namespace std;

class Czworokat
{
public:
    double pole, obwod;
    double bok1, bok2, bok3, bok4, kat;

    Czworokat(double bok1, double bok2, double bok3, double bok4, double kat);

    double count_Obwod();

    void print_info();
};

Czworokat::Czworokat(double bok1, double bok2, double bok3, double bok4, double kat)
{
    this->bok1 = bok1;
    this->bok2 = bok2;
    this->bok3 = bok3;
    this->bok4 = bok4;
    this->kat = kat;
    this->pole = 0;
}

double Czworokat::count_Obwod()
{
    obwod = bok1 + bok2 + bok3 + bok4;
    return obwod;
}

void Czworokat::print_info()
{
    cout << "--------------\n"
         << "\nPole = " << pole << "\nObwod = " << obwod << endl;
}

int main()
{
    cout << "Works!";

    Czworokat *kwadrat = new Czworokat(4, 4, 4, 4, 90);

    kwadrat->count_Obwod();
    kwadrat->print_info();

    return 0;
}