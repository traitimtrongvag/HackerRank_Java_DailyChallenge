import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        sc.close();

        if (s.isEmpty()) {
            System.out.println(0);
            return;
        }

        String[] tokens = s.split("[^A-Za-z]+");
        System.out.println(tokens.length);

        for (String token : tokens) {
            System.out.println(token);
        }
    }
}