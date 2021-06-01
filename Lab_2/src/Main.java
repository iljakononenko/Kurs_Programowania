import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Figura> figury = new ArrayList<>();
        String rodzaje_podanych_figur;

        if (args.length > 0)
        {

            int j = 1;

            // wpisujemy dane o figurach

            rodzaje_podanych_figur = args[0];

            for (int i = 0; i < rodzaje_podanych_figur.length(); i++)
            {
                char litera = rodzaje_podanych_figur.charAt(i);

                // Zapewniamy ze wystarczy nam argumentow

                if (j >= args.length || ( ( (j+4) >= args.length) && (litera == 'c') ))
                {
                    System.out.println("Niewystarczajaca ilosc podanych argumentow!");
                    break;
                }

                switch (litera)
                {

                    // Kolo

                    case 'o':
                    {
                        double promien = Double.parseDouble(args[j]);
                        j++;

                        if (promien <= 0.0)
                        {
                            System.out.println(promien + " - Niedodatnia wartosc promienia!");
                        }

                        else
                        {
                            figury.add(new Kolo(promien));
                        }

                        break;
                    }

                    // Czworokat

                    case 'c':
                    {
                        boolean flag = true;
                        double[] boki = new double[4];
                        double kat;
                        for (int k = 0; k < 4; k++)
                        {
                            boki[k] = Double.parseDouble(args[j]);
                            if (boki[k] <= 0.0)
                            {
                                flag = false;
                            }
                            j++;
                        }
                        kat = Double.parseDouble(args[j]);
                        j++;

                        // jak jakis bok jest niedodatni

                        if (flag != true)
                        {
                            System.out.println("Zle rozmiary bokow czworokata!\nCzworokat o bokach: "
                                    + boki[0]  + " " + boki[1] + " " + boki[2] + " " + boki[3] + " oraz kat o stopniach: " + kat
                            + " ma ujemna wartosc jednego (lub wiecej) boku (bokow)");
                        }

                        // jak wszystko jest w porzadku z wartosciami bokow

                        else
                        {
                            try
                            {
                                figury.add(Czworokat.decide_czworokat(boki, kat));
                            }

                            catch (Zle_boki_czworokata e)
                            {
                                System.out.println("Czworokat o bokach: "
                                        + boki[0]  + " " + boki[1] + " " + boki[2] + " " + boki[3] + " oraz kacie o " + kat
                                        + " stopniach nie jest zaimplementowany w systemie!\n" +
                                        "Czworokaty dostepne do zaimplementowania to:\nKwadrat, Romb, Prostokat");
                            }

                            catch (Zly_kat_czworokata e)
                            {
                                System.out.println("Podany zly kat!\n" +"Czworokat o bokach: "
                                        + boki[0]  + " " + boki[1] + " " + boki[2] + " " + boki[3] + " ma kat o wartosci " + kat
                                        + " stopni (jest mniejszy od 0 lub wiekszy od 180 stopni!");
                            }
                        }

                        break;
                    }

                    // Pieciokat

                    case 'p':
                    {
                        double bok = Double.parseDouble(args[j]);
                        j++;
                        if (bok <= 0)
                        {
                            System.out.println(bok + " - Niedodatnia wartosc boku!");
                        }
                        else
                        {
                            figury.add(new Pieciokat(bok));
                        }
                        break;
                    }

                    // Szesciokat

                    case 's':
                    {
                        double bok = Double.parseDouble(args[j]);
                        j++;
                        if (bok <= 0)
                        {
                            System.out.println(bok + " - Niedodatnia wartosc boku!");
                        }
                        else
                        {
                            figury.add(new Szesciokat(bok));
                        }
                        break;
                    }

                    default:
                    {
                        System.out.println(litera + "- litera nie odpowiada zadanym figurom");
                    }
                }
            }

            for (int i = 0; i < figury.size(); i++)
            {
                Figura figura = figury.get(i);
                figura.count_Obwod();
                figura.count_Pole();
                figura.print_info();
            }

        }

        else
        {
            System.out.println("Program wywoluje sie z argumentami!");
        }

    }

}
