package practice.test2;

import java.util.Arrays;

/**
 * Created by wenqi on 2022/1/8.
 */
public class Sort {
    public static void main(String[] args){
        int[] arr={3,2,1,4,5,3,4,2,1};
        Sort sort=new Sort();
        /*sort.quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));*/

        sort.mergeSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public void quickSort(int[] arr, int start,int end){
        if(start<end){
            int qivotIndex=findQivot(arr,start,end);
            quickSort(arr,start,qivotIndex-1);
            quickSort(arr,qivotIndex+1,end);
        }
    }

    private int findQivot(int[] arr,int start,int end){
        int qivotVal=arr[start];
        while (start<end){
            //qivot右边的都大于当前值
            while(start<end && arr[end]>qivotVal) end--;
            arr[start]=arr[end];
            //qivot左右的均大于等于当前值
            while (start<end && arr[start]<=qivotVal) start++;
            arr[end]=arr[start];
        }
        arr[start]=qivotVal;
        return start;
    }

    public void mergeSort(int[] arr,int start,int end){
        if(start<end){
            int mid=start+(end-start)/2;
            mergeSort(arr,start,mid);
            mergeSort(arr,mid+1,end);
            mergeTwoSortList(arr,start,mid,end);
        }
    }

    public void mergeTwoSortList(int[] arr,int start,int mid,int end){
        if(start<end){

            int originStart=start;

            int[] tmpArr=new int[end-start+1];
            int tmpIndex=0;
            int start2=mid+1;

            while (start<=mid && start2<=end){
                if(arr[start]<arr[start2]){
                    tmpArr[tmpIndex++]=arr[start++];
                }else{
                    tmpArr[tmpIndex++]=arr[start2++];
                }
            }

            while (start<=mid){
                tmpArr[tmpIndex++]=arr[start++];
            }
            while (start2<=end){
                tmpArr[tmpIndex++]=arr[start2++];
            }

            for(int i=0;i<tmpArr.length;i++){
                arr[originStart+i]=tmpArr[i];
            }
        }
    }

}
