package practice.test;

/**
 * Created by wenqi on 2020/6/26.
 */
public class TestBinarySearchWhileNotEqual {
    public static void main(String[] args){
        int[] arr={1,2,2,4};
        System.out.println(left_bound(arr,3));
        System.out.println(right_bound(arr,5));
    }

    static int left_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length-1; // 注意

        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }
        return left;
    }

    static int right_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length-1; // 注意

        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left=mid+1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }
        //返回的是第一个大于的值
        if(left==nums.length-1){
            //特殊情况，如果是尽头的话，应该返回left
            return left;
        } else{
            return left-1;
        }
    }
}
