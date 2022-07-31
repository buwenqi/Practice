package practice.test2;
import sun.awt.image.ImageWatched;

import java.util.*;
/**
 * Created by wenqi on 2022/1/8.
 */
public class Tree {

    public static void main(String[] args){
        TreeNode root=new TreeNode(1);
        TreeNode root2=new TreeNode(2);
        TreeNode root3=new TreeNode(3);
        TreeNode root4=new TreeNode(4);
        TreeNode root5=new TreeNode(5);
        root.left=root2;
        root2.left=root3;
        root2.right=root4;
        root.right=root5;
        Tree tree=new Tree();
        tree.levelOrder(root);
    }

    static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val){
            this.val=val;
        }
    }

    public void preOrder(TreeNode root){
        if(root==null) return;
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode curNode=stack.pop();
            System.out.print(curNode.val+",");
            if(curNode.right!=null) stack.push(curNode.right);
            if(curNode.left!=null) stack.push(curNode.left);
        }
    }

    public void inOrder(TreeNode root){
        if(root==null) return;
        Stack<TreeNode> stack=new Stack<>();
        TreeNode curNode=root;
        while (!stack.isEmpty() || curNode!=null){
            while (curNode!=null){
                stack.push(curNode);
                curNode=curNode.left;
            }
            curNode=stack.pop();
            System.out.print(curNode.val+",");
            if(curNode.right!=null){
                //转向右子树
                curNode=curNode.right;
            }else{
                //当前树遍历完了，需要弹栈处理
                curNode=null;
            }
        }
    }

    public void postOrder(TreeNode root){
        if(root==null) return;
        Stack<TreeNode> stack=new Stack<>();
        TreeNode preNode=null;
        TreeNode curNode=root;
        while (!stack.isEmpty() || curNode!=null){
            while (curNode!=null){
                stack.push(curNode);
                curNode=curNode.left;
            }

            TreeNode peekNode=stack.peek();
            if(peekNode.right==null || peekNode.right==preNode){
                TreeNode popNode=stack.pop();
                preNode=popNode;
                //下一次使用栈中操作
                curNode=null;
                System.out.print(popNode.val+",");
            }else{
                //右边未被访问过
                curNode=peekNode.right;
            }
        }
    }

    public void levelOrder(TreeNode root){
        if(root==null) return;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode curNode=queue.poll();
            System.out.print(curNode.val+",");
            if(curNode.left!=null) queue.offer(curNode.left);
            if(curNode.right!=null) queue.offer(curNode.right);
        }
    }
}
