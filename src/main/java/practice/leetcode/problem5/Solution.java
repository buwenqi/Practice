package practice.leetcode.problem5;

import java.util.*;

/**
 * Created by wenqi on 2019/12/1.
 *
 * 描述：
 * Longest Palindromic Substring
 *
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 * Example 1:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * 思路：
 * 想法1：
 * 定义一个检测是不是回文字符串的方法checkPalindrome(int startIndex,int endIndex)
 * 每次仅检测一个长度len的字符串，从len:n->1,检测到是的直接返回
 * 此方法时间复杂度为n^3(检测的子串串个数为n*(n+1)/2,每个子串长度为k，其检测复杂度也为O(n)),
 * 最坏情况要len=1才结束，最好情况直接len=n返回
 * 想法2：
 * 统计相同字符的index,形成一个列表，比如上面的例子，b->[0,1],a->[1,2],d->[4],
 * 检测相同字符之间是否是回文字符，此思想和想法1相结合
 *
 * 解法参考：
 * 解法1：优化递归的多余计算，记录(i,j)是否是回文字符串
 * 正向计算，不用递归重复计算，计算公式为
 * map(i,i)=true
 * map(i,i+1)={true if str[i]==str[i+1],else false}
 * map(i,j)={true if str[i]==str[j]&&map(i+1,j-1)==true,else false}
 * 更新应该按对角线依次更新
 */
public class Solution {

    public static void main(String[] args){
        Solution solution=new Solution();
        System.out.println(solution.longestPalindrome2("cbbd"));
    }

    public String longestPalindrome(String s) {

        if(s==null|| "".equals(s)){
            return "";
        }

        int len=s.length();
        int curLen=len;
        while (curLen>1){
            boolean result=false;
            int startIndex=0;
            int endIndex=startIndex+curLen-1;
            for(;endIndex<len;startIndex++,endIndex++){
                result=isPalindrome(s,startIndex,endIndex);
                if(result){
                    break;
                }
            }
            if(result){
                return s.substring(startIndex,endIndex+1);
            }
            curLen--;
        }

        return s.substring(0,1);
    }

    /**
     *
     * @param str 待检测的字符串
     * @param startIndex 将要检测的字符子串开始下标
     * @param endIndex 将要检测字符子串结束下标
     * @return
     */
    public boolean isPalindrome(String str, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return true;
        }
        if (str.charAt(startIndex) != str.charAt(endIndex)) {
            return false;
        } else {
            return isPalindrome(str, startIndex + 1, endIndex - 1);
        }
    }

    public String longestPalindrome2(String s) {

        if(s==null|| "".equals(s)){
            return "";
        }

        int len=s.length();
        //该数组代码从i到j
        boolean[][] palinMap=new boolean[len][len];
        //对角线更新
        for(int turn=0;turn<len;turn++) {
            for (int i = 0; i < len-turn; i++) {
                int j = i + turn;
                if(i==j){
                    palinMap[i][j]=true;
                }else if(j-i==1 &&s.charAt(i)==s.charAt(j)){
                    palinMap[i][j]=true;
                }else if(s.charAt(i)==s.charAt(j) && i+1<=j-1 && palinMap[i+1][j-1]){
                    palinMap[i][j]=true;
                }else{
                    palinMap[i][j]=false;
                }
            }
        }

        int curLen=len;
        while (curLen>1){
            boolean result=false;
            int startIndex=0;
            int endIndex=startIndex+curLen-1;
            for(;endIndex<len;startIndex++,endIndex++){
                result=palinMap[startIndex][endIndex];
                if(result){
                    break;
                }
            }
            if(result){
                return s.substring(startIndex,endIndex+1);
            }
            curLen--;
        }

        return s.substring(0,1);
    }
}
