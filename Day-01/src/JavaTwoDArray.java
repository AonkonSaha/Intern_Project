import java.util.Arrays;
import java.util.Scanner;

public class JavaTwoDArray {

    public void action()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int n=sc.nextInt();
        System.out.println("Enter the number of cols: ");
        int m=sc.nextInt();
        int[][] arr=new int[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                int num=sc.nextInt();
                arr[i][j]=num;
            }
        }
//        System.out.println(Arrays.deepToString(arr));
        int ans=0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                int sum=0;
                for(int k=j,p=1;k<m && p<=3;k++,p++)
                {
                    sum+=arr[i][k];
                }
                if(j+1<m && i+1<n)sum+=arr[i+1][j+1];
                for(int k=j,p=1,r=i+2;k<m && r<n && p<=3;k++,p++)
                {
                    sum+=arr[r][k];
                }
                ans=Math.max(ans,sum);

            }
        }
        System.out.println(ans);
    }
}
