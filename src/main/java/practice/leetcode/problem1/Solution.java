package practice.leetcode.problem1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wenqi on 2019/8/25.
 * Description:
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 *
 * Related:
 * Hash,Search
 */
public class Solution {

    public static void main(String[] args){
        int result[]=new Solution().twoSum(new int[]{2,7,11,15,16,18},26);
        System.out.println(Arrays.toString(result));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }

        for(int i=0;i<nums.length;i++){
            int leftValue=target-nums[i];
            if(map.containsKey(leftValue)&&map.get(leftValue)!=i){
                return new int[]{i,map.get(leftValue)};
            }
        }
        return null;
    }

    public int find(int findValue,int[]nums,int start,int end){
        while(start<=end &&start<nums.length&&end<nums.length) {
            int mid=(start+end)/2;
            if (nums[mid] < findValue) {
                start = mid + 1;
            }else if(nums[mid]>findValue){
                end=mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}
