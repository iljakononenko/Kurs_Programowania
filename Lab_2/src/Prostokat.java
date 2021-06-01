public class Prostokat extends Czworokat
{

    Prostokat(double bok1, double bok2)
    {
        super(bok1, bok1, bok2, bok2, 90);
    }

    @Override
    public double count_Pole()
    {
        pole = bok1 * bok3;
        pole = Math.round(pole * 10.0) / 10.0;
        return pole;
    }

    @Override
    public String get_name()
    {
        return "Prostokat";
    }
}
