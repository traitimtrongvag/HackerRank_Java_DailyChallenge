import java.util.Scanner;

class UsernameValidator {
    public static final String regularExpression = "^[A-Za-z][A-Za-z0-9_]{7,29}$";
}

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        while (n-- > 0) {
            String username = sc.nextLine();
            if (username.matches(UsernameValidator.regularExpression)) {
                System.out.println("Valid");
            } else {
                System.out.println("Invalid");
            }
        }

        sc.close();
    }
}