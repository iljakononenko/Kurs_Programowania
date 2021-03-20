public class Romb extends Czworokat
{

    Romb(double bok1, double bok2, double bok3, double bok4, double kat)
    {
        super(bok1, bok2, bok3, bok4, kat);
    }

    @Override
    public double count_Pole()
    {
        pole = Math.pow(bok1, 2) * Math.sin(Math.toRadians(kat));
        pole = Math.round(pole * 10.0) / 10.0;
        return pole;
    }

}
