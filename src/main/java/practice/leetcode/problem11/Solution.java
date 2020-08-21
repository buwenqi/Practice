package practice.leetcode.problem11;

public class Solution {

    //solution1: force solution
    public int maxArea(int[] height) {
        int maxContain=0;
        for(int i=0;i<height.length;i++){
            for(int j=i+1;j<height.length;j++){
                int curContain=getContainer(i,j,height);
                if(curContain>maxContain){
                    maxContain=curContain;
                }
            }
        }
        return maxContain;
    }

    public int getContainer(int i,int j,int[] height){
        int count=Math.abs(j-i);
        int minHight=Math.min(height[i],height[j]);
        return count*minHight;
    }

    //solution2: 双指针，状态消解
    //短的那一个组成的其他解一定小于当前的组合的解，在寻找最大值的过程中可以不用考虑
    public int maxArea2(int[] height){
        int maxArea=0;
        int i=0;
        int j=height.length;
        while(i<j){
            int curArea=Math.min(height[i],height[j])*(j-i);
            maxArea=Math.max(maxArea,curArea);
            if(height[i]<height[j]){
                i++;
            }else{
                j--;
            }
        }
        return maxArea;
    }

    //dynamic programing
    //false method
    public int maxArea3(int[] height) {
        int[][] dp=new int[height.length][height.length];
        for(int i=0;i<height.length;i++){
            dp[i][i]=0;
        }

        //对角更新
        for(int num=height.length-1;num>0;num++){
            for(int i=0;i<num;i++){
                int j=i+height.length-num;
                int curContain=getContainer(i,j,height);
                dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
                dp[i][j]=Math.max(dp[i][j],curContain);
            }
        }
        return dp[0][height.length-1];
    }
}