class Zle_boki_czworokata extends Exception {};
class Zly_kat_czworokata extends Exception {};

abstract public class Czworokat extends Figura
{

    double bok1, bok2, bok3, bok4, kat;

    Czworokat (double bok1, double bok2, double bok3, double bok4, double kat)
    {
        this.bok1 = bok1;
        this.bok2 = bok2;
        this.bok3 = bok3;
        this.bok4 = bok4;
        this.kat = kat;
    }

    @Override
    public double count_Obwod()
    {
        obwod = bok1 + bok2 + bok3 + bok4;
        return obwod;
    }

    public static Czworokat decide_czworokat (double[] boki, double kat) throws Zle_boki_czworokata, Zly_kat_czworokata
    {

        // sprawdzamy czy kat jest poprawny

        if (kat <= 0.0 || kat >= 180.0)
        {
            throw new Zly_kat_czworokata();
        }

        // wszystkie boki sa rowne

        if (boki[0] == boki[1] && boki[1] == boki[2] && boki[2] == boki[3])
        {
            if (kat == 90)
            {
                return new Kwadrat(boki[0]);
            }
            else
            {
                return new Romb(boki[0], kat);
            }
        }

        // czy jest prostokatem

        else if (boki[0] == boki[1] && boki[2] == boki[3])
        {
            return new Prostokat(boki[0], boki[2]);
        }

        else if (boki[0] == boki[3] && boki[1] == boki[2])
        {
            return new Prostokat(boki[0], boki[2]);
        }

        else if (boki[0] == boki[2] && boki[1] == boki[3])
        {
            return new Prostokat(boki[0], boki[3]);
        }

        throw new Zle_boki_czworokata();
    }
}
