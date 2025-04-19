public class HackerRank_Java_15 {
    public static String getSmallest(String s,int k)
    {
        String smallest=s.substring(0,k);
        for(int i=0;i<=s.length()-k;i++)
        {
//            System.out.println(s.substring(i,i+k));
            smallest=s.substring(i,i+k).compareTo(smallest)>0?smallest:s.substring(i,i+k);
        }
        return smallest;
    }
    public static String getLargest(String s,int k)
    {
        String largest=s.substring(0,k);
        for(int i=0;i<=s.length()-k;i++)
        {
            largest=s.substring(i,i+k).compareTo(largest)>0?s.substring(i,i+k):largest;
        }
        return largest;
    }

    public static String getSmallestAndLargest(String s, int k) {
        String smallest = getSmallest(s,k);
        String largest = getLargest(s,k);

        // Complete the function
        // 'smallest' must be the lexicographically smallest substring of length 'k'
        // 'largest' must be the lexicographically largest substring of length 'k'

        return smallest + "\n" + largest;
    }

    //This Section with Main function given by Judge
}
