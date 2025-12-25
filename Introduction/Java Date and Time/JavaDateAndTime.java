import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

class Result {
    public static String findDay(int month, int day, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        return cal.getDisplayName(
                Calendar.DAY_OF_WEEK,
                Calendar.LONG,
                Locale.US
        ).toUpperCase();
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int month = sc.nextInt();
        int day = sc.nextInt();
        int year = sc.nextInt();

        sc.close();

        System.out.println(Result.findDay(month, day, year));
    }
}