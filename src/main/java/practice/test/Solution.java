package practice.test;
import java.util.*;

/**
 * Created by wenqi on 2020/2/5.
 */
public class Solution {
    int[] count;
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        count = new int[n];
        List<Integer> res = new ArrayList<>();
        int[] pos = new int[n];
        for(int i = 0; i < n; i++) {
            pos[i] = i;
        }
        sort(nums,0,n-1,pos);
        for(int num : count) {
            res.add(num);
        }
        return res;
    }

    public void sort(int[] nums, int left, int right, int[] pos) {
        if(left < right) {
            int mid = (left + right) >> 1;
            sort(nums,left,mid,pos);
            sort(nums,mid+1,right,pos);
            merge(nums,left,right,mid,pos);
        }
    }
    // 位置数组，交换的时候就交换位置数组
    public void merge(int[] nums, int left, int right, int mid, int[] pos) {
        int[] temp = new int[right - left + 1];
        int t = 0, l = left, r = mid + 1;
        while(l <= mid && r <= right) {
            // 这样，保证r左边到mid都是小于它的数
            //如果nums[pos[l]] <= nums[pos[r]]，说明右边部分，即mid+1到r-1的值都小于num[pos[l]]
            //根据值归并，变动pos位置
            if(nums[pos[l]] <= nums[pos[r]]) {

                count[pos[l]] += r - (mid + 1);  // 将个数加入到当前位置
                temp[t++] = pos[l++];
            } else {
                //否则的话，应该继续向右走
                temp[t++] = pos[r++];
            }
        }
        //如果到底了，l不为空的话，则说明剩下的l都大于右边（r-1到mid+1的值）
        while(l <= mid) {
            //mid+1到r-1的值一定是小于pos[l]位置的值
            count[pos[l]] += r - (mid + 1);
            //变动pos位置
            temp[t++] = pos[l++];
        }
        while(r <= right) {
            temp[t++] = pos[r++];
        }
        System.arraycopy(temp,0,pos,left,temp.length);
    }

    public static void main(String[] args){

    }
}
