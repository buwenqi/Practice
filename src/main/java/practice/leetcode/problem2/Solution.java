package practice.leetcode.problem2;

import java.net.Socket;

/**
 * Created by wenqi on 2019/9/7.
 *
 * Description:
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 * Related:
 * Basic
 */
public class Solution {

    public static void main(String[] args){
        ListNode l1=new ListNode(1);


        ListNode l5=new ListNode(9);
        ListNode l6=new ListNode(9);
        l5.next=l6;


        Solution solution=new Solution();
        System.out.println(solution.addTwoNumbers(l1,l5));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode curL1=l1;
        ListNode curL2=l2;
        ListNode resultHead=null;
        ListNode resultPointer=null;
        int flag=0;
        while(curL1!=null||curL2!=null){
            int curL1Value,curL2Value;
            curL1Value=curL1==null?0:curL1.val;
            curL2Value=curL2==null?0:curL2.val;
            int curSum=curL1Value+curL2Value+flag;
            if(curSum>=10){
                flag=1;
                curSum=curSum%10;
            }else{
                flag=0;
            }

            ListNode newNode=new ListNode(curSum);
            newNode.next=null;
            if(resultPointer!=null){
                resultPointer.next=newNode;
                resultPointer=newNode;
            }else{
                resultHead=newNode;
                resultPointer=newNode;
            }
            curL1=curL1==null?null:curL1.next;
            curL2=curL2==null?null:curL2.next;
        }

        if(flag==1){
            ListNode newNode=new ListNode(1);
            newNode.next=null;
            resultPointer.next=newNode;
        }
        return resultHead;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString(){
        StringBuilder builder=new StringBuilder(val+",");
        ListNode curNext=next;
        while (curNext!=null){
            builder.append(curNext.val+",");
            curNext=curNext.next;
        }
        return builder.toString();
    }
}
