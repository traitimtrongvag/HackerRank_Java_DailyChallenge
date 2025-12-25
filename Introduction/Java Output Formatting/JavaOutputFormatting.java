import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("================================");

        while (sc.hasNext()) {
            String s = sc.next();
            int x = sc.nextInt();
            System.out.printf("%-15s%03d%n", s, x);
        }

        System.out.println("================================");

        sc.close();
    }
}