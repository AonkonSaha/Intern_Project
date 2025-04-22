import java.util.Scanner;

public class HackerRank_Java_16 {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String A=sc.next();
        StringBuilder s=new StringBuilder(A);
        if(s.reverse().toString().equals(A))
        {
            System.out.println("Yes");
        }
        else System.out.println("No");
        /* Enter your code here. Print output to STDOUT. */

    }
}
