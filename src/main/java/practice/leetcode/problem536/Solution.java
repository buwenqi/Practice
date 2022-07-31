package practice.leetcode.problem536;

/**
 * Created by wenqi on 2021/11/28.
 */
class Solution{
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
        Solution solution=new Solution();
        TreeNode root=solution.constructTree(inputStr);
        System.out.println(root.val);
        System.out.println(root.left.val);
        System.out.println(root.right.val);
    }

    public TreeNode constructTree(String str){
        if(index>=str.length()) return null;

        int val=getRootVal(str);
        System.out.println("root:"+val);
        TreeNode root=new TreeNode(val);
        if(index>=str.length()) return root;

        //
        if(str.charAt(index)==')') {
            return root;
        }
        else{
            //skip (
            index++;
            System.out.println("before left:"+index);
            root.left=constructTree(str);
            //skip )
            index++;
            System.out.println("after left:"+index);
            if(index<str.length()){
                //skip (
                index++;
                System.out.println("before right:"+index);
                root.right=constructTree(str);
                //skip )
                index++;
                System.out.println("after right:"+index);
            }
            return root;
        }
    }

    public int getRootVal(String str){
        int result=0;
        System.out.println("root char:"+ str.charAt(index));
        while(index<str.length() && str.charAt(index)>'0' && str.charAt(index)<'9'){
            result=result*10+(str.charAt(index)-'0');
            index++;
        }
        return result;
    }
}