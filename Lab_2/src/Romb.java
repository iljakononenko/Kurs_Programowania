public class Romb extends Czworokat
{

    Romb(double bok1, double kat)
    {
        super(bok1, bok1, bok1, bok1, kat);
    }

    @Override
    public double count_Pole()
    {
        pole = Math.pow(bok1, 2) * Math.sin(Math.toRadians(kat));
        pole = Math.round(pole * 10.0) / 10.0;
        return pole;
    }

    @Override
    public String get_name()
    {
        return "Romb";
    }
}
