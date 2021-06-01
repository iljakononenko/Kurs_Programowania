abstract public class Figura implements Obliczenia
{
    double pole, obwod;

    @Override
    public void print_info()
    {
        System.out.println("--------------\n" + get_name() + "\nPole = " + pole + "\nObwod = " + obwod);
    }
}
