package practice.test2;

/**
 * Created by wenqi on 2022/1/6.
 */
public class MyQueue {

    public static void main(String[] args){

    }


    private int arrSize;
    private int[] arr;
    private int font;
    private int rear;
    public MyQueue(int maxSize){
        arrSize=maxSize+1;
        arr=new int[arrSize];
        font =0;
        rear=0;
    }

    public boolean isFull(){
        return (rear+1)%arrSize== font;
    }

    public boolean offer(int val){
        if(isFull()){
            return false;
        }else{
            rear=(rear+1)%arrSize;
            arr[rear]=val;
            return true;
        }
    }

    public boolean isEmpty(){
        return rear== font;
    }

    public int poll() throws Exception {
        if(isEmpty()){
            throw new Exception();
        }else{
            font=(font+1)%arrSize;
            return arr[font];
        }
    }
}
