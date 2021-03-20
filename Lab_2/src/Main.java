import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Figura> figury = new ArrayList<>();

        figury.add(new Kolo(5));
        figury.add(new Kwadrat(5));
        figury.add(new Prostokat(5, 7));
        figury.add(new Romb(5, 30));
        figury.add(new Pieciokat(5));
        figury.add(new Szesciokat(5));

        if (args.length > 0)
        {

        }

        else
        {
            System.out.println("Program wywoluje sie z argumentami!");
        }




    }
}
