package com.iljaknk;

class Wrong_array_size extends Exception {}

public class Wiersz_Trojkata_Pascala
{
    long tablica[];
    int wiersz_length;
    public Wiersz_Trojkata_Pascala (int n) throws Wrong_array_size
    {
        if (n < 0) throw new Wrong_array_size();
        wiersz_length = n;
        tablica = new long[wiersz_length + 1];
        for (int i = 0; i < tablica.length; i++)
        {
            tablica[i] = wspolczynnik(i);
        }
    }

    public String print_caly_wiersz ()
    {
        String wiersz = "";
        for (int i = 0; i < tablica.length; i++)
        {
            wiersz += tablica[i] + " ";
        }
        return wiersz;
    }

    public String print_caly_Trojkat (int n) throws Wrong_array_size
    {
        if (n <= 0) throw new Wrong_array_size();
        String trojkat_string = "";
        long c = 1;

        for(int i = 0; i <= n; i++)
        {
            for(int blk = 1; blk <= n - i; blk++)
                trojkat_string += " ";
            for(int j = 0; j <= i; j++)
            {
                if (j==0||i==0)
                {
                    c = 1;
                }
                else
                {
                    c = c * (i - j + 1) / j;
                }

                trojkat_string += " " + c;
            }
            trojkat_string += "\n";
        }

        return trojkat_string;
    }

    public int wspolczynnik (int m)
    {
        int result;
        int dzielnik = factorial(m) * factorial(wiersz_length - m);
        result = factorial(wiersz_length) / dzielnik;
        return result;
    }

    public int factorial (int number)
    {
        int fact = 1;
        for (int i = 2; i <= number; i++)
        {
            fact *= i;
        }
        return fact;
    }

}
