import java.security.KeyPair;
import java.util.*;

public class JavaMap {
    public void action()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of testcases: ");
        int t=Integer.parseInt(sc.nextLine());
        Map<String,String> mp=new HashMap<>();
        while(t>0)
        {

            String name=sc.nextLine();
            String number=sc.nextLine();
//            System.out.println("->name: "+name);
            mp.put(name,number);
            t--;
        }
        List<AbstractMap.SimpleEntry<String, String>> list=new ArrayList<>();
        while(sc.hasNextLine())
        {
            String queryString=sc.nextLine();
            if(mp.containsKey(queryString))
            {
                list.add(new AbstractMap.SimpleEntry<>(queryString, mp.get(queryString)));
            }
            else
            {
                list.add(new AbstractMap.SimpleEntry<>(queryString, "NO"));
            }

        }
        for(AbstractMap.SimpleEntry<String, String> ans:list)
        {
//            System.out.println("op: "+ans.getKey()+" = "+ans.getValue());
            if(ans.getValue().equals("NO"))
            {
                System.out.println("Not found");
            }
            else
            {
                System.out.println(ans.getKey()+"="+ans.getValue());
            }
        }
    }
}
