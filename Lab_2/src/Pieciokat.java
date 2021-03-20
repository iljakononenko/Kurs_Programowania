public class Pieciokat extends Figura
{

    double bok;

    Pieciokat (double bok)
    {
        this.bok = bok;
    }

    @Override
    public double count_Pole()
    {
        pole = (Math.pow(bok, 2) / 4) * Math.sqrt(25 + (10 * Math.sqrt(5)));
        pole = Math.round(pole * 10.0) / 10.0;
        return pole;
    }

    @Override
    public double count_Obwod()
    {
        obwod = bok * 5;
        return obwod;
    }
}
