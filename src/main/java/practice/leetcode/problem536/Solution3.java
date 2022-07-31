package practice.leetcode.problem536;

/**
 * Created by wenqi on 2021/11/28.
 */
import java.util.*;
public class Solution3{
    class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val){
            this.val=val;
        }
    }

    static int index;
    public static void main(String[] args){
        String inputStr="4(2(3)(1))(6(5))";
        index=0;
        Solution3 solution=new Solution3();
        TreeNode root=solution.constructTree(inputStr);
        System.out.println(root.val);
        System.out.println(root.left.val);
        System.out.println(root.right.val);
    }

    public TreeNode constructTree(String str){
        Stack<TreeNode> stack=new Stack<>();
        for(int i=0;i<str.length();i++){
            if(isNum(str.charAt(i))){
                int start=i;
                while(i<str.length() && isNum(str.charAt(i))){
                    i++;
                }
                String curNumStr=str.substring(start,i);
                //i处于最后当前数字的后一个位置，需要退一格，进步需要通过for循环来完成
                i--;
                System.out.println(curNumStr);
                TreeNode curNode=new TreeNode(Integer.valueOf(curNumStr));
                if(!stack.isEmpty()){
                    // determine whether currentNode is left child or right child through topNode
                    TreeNode topNode=stack.peek();
                    if(topNode.left==null) topNode.left=curNode;
                    else topNode.right=curNode;
                }
                stack.push(curNode);

            }else if(str.charAt(i)==')'){
                //pop the nodes which already without left and right node
                stack.pop();
            }
        }

        return stack.pop();
    }


    public boolean isNum(char curChar){
        if(curChar>'0' && curChar<'9'){
            return true;
        }else{
            return false;
        }
    }
}
