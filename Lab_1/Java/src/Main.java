public class Main {

    public static void main(String[] args) {

        int zmienna;
        Wiersz_Trojkata_Pascala test;

        // jesli nie podano zadnego argumentu, skoczymy do else (wiersz 63)

        if (args.length > 0)
        {
            try
            {
                zmienna = Integer.parseInt(args[0]);

                // probujemy stworzyc tablice podanego wiersza trojkata Pascala

                try
                {
                    test = new Wiersz_Trojkata_Pascala(zmienna);
                    test.print_caly_wiersz();

                    for (int i = 1; i < args.length; i++)
                    {

                        //probujemy przekonwertowac zmienna do typu int

                        try
                        {
                            zmienna = Integer.parseInt(args[i]);

                            // probujemy wypisac wartosc wiersza podanego indeksu (argumentu)

                            try
                            {
                                test.print_indexed_argument(zmienna);
                            }
                            catch (Wrong_index e)
                            {
                                System.out.println(zmienna + " - Zly indeks! Liczba spoza zakresu tablicy!");
                            }

                        }
                        catch (NumberFormatException ex) {
                            System.out.println(args[i] + " nie jest liczba calkowita! ");
                        }
                    }
                }
                catch (Wrong_array_size e)
                {
                    System.out.println(zmienna + " - Zly rozmiar tablicy!");
                }

            }
            catch (NumberFormatException ex)
            {
                System.out.println(args[0] + " - nie jest liczba calkowita! Nie mozemy stworzyc tablicy!");
            }


        }

        else
        {
            System.out.println("Program wywoluje sie z argumentami!");
        }
    }
}
