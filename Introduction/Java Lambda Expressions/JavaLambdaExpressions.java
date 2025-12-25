import java.io.*;
import java.util.*;

interface PerformOperation {
    boolean check(int a);
}

class MyMath {
    public static boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }
    
    public static PerformOperation isOdd() {
        return new PerformOperation() {
            public boolean check(int n) {
                return n % 2 != 0;
            }
        };
    }
    
    public static PerformOperation isPrime() {
        return new PerformOperation() {
            public boolean check(int n) {
                if (n < 2) return false;
                if (n == 2) return true;
                if (n % 2 == 0) return false;
                for (int i = 3; i * i <= n; i += 2) {
                    if (n % i == 0) return false;
                }
                return true;
            }
        };
    }
    
    public static PerformOperation isPalindrome() {
        return new PerformOperation() {
            public boolean check(int n) {
                String str = Integer.toString(n);
                String reversed = new StringBuilder(str).reverse().toString();
                return str.equals(reversed);
            }
        };
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        MyMath ob = new MyMath();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        PerformOperation op;
        boolean ret = false;
        String ans = null;
        
        while (T-- > 0) {
            String s = br.readLine().trim();
            StringTokenizer st = new StringTokenizer(s);
            int ch = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            
            if (ch == 1) {
                op = MyMath.isOdd();
                ret = ob.checker(op, num);
                ans = ret ? "ODD" : "EVEN";
            } else if (ch == 2) {
                op = MyMath.isPrime();
                ret = ob.checker(op, num);
                ans = ret ? "PRIME" : "COMPOSITE";
            } else if (ch == 3) {
                op = MyMath.isPalindrome();
                ret = ob.checker(op, num);
                ans = ret ? "PALINDROME" : "NOT PALINDROME";
            }
            System.out.println(ans);
        }
    }
}