package practice.test;

import java.util.Arrays;

/**
 * 使用循环数组来实现队列，有最大容量的限制
 *
 * @author buwenqi
 * @date 2020/8/21
 */
public class TestCycleArray {
    static class MyQueue{
        int front;
        int rear;
        int ARR_SIZE;
        int[] cycleArray;

        public MyQueue(int maxSize){
            //maxSize+1最大容量是maxSize
            ARR_SIZE=maxSize+1;
            cycleArray=new int[ARR_SIZE];
            //front的位置处于待取元素的前一个位置，front+1是当前要取元素的位置
            front=0;
            //rear的位置处于最后一个已放入元素的位置，放入元素后，新放入元素位置在rear+1
            rear=0;
        }

        public boolean isFull(){
            return (rear+1)%ARR_SIZE==front;
        }

        public boolean offer(int element){
            //判断是否队列已满，满的情况下空出一个位置
            if(isFull()){
                return false;
            }else{
                rear=(rear+1)%ARR_SIZE;
                cycleArray[rear]=element;
                return true;
            }
        }

        public boolean isEmpty(){
            return front==rear;
        }
        public int poll(){
            if(isEmpty()){
                throw new IllegalArgumentException("队列为空！");
            }else{
                front=(front+1)%ARR_SIZE;
                return cycleArray[front];
            }
        }

        public int size(){
            return (rear+ARR_SIZE-front)%ARR_SIZE;
        }

        @Override
        public String toString(){
            int size=size();
            if(size==0){
                return "";
            }else{
                int index=front;
                int count=0;
                StringBuilder builder=new StringBuilder();
                while (count<size){
                    index=(index+1+ARR_SIZE)%ARR_SIZE;
                    builder.append(cycleArray[index]+",");
                    count++;
                }
                return builder.deleteCharAt(builder.length()-1).toString();
            }
        }
    }

    public static void main(String[] args){
        MyQueue queue=new MyQueue(4);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.poll();
        queue.poll();
        queue.poll();
        queue.offer(5);
        queue.offer(6);
        queue.poll();
        queue.poll();
        System.out.println(queue.toString());
    }
}
