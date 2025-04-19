import java.util.Scanner;

public class HackerRank_Java_04 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("================================");
        for(int i=0;i<3;i++){
            String s1=sc.next();
            String x=sc.next();
            while(x.length()<=2)
            {
                x="0"+x;
            }
            System.out.print(s1);
            int sp=15-s1.length();
            for(int j=0;j<sp;j++)System.out.print(" ");
            System.out.println(x);
            //Complete this line
        }
        System.out.println("================================");

    }
}
