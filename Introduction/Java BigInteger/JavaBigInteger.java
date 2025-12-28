import java.util.Scanner;
import java.math.BigInteger;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    BigInteger n = sc.nextBigInteger();
    sc.nextLine();
    BigInteger m = sc.nextBigInteger();

    System.out.println(n.add(m));
    System.out.println(n.multiply(m));
  }
}