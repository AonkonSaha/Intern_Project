import java.util.Scanner;

public class HackerRank_Java_07 {

    public static void main(String []argh)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        // System.out.println(Byte.MIN_VALUE);
        for (int i = 0; i < t; i++) {

            String s = sc.next();
            try {
                Long tempVal = Long.parseLong(s);
            } catch (Exception ex) {
                System.out.println(s + " can't be fitted anywhere.");
                continue;
            }

            System.out.println(s + " can be fitted in:");
            if (s.contains(".")) {
                double x =  Double.parseDouble(s);
                if (x >= Float.MIN_VALUE && x <= Float.MAX_VALUE) System.out.println("* float");
                if (x >= Double.MIN_VALUE && x <= Double.MAX_VALUE) System.out.println("* double");
                continue;
            }
//            System.out.print(s+" ->");
            long x =   Long.parseLong(s);
//            System.out.println( Long.parseLong(s));
            if (x >= Byte.MIN_VALUE && x <= Byte.MAX_VALUE) System.out.println("* byte");
            if (x >= Short.MIN_VALUE && x <= Short.MAX_VALUE) System.out.println("* short");
            if (x >= Integer.MIN_VALUE && x <= Integer.MAX_VALUE) System.out.println("* int");
            if (x >= Long.MIN_VALUE && x <= Long.MAX_VALUE) System.out.println("* long");

            //Complete the code

        }
    }
}
