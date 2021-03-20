#include <iostream>
#include <string>
#include <sstream>

using namespace std;

class Wiersz_Trojkata_Pascala
{
	public:
		int *tablica;
		int wiersz_length;

		void print_caly_wiersz ()
		{
			cout << "Caly wiersz Trojkata Pascala: ";
			for (int i = 0; i < wiersz_length+1; i++)
			{
				cout << tablica[i] << " ";
			}
			cout << endl;
		}

		void print_indexed_argument (int i) throw (string)
		{
			if ((i < 0) || (i > wiersz_length)) throw (string) "Zly indeks! Liczba spoza zakresu!";
			cout << i << " - " << tablica[i] << endl;
		}

		int wspolczynnik (int m)
		{
			int result;
			int dzielnik = factorial(m) * factorial(wiersz_length - m);
			result = factorial(wiersz_length) / dzielnik;
			return result;
		}

		int factorial (int number)
		{
			int fact = 1;
			for (int i = 2; i <= number; i++)
			{
				fact = fact * i;
			}
			return fact;
		}

		Wiersz_Trojkata_Pascala (int n) throw (string)
		{
			if(n <= 0) throw (string) "Zly rozmiar tablicy!";
			wiersz_length = n;
			tablica = new int(n+1);
			for (int i = 0; i < wiersz_length+1; i++)
			{
				tablica[i] = wspolczynnik(i);
			}
		}
		~Wiersz_Trojkata_Pascala()
		{
			cout << "Zniszczono i posprzatano!" << endl;
		}
};

int main (int argc, char *argv[])
{
	Wiersz_Trojkata_Pascala *test;
	
	if(argc == 1)
    {
		cout << "\n\n! Program wywoluje sie z argumentami !";
    }
	else
    {
	   	int j;
		string arg = argv[1];
			
		istringstream iss (arg);
		iss >> j;

		if (!iss) 
		{
			cout << arg << ": nie jest liczba! Nie mozemy stworzyc tablice!" << endl;
			return 1;
		}
		else
		{
			try
			{
				test = new Wiersz_Trojkata_Pascala(j);
				test->print_caly_wiersz();
			}
			catch (string e)
			{
				cout << e << endl;
				return 1;
			}
			
		}

		for(int i = 2;i < argc; i++)
		{
			int j;
			string arg = argv[i];
			
			stringstream iss(arg);
			iss >> j;

			if (!iss) 
			{
				cout << arg << ": nie jest liczba" << endl;
			}
			else
			{
				try 
				{
					test->print_indexed_argument(j);
				}
				catch (string e)
				{
					cout << j << " - " << e << endl;
				}
			}
		}
	}
	delete test;
	return 0;
}