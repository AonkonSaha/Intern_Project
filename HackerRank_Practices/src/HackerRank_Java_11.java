import java.util.Calendar;

public class HackerRank_Java_11 {
    /*
     * Complete the 'findDay' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER month
     *  2. INTEGER day
     *  3. INTEGER year
     */

    public static String findDay(int month, int day, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day); // Month is 0-indexed in Calendar

        String[] days = {
                "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY",
                "THURSDAY", "FRIDAY", "SATURDAY"
        };

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 = Sunday, 2 = Monday, ...
        return days[dayOfWeek - 1];
    }

  //This Section Code with Main Function Given By Judge
}
