import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

enum Color {
    RED, GREEN
}

abstract class Tree {
    private int value;
    private Color color;
    private int depth;
    
    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }
    
    public int getValue() {
        return value;
    }
    
    public Color getColor() {
        return color;
    }
    
    public int getDepth() {
        return depth;
    }
    
    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {
    private ArrayList<Tree> children = new ArrayList<>();
    
    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }
    
    public void accept(TreeVis visitor) {
        visitor.visitNode(this);
        for (Tree child : children) {
            child.accept(visitor);
        }
    }
    
    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {
    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }
    
    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis {
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);
}

// === PHẦN CỦA BẠN BẮT ĐẦU TỪ ĐÂY ===
class SumInLeavesVisitor extends TreeVis {
    private int result = 0;
    
    public int getResult() {
        return result;
    }
    
    public void visitNode(TreeNode node) {
    }
    
    public void visitLeaf(TreeLeaf leaf) {
        result += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private long result = 1;
    private final int MOD = 1000000007;
    
    public int getResult() {
        return (int) result;
    }
    
    public void visitNode(TreeNode node) {
        if (node.getColor() == Color.RED) {
            result = (result * node.getValue()) % MOD;
        }
    }
    
    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == Color.RED) {
            result = (result * leaf.getValue()) % MOD;
        }
    }
}

class FancyVisitor extends TreeVis {
    private int nonLeafEvenSum = 0;
    private int greenLeafSum = 0;
    
    public int getResult() {
        return Math.abs(nonLeafEvenSum - greenLeafSum);
    }
    
    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 == 0) {
            nonLeafEvenSum += node.getValue();
        }
    }
    
    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == Color.GREEN) {
            greenLeafSum += leaf.getValue();
        }
    }
}

public class Solution {
    static int[] values;
    static Color[] colors;
    static Map<Integer, Set<Integer>> edges = new HashMap<>();
    
    public static Tree solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        values = new int[n];
        colors = new Color[n];
        
        for (int i = 0; i < n; i++) values[i] = sc.nextInt();
        for (int i = 0; i < n; i++) colors[i] = sc.nextInt() == 0 ? Color.RED : Color.GREEN;
        
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            
            if (!edges.containsKey(u)) {
                edges.put(u, new HashSet<Integer>());
            }
            edges.get(u).add(v);
            
            if (!edges.containsKey(v)) {
                edges.put(v, new HashSet<Integer>());
            }
            edges.get(v).add(u);
        }
        
        sc.close();
        
        if (n == 1) {
            return new TreeLeaf(values[0], colors[0], 0);
        }
        
        TreeNode root = new TreeNode(values[0], colors[0], 0);
        buildTree(root, 0);
        return root;
    }
    
    private static void buildTree(TreeNode parent, int parentIndex) {
        for (int childIndex : edges.get(parentIndex)) {
            edges.get(childIndex).remove(parentIndex);
            
            Tree child;
            if (edges.get(childIndex).isEmpty()) {
                child = new TreeLeaf(values[childIndex], colors[childIndex], parent.getDepth() + 1);
            } else {
                child = new TreeNode(values[childIndex], colors[childIndex], parent.getDepth() + 1);
                buildTree((TreeNode) child, childIndex);
            }
            parent.addChild(child);
        }
    }
    
    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        System.out.println(vis1.getResult());
        System.out.println(vis2.getResult());
        System.out.println(vis3.getResult());
    }
}