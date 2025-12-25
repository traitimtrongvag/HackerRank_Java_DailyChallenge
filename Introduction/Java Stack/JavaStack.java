import java.util.Scanner;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            Stack<Character> stack = new Stack<>();
            boolean valid = true;

            for (char c : s.toCharArray()) {
                if (c == '(' || c == '{' || c == '[') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) {
                        valid = false;
                        break;
                    }
                    char top = stack.pop();
                    if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!stack.isEmpty()) valid = false;
            System.out.println(valid);
        }

        sc.close();
    }
}