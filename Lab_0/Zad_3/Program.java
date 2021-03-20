import java.util.*;

public class Program
{
	public static int div(int n)
	{
		int dzielnik = 1;
		for (int i = 2; i <= n; i++)
		{
			if (n % i == 0)
			{
				return n / i;
			}
		}
	}

	public static void main(String[] args) {
		int n = 0;
		for (int i = 0; i < args.length; i++)
		{
			try
			{
				n = Integer.parseInt(args[i]);
				System.out.println("Najwiekszy dzielnik liczby " + n + " jest " + div(n));
			}
			catch (NumberFormatException ex) {
				System.out.println(args[i] + " nie jest liczba calkowita");
			}
		}
	}
}