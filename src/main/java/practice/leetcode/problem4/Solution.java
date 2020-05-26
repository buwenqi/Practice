package practice.leetcode.problem4;

/**
 * Created by wenqi on 2019/11/9.
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * 解题思路：
 * 首先有序数组的查找问题应该首先想到二分法，该题的难点在于如何使用二分法查找什么
 * nums1:0,1,2,i-1,\i……，len_nums1-1
 * nums2:0,1,2,j-1,\j……，len_nums2-1
 * 找到中位数的其实就是找到划分i,j的一条线
 * i,j符合i+j=(len_nums1+len_nums2+1)/2,这样可以将数组划分成等额的两份
 * (而加1的是在总数为奇数的时候能够使得左边的多一个)
 * 并且num1[i-1]<=num2[j],num2[j-1]<=num1[i]
 * 如果符合这个条件，则可以返回结果，
 * 总数为偶数的情况下，result=(max(num1[i-1],num2[j-1])+min(num1[i],num2[j]))/2
 * 总数为奇数的情况下，result=max(num1[i-1],num2[j-1])
 *
 * 那么如何找这个i,j呢？我们可以先满足条件
 * j=(len_nums1+len_nums2+1)/2-i,(此处为了保证j不为负数，需让nums1的长度小于nums2的长度)
 * 然后寻找另外两个条件的i就可以了，j可以算出
 * 那么如何寻找i呢？二分法呀
 * 如果num1[i-1]>num2[j],说明i太大，需要从cur_start~i-1从新选择
 * 如果num2[j-1]>num1[i]，说明i太小了，需要从i+1~cur_end中选择一个
 *
 * 至于边界情况，比如i等于0时，此时i左边无值，要验证是否num1[i-1]<=num2[j]，
 * num1[i-1]特殊处理下，直接返回一个最小值即可
 * i等于len_num1时，仅需返回一个最大值即可
 */
public class Solution {

    public static void main(String[] args){
        int[] nums1=new int[]{1,3,8,9,15};
        int[] nums2=new int[]{7,11,18,19,21,25};
        System.out.println(findMedianSortedArrays(nums1,nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length){
            //此时保证num1的长度小于num2的长度
            return findMedianSortedArrays(nums2,nums1);
        }
        int len1=nums1.length;
        int len2=nums2.length;
        int startNums1=0;
        int endNums1=len1;
        while (true){
            int i=(startNums1+endNums1)/2;
            int j=(len1+len2+1)/2-i;
            int leftMax1=i==0?Integer.MIN_VALUE:nums1[i-1];
            int leftMax2=j==0?Integer.MIN_VALUE:nums2[j-1];
            int rightMin1=i==len1?Integer.MAX_VALUE:nums1[i];
            int rightMin2=j==len2?Integer.MAX_VALUE:nums2[j];
            if(leftMax1<=rightMin2&&leftMax2<=rightMin1){
                //符合条件
                if((len1+len2)%2==0){
                    return (Math.max(leftMax1,leftMax2)+Math.min(rightMin1,rightMin2))/2.0d;
                }else{
                    return Math.max(leftMax1,leftMax2);
                }
            }else if(leftMax1>rightMin2){
                endNums1=i-1;
            }else if(rightMin1<leftMax2){
                startNums1=i+1;
            }
        }

    }
}
