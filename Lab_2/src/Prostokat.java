public class Prostokat extends Czworokat
{

    Prostokat(double bok1, double bok2, double bok3, double bok4)
    {
        super(bok1, bok2, bok3, bok4, 90);
    }

    @Override
    public double count_Pole()
    {
        pole = bok1 * bok3;
        pole = Math.round(pole * 10.0) / 10.0;
        return pole;
    }

}
