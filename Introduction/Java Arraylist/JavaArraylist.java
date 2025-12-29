import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            int d = scanner.nextInt();
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < d; j++) {
                row.add(scanner.nextInt());
            }
            list.add(row);
        }
        
        int q = scanner.nextInt();
        
        for (int i = 0; i < q; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            
            if (x > 0 && x <= list.size()) {
                ArrayList<Integer> inner = list.get(x - 1);
                if (y > 0 && y <= inner.size()) {
                    System.out.println(inner.get(y - 1));
                } else {
                    System.out.println("ERROR!");
                }
            } else {
                System.out.println("ERROR!");
            }
        }
        
        scanner.close();
    }
}