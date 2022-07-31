package practice.leetcode.problem536;

/**
 * Created by wenqi on 2021/11/28.
 */
class Solution2{
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
        Solution2 solution=new Solution2();
        TreeNode root=solution.constructTree(inputStr);
        System.out.println(root.val);
        System.out.println(root.left.val);
        System.out.println(root.right.val);
    }

    public TreeNode constructTree(String str){
        if(str==null || str.length()==0) return null;

        int firstLeftBraceIndex=str.indexOf("(");
        if(firstLeftBraceIndex<=0) return null;

        TreeNode root=new TreeNode(Integer.valueOf(str.substring(0,firstLeftBraceIndex)));

        int bracesCount=0;
        boolean leftMark=true;
        int rightFirstIndex=firstLeftBraceIndex;
        for(int i=firstLeftBraceIndex;i<str.length();i++){
            if(str.charAt(i)=='(') bracesCount++;
            else if(str.charAt(i)==')') bracesCount--;

            if(bracesCount==0 && leftMark){
                String leftStr=str.substring(firstLeftBraceIndex+1,i);
                System.out.println(leftStr);
                root.left=constructTree(leftStr);
                leftMark=false;
                rightFirstIndex=i+1;
            }else if(bracesCount==0 && !leftMark){
                String rightStr=str.substring(rightFirstIndex+1,i);
                System.out.println(rightStr);
                root.right=constructTree(rightStr);
            }
        }
        return root;
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
