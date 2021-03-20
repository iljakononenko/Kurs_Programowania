public class Kwadrat extends Czworokat
{

    Kwadrat(double bok)
    {
        super(bok, bok, bok, bok, 90);
    }

    @Override
    public double count_Pole()
    {
        pole = Math.pow(bok1, 2);
        pole = Math.round(pole * 10.0) / 10.0;
        return pole;
    }

}
