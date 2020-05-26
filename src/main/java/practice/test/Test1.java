package practice.test;


import java.util.*;

/**
 * Created by wenqi on 2020/1/25.
 */
public class Test1 {
    public static void main(String[] args){
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        TreeNode node3=new TreeNode(3);
        node3.left=new TreeNode(4);
        node3.right=new TreeNode(5);
        root.right=node3;
        System.out.println(Arrays.toString(encodeTree(root).split(",")));
        Queue<String> queue=new LinkedList<>();
        Stack<String> stack=new Stack<>();
        Scanner scanner=new Scanner(System.in);
        System.out.println(scanner.nextLine());
        int result1=scanner.nextInt();
        int result2=scanner.nextInt();
        String line=scanner.nextLine();
        String line2=scanner.nextLine();
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(line);
        System.out.println(line2);

    }

    public static String encodeTree(TreeNode root){
        if(root==null){
            return "#,";
        }
        StringBuilder builder=new StringBuilder(root.val+",");
        builder.append(encodeTree(root.left));
        builder.append(encodeTree(root.right));
        return builder.toString();

    }
}

class TreeNode{
    public int val;
    public TreeNode left,right;
    public TreeNode(){}
    public TreeNode(int val){
        this.val=val;
    }
}
