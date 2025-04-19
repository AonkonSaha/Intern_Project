import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HackerRank_Java_06 {

    public static void main(String []argh){
        Scanner in = new Scanner(System.in);
        int t=in.nextInt();
        for(int i=0;i<t;i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();
            List<Long> list = new ArrayList<Long>();
            for(int j=0;j<n;j++){
                long sum=a;
                for(int k=0;k<=j;k++) {
                    sum += (long) Math.pow(2, (double) k)*b;
                }
                list.add(sum);
            }
            for(Long val:list)
            {
                System.out.print(val+" ");
            }
            System.out.println();

        }
        in.close();
    }
}
