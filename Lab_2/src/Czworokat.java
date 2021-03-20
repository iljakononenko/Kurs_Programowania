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
}
