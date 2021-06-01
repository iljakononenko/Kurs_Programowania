public class Kolo extends Figura
{
    double promien;

    Kolo (double promien)
    {
        this.promien = promien;
    }

    @Override
    public double count_Pole()
    {
        pole = Math.pow(promien, 2) * Math.PI;
        pole = Math.round(pole * 10.0) / 10.0;
        return pole;
    }

    @Override
    public double count_Obwod()
    {
        obwod = 2 * Math.PI * promien;
        obwod = Math.round(obwod * 10.0) / 10.0;
        return obwod;
    }

    @Override
    public String get_name()
    {
        return "Kolo";
    }
}
