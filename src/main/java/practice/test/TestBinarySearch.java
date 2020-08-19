package practice.test;

import javax.xml.stream.FactoryConfigurationError;

/**
 * Created by wenqi on 2020/6/26.
 */
public class TestBinarySearch {
    public static void main(String[] args){
        int[] arr={1,3,5,7,9,15,18};
        //查找target对应的下标，如果不存在，则返回-1
        int target1=3;
        System.out.println(String.format("binarySearch1 find %s index:%s",target1,binarySearch1(arr,target1)));

        //含有重复数字的数组，查找最左边的target,如果查找失败，则返回-1
        int[] duplicatedArr={1,2,2,2,3,5,8};
        int target2=8;
        System.out.println(String.format("binarySearchDuplicateLeft find %s index:%s",target2,binarySearchDuplicateLeft(duplicatedArr,target2)));
        int target3=1;
        System.out.println(String.format("binarySearchDuplicateRight find %s index:%s",target3,binarySearchDuplicateRight(duplicatedArr,target3)));

        //不管是否重复,查找第一个小于target的值
        int target4=18;
        System.out.println(String.format("binarySearchFindLeftBorder find %s index:%s",target4,binarySearchFindLeftBorder(arr,target4)));
        System.out.println(String.format("binarySearchFindRightBorder find %s index:%s",target4,binarySearchFindRightBorder(arr,target4)));
        //binarySearchFindRightBorder
    }

    //查找target对应的下标，如果不存在，则返回-1
    public static int binarySearch1(int[] arr,int target){
        if(arr==null || arr.length==0){
            return -1;
        }
        int low=0;
        int high=arr.length-1;
        while (low<=high){
            int mid=low+(high-low)/2;
            //System.out.println("mid:"+mid+",low:"+low+",high:"+high);
            if(arr[mid]==target){
                return mid;
            }else if (arr[mid]>target){
                high=mid-1;
            }else if(arr[mid]<target){
                low=mid+1;
            }
        }
        return -1;
    }

    //含有重复数字的数组，查找最左边的target,如果查找失败，则返回-1
    public static int binarySearchDuplicateLeft(int[] arr,int target){
        if(arr==null || arr.length==0){
            return -1;
        }
        int low=0;
        int high=arr.length-1;
        while (low<=high){
            int mid=low+(high-low)/2;
            //System.out.println("mid:"+mid+",low:"+low+",high:"+high);
            if(arr[mid]==target){
                high=mid-1;
            }else if (arr[mid]>target){
                high=mid-1;
            }else if(arr[mid]<target){
                low=mid+1;
            }
        }
        //low和high交叉位置，如果找到target，则low应该指向它
        if(low<arr.length && arr[low]==target){
            return low;
        }else{
            return -1;
        }
    }

    //含有重复数字的数组，查找最右边的target,如果查找失败，则返回-1
    public static int binarySearchDuplicateRight(int[] arr,int target){
        if(arr==null || arr.length==0){
            return -1;
        }
        int low=0;
        int high=arr.length-1;
        while (low<=high){
            int mid=low+(high-low)/2;
            //System.out.println("mid:"+mid+",low:"+low+",high:"+high);
            if(arr[mid]==target){
                low=mid+1;
            }else if (arr[mid]>target){
                high=mid-1;
            }else if(arr[mid]<target){
                low=mid+1;
            }
        }
        //low和high交叉位置，如果找到target，则high应该指向它,因为取最右边的值
        if(high>=0 && arr[high]==target){
            return high;
        }else{
            return -1;
        }
    }

    public static int binarySearchFindLeftBorder(int[] arr,int target){
        if(arr==null || arr.length==0){
            return -1;
        }
        int low=0;
        int high=arr.length-1;
        while (low<=high){
            int mid=low+(high-low)/2;
            //System.out.println("mid:"+mid+",low:"+low+",high:"+high);
            if(arr[mid]==target){
                high=mid-1;
            }else if (arr[mid]>target){
                high=mid-1;
            }else if(arr[mid]<target){
                low=mid+1;
            }
        }
        //此时如果有等于target的，应该是low指向
        //如果所有的数均大于等于target的话，high会小于0
        if (high>=0) {
            return high;
        } else {
            return -1;
        }
    }

    //不管是否重复，查找第一个大于target的值
    public static int binarySearchFindRightBorder(int[] arr,int target){
        if(arr==null || arr.length==0){
            return -1;
        }
        int low=0;
        int high=arr.length-1;
        while (low<=high){
            int mid=low+(high-low)/2;
            //System.out.println("mid:"+mid+",low:"+low+",high:"+high);
            if(arr[mid]==target){
                low=mid+1;
            }else if (arr[mid]>target){
                high=mid-1;
            }else if(arr[mid]<target){
                low=mid+1;
            }
        }
        //此时如果有等于target的，应该是high指向,应该返回low这里
        //如果所有的数均小于等于target的话，high会等于arr.length
        if (low<arr.length) {
            return low;
        } else {
            return -1;
        }
    }
}
