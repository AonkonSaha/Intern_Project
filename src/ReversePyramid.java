import java.util.Scanner;

public class ReversePyramid {

    public void action()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please Enter Row's Size:");
        int rows = sc.nextInt();
        System.out.println("Please Enter Col's Size:");
        int cols=sc.nextInt();
        for (int i = rows; i>0; i--) {
            for (int j = 1; j <= i; ++j) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
