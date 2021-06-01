public class Szesciokat extends Figura
{

    double bok;

    Szesciokat (double bok)
    {
        this.bok = bok;
    }

    @Override
    public double count_Pole()
    {
        pole = (3 * Math.pow(bok, 2) * Math.sqrt(3)) / 2;
        pole = Math.round(pole * 10.0) / 10.0;
        return pole;
    }

    @Override
    public double count_Obwod()
    {
        obwod = bok * 6;
        return obwod;
    }

    @Override
    public String get_name()
    {
        return "Szesciokat";
    }
}
