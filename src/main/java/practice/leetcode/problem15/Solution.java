import java.util.*;
class Solution {
   boolean[] flags;
    List<List<Integer>> result;

    //Solution1: Deep search, but time complexity is to large.
    public List<List<Integer>> threeSum(int[] nums) {
        result=new ArrayList<>();
        if(nums==null || nums.length<3){
            return result;
        }
        flags=new boolean[nums.length];
        for(int i=0;i<flags.length;i++){
            flags[i]=false;
        }
        Arrays.sort(nums);
        getThreeSum(0,0,0,new ArrayList<Integer>(),nums);

        // clear duplicate triplats
        Set<Integer> duplicateIndexs=new HashSet<>();
        for(int i=0;i<result.size();i++){
            for(int j=i+1;j<result.size();j++){
                if(isSame(result.get(i),result.get(j))){
                   duplicateIndexs.add(j);
                }
            }
        }
        List<List<Integer>> finalResult=new ArrayList<>();
        for(int i=0;i<result.size();i++){
            if(duplicateIndexs.contains(i)){
                continue;
            }else{
                finalResult.add(result.get(i));
            }
        }
        return finalResult;
        
    }

    public void getThreeSum(int startIndex,int curStep,int curSum,List<Integer> curList,int[] nums){
        if(curStep==3){
            //已经放入了3个数字
            if(curSum==0){
                //符合要求
                List<Integer> tmpList=new ArrayList();
                tmpList.addAll(curList);
                result.add(tmpList);
            }else{
                //不符合要求
            }
            return ;
        }else{
            for(int i=startIndex;i<nums.length;i++){
                if(flags[i]){
                    //已经放入了
                    continue;
                }else{
                    flags[i]=true;
                    curSum+=nums[i];
                    curList.add(nums[i]);
                    getThreeSum(i+1,curStep+1,curSum,curList,nums);
                    curList.remove(curList.size()-1);
                    flags[i]=false;
                    curSum-=nums[i];
                }
            }
        }
    }

    private boolean isSame(List<Integer> resultItem, List<Integer> tempList){
        boolean result= true;
        for(int i=0;i<resultItem.size();i++){
            if(resultItem.get(i)==tempList.get(i)){
                continue;
            }else{
                return false;
            }
        }
        return result;
    }

    //Solution2， two pointer and remove the duplicate item
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        if(nums==null || nums.length<3){
            return result;
        }
        //排序
        Arrays.sort(nums);
        for(int left=0;left<nums.length-2;left++){
            //以当前left为三元组中的最左边的值
            if(nums[left]>0){
                continue;
            }
            //如果和之前的left相同的话，跳过
            if(left>0 && nums[left]==nums[left-1]){
                continue;
            }
            int low=left+1;
            int high=nums.length-1;
            //初始值为-1，不进行判定，其他情况代表上次改变的是0：low, 1:high;
            while(low<high){
                //双指针校验过程
                int sum=nums[left]+nums[low]+nums[high];
                if(sum==0){
                    result.add(getOneResult(nums[left],nums[low],nums[high]));
                    high--;
                    while(low<high && nums[high]==nums[high+1]) high--;
                    continue;
                }else if(sum<0){
                    low++;
                    while(low<high && nums[low]==nums[low-1]) low++;
                }else{
                    high--;
                    while(low<high && nums[high]==nums[high+1]) high--;
                }
            }
        }
        return result;
        
        
    }
    
    private List<Integer> getOneResult(int leftValue,int lowValue,int highValue){
        List<Integer> tmpList=new ArrayList<>();
        tmpList.add(leftValue);
        tmpList.add(lowValue);
        tmpList.add(highValue);
        return tmpList;
    }

    // solution3: remove duplicate items dependently -time limit exceeded
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        if(nums==null || nums.length<3){
            return result;
        }
        //排序
        Arrays.sort(nums);
        for(int left=0;left<nums.length-2;left++){
            //以当前left为三元组中的最左边的值
            if(nums[left]>0){
                continue;
            }
            int low=left+1;
            int high=nums.length-1;
            //初始值为-1，不进行判定，其他情况代表上次改变的是0：low, 1:high;
            while(low<high){
                //双指针校验过程
                int sum=nums[left]+nums[low]+nums[high];
                if(sum==0){
                    List<Integer> curResult=getOneResult(nums[left],nums[low],nums[high]);
                    if(!result.contains(curResult)){
                        result.add(curResult);
                    }
                    high--;
                    continue;
                }else if(sum<0){
                    low++;
                }else{
                    high--;
                }
            }
        }
        
        return result;
        
        
    }
    
    private List<Integer> getOneResult(int leftValue,int lowValue,int highValue){
        List<Integer> tmpList=new ArrayList<>();
        tmpList.add(leftValue);
        tmpList.add(lowValue);
        tmpList.add(highValue);
        return tmpList;
    }
}