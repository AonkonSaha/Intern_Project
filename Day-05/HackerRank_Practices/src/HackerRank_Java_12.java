import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class HackerRank_Java_12 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double currency=sc.nextDouble();
        Locale locale = new Locale("en", "IN");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat currencyFormat2 = NumberFormat.getCurrencyInstance(Locale.CHINA);
        NumberFormat currencyFormat3 = NumberFormat.getCurrencyInstance(locale);
        NumberFormat currencyFormat4 = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        System.out.println("US: "+currencyFormat.format(currency));
        System.out.println("India: "+currencyFormat3.format(currency));
        System.out.println("China: "+currencyFormat2.format(currency));
        System.out.println("France: "+currencyFormat4.format(currency));

        sc.close();
    }
}
