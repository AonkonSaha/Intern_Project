import java.util.Scanner;

public class Palindrome {

    public void action()
    {
        while(true)
        {

            System.out.println("Enter the string: ");
            Scanner sc=new Scanner(System.in);
            String s=sc.nextLine();
            if(s.equals("n"))
            {
                break;
            }
            StringBuilder ans=new StringBuilder();
            for(char ch:s.toCharArray())
            {
                if((ch>='A' && ch<='Z') ||  (ch>='a' && ch<='z' ))
                {
                    ans.append(ch);
                }
            }
            String temp=ans.toString().toLowerCase();
            if(ans.reverse().toString().toLowerCase().equals(temp))
            {
                System.out.println("Yes,It is Palindrome!");
            }
            else
            {
                System.out.println("No,It is not Palindrome!");
            }
            System.out.println("If you don't want to continue, press 'n");

        }

       // System.out.println(ans.toString());

    }
}
