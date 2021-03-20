class Wrong_index extends Exception {}
class Wrong_array_size extends Exception {}

public class Wiersz_Trojkata_Pascala
{
    int tablica[];
    int wiersz_length;
    public Wiersz_Trojkata_Pascala (int n) throws Wrong_array_size
    {
        if (n <= 0) throw new Wrong_array_size();
        wiersz_length = n;
        tablica = new int[wiersz_length + 1];
        for (int i = 0; i < tablica.length; i++)
        {
            tablica[i] = wspolczynnik(i);
        }
    }

    public void print_caly_wiersz ()
    {
        System.out.print("Caly wiersz Trojkata Pascala: ");
        for (int i = 0; i < tablica.length; i++)
        {
            System.out.print(tablica[i] + " ");
        }
        System.out.println();
    }

    public void print_indexed_argument (int i) throws Wrong_index
    {
        if ((i < 0) || (i > tablica.length)) throw new Wrong_index();
        System.out.println(i + " - " + tablica[i]);
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

    protected void finalize() throws Throwable
    {
        System.out.println("Zniszczono i posprzatano!");
    }
}
