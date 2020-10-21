package practice.test;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Java基础数据结构类整理
 *
 * @author buwenqi
 * @date 2020/8/19
 */
public class JavaBasis {
    public static void main(String[] args) {
        /**
         * Array相关
         */
        //一维二维数组
        int[] arr1 = {1, 2, 3, 4};
        int[][] arr2 = {{1}, {1, 3}};
        int[][] testArr2 = new int[][]{{1,2},{2,3}};
        System.out.println(testArr2[1][1]);

        //复制数组
        int[] array2 = {0, 1, 2, 3, 4, 5, 6, 7};
        int[] arrayPart1 = Arrays.copyOfRange(array2, 0, 4);
        System.out.println("part1:" + Arrays.toString(arrayPart1));
        int[] arrayAll = Arrays.copyOf(array2, array2.length);
        System.out.println("arrayAll:" + Arrays.toString(arrayAll));
        //原生System.arraycopy函数，需要承接
        int[] result2 = new int[array2.length-1];
        System.arraycopy(array2, 1, result2, 0, result2.length);
        System.out.println(Arrays.toString(result2));

        //一维数组求和+最大值
        //array求和
        int[] arr = {1, 2, 3};
        int sum = Arrays.stream(arr).sum();
        System.out.println(sum);
        //array求最大值
        int maxValue = Arrays.stream(arr).max().getAsInt();
        System.out.println("maxValue:" + maxValue);

        //数组排序
        Arrays.sort(arr);
        Integer[] boxedArr = {1, 2, 3};
        Arrays.sort(boxedArr, (obj1, obj2) -> {
            return obj2 - obj1;
        });

        //排序二维数组
        int[][] multiArr = {{1, 4}, {1, 3}};
        Arrays.sort(multiArr, (obj1, obj2) -> obj1[1] - obj2[1]);

        for (int i = 0; i < multiArr.length; i++) {
            System.out.println(Arrays.toString(multiArr[i]));
        }

        /***
         * Set相关
         */
        System.out.println("Set ------------------");
        int[] arr3 = {1, 3, 20, 11, 3, 11, 30, 6};
        SortedSet<Integer> sortedSet = new TreeSet<>();
        for (int i = 0; i < arr3.length; i++) {
            sortedSet.add(arr3[i]);
        }
        System.out.println(Arrays.toString(sortedSet.toArray()));

        /***
         * list相关
         */
        System.out.println("List ------------------");
        //List添加批量数据的方式
        List<String> test2 = new ArrayList<String>() {{
            add("test1");
        }};
        Collections.addAll(test2, "test2", "test3");
        System.out.println(Arrays.toString(test2.toArray()));
        List<String> newList = new ArrayList<>(test2);

        //不同的数组转List
        Integer[] listArr = {1, 2, 3, 4};
        List<Integer> newList2 = new ArrayList<Integer>(Arrays.asList(listArr));
        int[] listArr2 = {1, 2, 3, 4};
        List<Integer> newList3 = Arrays.stream(listArr2).boxed().collect(Collectors.toList());

        //List转不同的数组
        //当list2Arr1的长度小于List的长度时，返回的是List长度的数组，新开辟的地址
        Integer[] list2Arr1=new Integer[newList3.size()];
        list2Arr1=newList3.toArray(list2Arr1);
        //System.out.println(Arrays.toString(list2Arr1));
        //list转成int[]数组
        int[] list2Arr2 = newList3.stream().mapToInt(item -> item).toArray();
        System.out.println(Arrays.toString(list2Arr2));

        //list删除remove
        List<String> stringList = new ArrayList<>(2);
        stringList.add("haha");
        stringList.add("ff");
        //using iterator
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()) {
            String curString = iterator.next();
            System.out.println(curString);
            iterator.remove();
            System.out.println(Arrays.toString(stringList.toArray()));
        }

        //arrayList也可以实现头插法
        List<String> arrayList = new ArrayList<>(Arrays.asList(new String[]{"abc", "dce"}));
        arrayList.add(1, "test");
        System.out.println(Arrays.toString(arrayList.toArray()));
        //LinkedList也可以
        LinkedList<String> linkedList = new LinkedList<>(Arrays.asList(new String[]{"abc", "dce"}));
        linkedList.add(1,"test2");
        System.out.println(Arrays.toString(linkedList.toArray()));

        //List排序
        Collections.sort(newList3, (obj1, obj2) -> {
            return obj2 - obj1;
        });

        /***
         * List数组，其他类型的集合数组也可参考这个例子
         */
        //List数组
        //建立list数组
        List[] listArr3 = new ArrayList[2];
        for(int i=0;i<listArr3.length;i++){
            listArr3[i]=new ArrayList<String>();
        }
        //从list数组总获取对应的list
        List<String> curList=(ArrayList<String>)listArr3[1];
        Collections.addAll(curList,"test1","test2");
        System.out.println(Arrays.toString(listArr3[1].toArray()));

        /**
         * Map字典相关
         */
        System.out.println("Map ------------------");
        //基本put,get
        Map<Character, Character> map = new HashMap<>();
        map.put(null, 'a');
        map.put('t','t');
        System.out.println(map.get(null));
        System.out.println("get not exits: "+map.get('('));
        System.out.println("get or default: "+map.getOrDefault('(','D'));
        //是否包含Key或者Value
        if(map.containsKey(null)){
            System.out.println("containsKey:"+null);
        }
        if(map.containsValue('a')){
            System.out.println("containsValue:"+'a');
        }
        //遍历Key或者Value
        for(Character key: map.keySet()){
            System.out.print(key+",");
        }
        System.out.println();
        for(Character value:map.values()){
            System.out.print(value+",");
        }
        System.out.println();

        //根据Key排序使用TreeMap
        Map<Integer, int[]> map2 = new TreeMap<>((o1, o2) -> o1.compareTo(o2));
        map2.put(3,new int[2]);
        map2.put(2,new int[2]);
        map2.put(4,new int[2]);
        Iterator<Integer> iterator2 = map2.keySet().iterator();
        while (iterator2.hasNext()) {
            Integer key = iterator2.next();
            System.out.println("key:" + key + ",value:" + Arrays.toString(map2.get(key)));
        }

        //按value值排序
        List<Map.Entry<Integer, int[]>> entrySets = new ArrayList<>(map2.entrySet());
        Collections.sort(entrySets, (entry1, entry2) -> entry1.getValue()[0] - entry2.getValue()[0]);

        /**
         * Java中堆的实现PriorityQueue
         */
        System.out.println("priorityQueue ------------------");
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((v1, v2) -> v1.compareTo(v2));
        priorityQueue.offer(4);
        priorityQueue.offer(1);
        priorityQueue.offer(5);
        priorityQueue.offer(6);
        System.out.println("size:" + priorityQueue.size());
        priorityQueue.stream().forEach(System.out::println);
        System.out.println("poll:" + priorityQueue.poll());
        //priorityQueue.peek();
        priorityQueue.remove(4);
        priorityQueue.stream().forEach(System.out::println);

        /**
         * String相关
         */
        System.out.println("String相关 ------------------");
        //char数组转换成String
        char[] charArr = new char[10];
        charArr[0] = 'a';
        charArr[1] = 'b';
        String resultStr=new String(charArr);
        System.out.println(resultStr);
        //String转Char数组
        char[] resultCharArr=resultStr.toCharArray();

        //StringBuilder操作字符串
        //StringBuilder的deleteCharAt和toString()
        StringBuilder builder = new StringBuilder("abc");
        builder.append("de");
        System.out.println(builder.toString());
        builder.deleteCharAt(builder.length() - 1);
        System.out.println(builder.toString());

        //String取子串
        String str = "0123456";
        System.out.println(str.substring(0,6));
        //output:012345
        //获取某个字符的下标
        System.out.println(str.indexOf('0'));
        System.out.println(str.indexOf('0', 3));

        /**
         * stack相关
         */
        System.out.println("Stack ------------------");
        Stack<String> stack = new Stack<>();
        stack.push("abc");
        if(!stack.isEmpty()) {
            //peek方法应该在不为空的情况下调用，否则会报异常
            System.out.println(stack.peek());
            System.out.println(stack.pop());
        }

        /**
         * 队列相关
         */
        System.out.println("Queue ------------------");
        Queue<String> queue=new LinkedList<>();
        queue.offer("test1");
        queue.offer("test2");
        queue.offer("test3");
        if(!queue.isEmpty()){
            System.out.println(queue.peek());
            System.out.println(queue.poll());
        }

        //双向链表
        LinkedList<String> doubleLinkedList=new LinkedList<>();
        doubleLinkedList.offerLast("test1");
        doubleLinkedList.offerFirst("test2");
        doubleLinkedList.offerFirst("test3");
        //output: test3,test2,test1,
        for(String item: doubleLinkedList){
            System.out.print(item+",");
        }
        System.out.println();
        //output: test3,test3,test1
        System.out.println(doubleLinkedList.peek()+","+doubleLinkedList.peekFirst()+","+ doubleLinkedList.peekLast());
        //output:test3,test1
        System.out.println(doubleLinkedList.pollFirst()+","+doubleLinkedList.peekLast());

        /**
         * 双端队列相关
         */
        Deque<Integer> deque=new ArrayDeque<>(2);
        deque.offerLast(1);
        deque.offerLast(2);
        deque.offerLast(3);
        while (!deque.isEmpty()){
            System.out.println(deque.pollFirst());
        }

        /**
         * 其他小点
         */
        System.out.println("Other ------------------");
        //null不能和基本类型比较
        System.out.println(null == (Character) 'x');

        //Pair类型返回多个值
        Pair<Integer, String> pair = new Pair<>(1, "12");
        System.out.println(pair.getKey() + ":" + pair.getValue());

        int testOperator = 4;
        if ((testOperator & 1) == 1) {
            System.out.println("test");
        }

        //操作符优先级测试
        System.out.println((4 << 1) + 1);
        String curStr="abcdefg";
        int index=2;
        String result=curStr.substring(0,index)+curStr.substring(index+1,curStr.length());
        System.out.println(result);


    }

}
