package practice.test;
import javafx.collections.transformation.SortedList;
import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

/**
 * Created by wenqi on 2020/5/31.
 */
public class TestJavaBasis {

    static class Pair{
        public Integer upValue;
        public Integer downValue;

        public Pair(int upValue,int downValue){
            this.upValue=upValue;
            this.downValue=downValue;
        }

        @Override
        public int hashCode() {
            return upValue.hashCode()+downValue.hashCode();
        }

        @Override
        public boolean equals(Object obj){
            if(obj instanceof Pair){
                Pair cmpPair=(Pair) obj;
                if(cmpPair.upValue==this.upValue && cmpPair.downValue==this.downValue){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
    }
    public static void main(String[] args) {
        Queue<String> queue=new LinkedList<>();
        queue.peek();

        Set<Integer> hashSet=new HashSet<>();
        hashSet.add(1);
        hashSet.add(5);
        hashSet.contains(5);
        hashSet.remove(1);
        int first=hashSet.iterator().next();
        int first2=hashSet.iterator().next();
        System.out.println(first2);

        Map<Pair,Integer> map=new HashMap<>();
        map.put(new Pair(1,2),1);
        int getValue=map.get(new Pair(1,2));
        System.out.println(getValue);

        System.out.println(getGcd(4,-8));

        //
        List<String> list=new LinkedList<>();
        //list.remove(1);

        Stack<String> stack=new Stack<>();
        stack.toArray();
        //stack.peek();

        PriorityQueue<String> priorityQueue=new PriorityQueue<>();
        priorityQueue.offer("test");
        priorityQueue.poll();
        priorityQueue.remove(1);

        Set<Integer> set=new HashSet<>();
        set.contains(1);
        set.add(1);
        String test="";
        //test.substring(1,2);

        Queue<Integer> queue2=new ArrayDeque<>();
        queue2.offer(1);
        //[2147483647,2147483647,2147483647]
        int val=2147483647;
        long longval=Long.valueOf(val);
        System.out.println(longval+longval);
        Map<String,Integer> map1 = new HashMap<>();
        for(Map.Entry<String,Integer> entry:map1.entrySet()){
            entry.setValue(1);
            entry.getValue();
        }


        Queue<String> queue1=new LinkedBlockingDeque<>();
        //ArrayDeque不允许有null值
        //queue1.offer(null);

        String testStr="xxx";
        testStr.substring(1,3);
        StringBuilder builder=new StringBuilder("test");
        builder.setCharAt(1,'x');

//        List<String> list2=new ArrayList<String>();
//        list2.set(1,"");
//        list2.remove(0);
        char[] testArr={'a','b','c'};
        new String(testArr);
        String.valueOf(testArr);
        BitSet bitSet=new BitSet();
        PriorityQueue<String> heap=new PriorityQueue<>();

        List<Integer> result=new ArrayList<>();
        result.add(3);
        result.add(3);
        result.add(3);
        result.add(4);
        result.remove(result.size()-1);
        System.out.println(Arrays.toString(result.toArray()));

        Integer[] arr={1,2,3,4,5,6};
        Arrays.sort(arr,(item1,item2)->item1-item2);
        String[] strArr=new String[2];

        int testInt=912834723;
        System.out.println(testInt*10+2);
    }

    public static int getGcd(int num1,int num2){
        //负数同样使用辗转相除法
        //4/(-8)=0, 4%(-8)=4
        int remender=0;
        do{
            remender=num1%num2;
            num1=num2;
            num2=remender;
        }while(remender!=0);
        return num1;
    }
}
