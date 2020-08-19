package practice.test;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by wenqi on 2020/5/31.
 */
public class Test2 {
    public static void main(String[] args) {
        //List的equal方法
        List<Integer> hello = new ArrayList<Integer>(Arrays.asList(new Integer[]{1, 2, 3}));
        List<Integer> hello2 = new ArrayList<Integer>(Arrays.asList(new Integer[]{1, 2, 3}));
        List<List<Integer>> helloCollection = new ArrayList<>();
        helloCollection.add(hello);
        helloCollection.contains(hello);
        helloCollection.add(hello2);
        System.out.println(hello.equals(hello2));
        //二维数组初始化
        int[][] test = {{1}, {1, 3}};
        System.out.println(test[0][0]);
        int[][] test2Arr = new int[2][2];
        test2Arr[1][0] = 1;
        System.out.println("二维数组：" + test2Arr[1][1]);
        //StringBuilder的deleteCharAt和toString()
        StringBuilder builder = new StringBuilder("abc");
        System.out.println("builder length:" + builder.length());
        System.out.println(builder.toString());
        builder.deleteCharAt(builder.length() - 1);
        System.out.println(builder.toString());

        //stack
        Stack<Character> stack = new Stack<>();
        //stack.peek(); //peek方法应该在不为空的情况下调用，否则会报异常
        Map<Character, Character> map = new HashMap<>();
        map.put(null, 's');
        System.out.println("get--"+map.get('('));
        System.out.println(map.get(null));
        //null不能喝基本类型比较
        System.out.println(null == (Character) 'x');
        //char数组转换成String
        char[] charArr = new char[10];
        charArr[8] = 'a';
        charArr[2] = 'a';
        System.out.println(new String(charArr).toString());

        Map<Integer, Integer> map3 = new HashMap<>();
        map3.put(1, 1);
        System.out.println("containsKey:" + map3.containsKey(1));
        map3.getOrDefault(1, 0);


        List<String> stringList = new ArrayList<>(2);
        stringList.add("haha");
        stringList.add("ff");
        System.out.println(stringList.get(0));
        stringList.stream().map(item -> item.toString()).collect(Collectors.toList());
        //using iterator
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()) {
            String curString = iterator.next();
            iterator.remove();
            System.out.println(stringList);
        }

        //Pair类型返回多个值
        Pair<Integer, String> pair = new Pair<>(1, "12");
        System.out.println(pair.getKey() + ":" + pair.getValue());

        //优先队列模拟堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(2, (v1, v2) -> v1.compareTo(v2));
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

        int s = 11;
        int ps = s > 1 ? 2 : 3;
        System.out.println(ps);

        //array求和
        int[] arr = {1, 2, 3};
        int sum = Arrays.stream(arr).sum();
        System.out.println(sum);
        //array求最大值
        int maxValue = Arrays.stream(arr).max().getAsInt();
        System.out.println("maxValue:" + maxValue);


        //排序多维数组
        int[][] multiArr = {{1, 4}, {1, 3}};

        /*Arrays.sort(multiArr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });*/
        Arrays.sort(multiArr, (obj1, obj2) -> obj1[1] - obj2[1]);

        for (int i = 0; i < multiArr.length; i++) {
            System.out.println(Arrays.toString(multiArr[i]));
        }

        //防止求中间值溢出操作
        short short1 = (short) (Short.MAX_VALUE + 1);
        System.out.println(short1 >> 1);
        System.out.println(1 + ((9 - 1) >> 1));

        //Map的API
        Map<Integer, int[]> map1 = new TreeMap<>();
        int[] mapValue = new int[]{1, 2};
        map1.put(3, mapValue);
        map1.size();
        map1.keySet();
        map1.values();
        boolean containsKey = map1.containsKey(3);
        boolean containsValue = map1.containsValue(mapValue);
        System.out.println(containsKey + ":" + containsValue);
        //map的遍历和排序操作
        //TreeMap可以自动排序，可以传入自定义Comparator
        /*Comparator<Integer> comparator=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o1);
            }
        };*/
        Map<Integer, int[]> map2 = new TreeMap<>((o1, o2) -> o1.compareTo(o2));
        Iterator<Integer> iterator2 = map2.keySet().iterator();
        while (iterator2.hasNext()) {
            Integer key = iterator2.next();
            System.out.println("key:" + key + ",value:" + Arrays.toString(map2.get(key)));
        }

        //按value值排序
        List<Map.Entry<Integer, int[]>> entrySets = new ArrayList<>(map2.entrySet());
        Collections.sort(entrySets, (entry1, entry2) -> entry1.getValue()[0] - entry2.getValue()[0]);
        //使用linkedList,和数组结合
        List<int[]> list = new LinkedList<>();
        list.add(0, new int[]{1, 2});
        list.add(1, new int[]{3, 4});
        //add是在index之前插入，代替其位置，头插法
        list.add(1, new int[]{5, 6});
        //只能这样使用，不能使用强制转型
        int[][] result = list.toArray(new int[list.size()][2]);
        System.out.println(Arrays.toString(result[1]));

        //arrayList也可以实现头插法
        List<String> arrayList = new ArrayList<>(Arrays.asList(new String[]{"abc", "dce"}));
        arrayList.add(1, "test");
        System.out.println(Arrays.toString(arrayList.toArray()));

        int testOperator = 4;
        if ((testOperator & 1) == 1) {
            System.out.println("test");
        }

        //操作符优先级测试
        System.out.println((4 << 1) + 1);

        //List添加批量数据的方式
        List<String> test2 = new ArrayList<String>() {{
            add("test1");
        }};
        Collections.addAll(test2, "test2", "test3");
        System.out.println(Arrays.toString(test2.toArray()));
        //复制List
        List<String> newList = new ArrayList<>(test2);
        Integer[] listArr = {1, 2, 3, 4};
        List<Integer> newList2 = new ArrayList<Integer>(Arrays.asList(listArr));
        int[] listArr2 = {1, 2, 3, 4};
        List<Integer> newList3 = Arrays.stream(listArr2).boxed().collect(Collectors.toList());

        //拷贝数组的一部分出来
        int[] array2 = {0, 1, 2, 3, 4, 5, 6, 7};
        //from为开始下标，to为结束的下标+1，[from,to)，左闭右开
        int[] arrayPart1 = Arrays.copyOfRange(array2, 0, array2.length >> 1);
        int[] arrayPart2 = Arrays.copyOfRange(array2, array2.length >> 1, array2.length);
        System.out.println("part1:" + Arrays.toString(arrayPart1));
        System.out.println("part2:" + Arrays.toString(arrayPart2));
        //全部拷贝
        int[] arrayAll = Arrays.copyOf(array2, array2.length);
        System.out.println("arrayAll:" + Arrays.toString(arrayAll));

        int[] result2 = new int[array2.length - 1];
        System.arraycopy(array2, 0, result2, 0, result2.length);
        System.out.println(Arrays.toString(result2));

        //去重查询

        Integer[] arr2 = {1, 3, 2, 1, 3, 5, 1, 6};
        Set<Integer> set = new HashSet<Integer>(Arrays.asList(arr2));
        int[] arr3 = {1, 3, 2, 1, 3, 5, 1, 6};
        SortedSet<Integer> sortedSet = new TreeSet<>();
        for (int i = 0; i < arr3.length; i++) {
            sortedSet.add(arr3[i]);
        }

        System.out.println(Arrays.toString(set.toArray()));
        System.out.println(set.size());
        //Set没有get方法，只能转换成Array或者List，或者遍历获取

        //arr转list
        List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(arr2));


        String good = "ADOBECODEBANC";
        good.length();
        System.out.println(good.substring(9, 12));

        int sqrtResult = (int) Math.sqrt(9);
        System.out.println(sqrtResult);


        //String操作
        String str1 = "aaz";
        String str2 = "az";
        System.out.println(str1.compareTo(str2));
        //获取某个字符的下标
        System.out.println(str1.indexOf('z'));
        System.out.println(str1.indexOf('z', 3));

        //建立list数组
        List[] listArrss = new ArrayList[2];
        List<Integer> tmp = new ArrayList<>();
        listArrss[0] = tmp;

        //list转成int[]数组
        tmp.add(1);
        tmp.add(3);
        int[] intArr = tmp.stream().mapToInt(item -> item).toArray();
        System.out.println(Arrays.toString(intArr));

        //string的操作
        String test1 = "aabbb";
        char[] testArr = test1.toCharArray();
        System.out.println(new String(testArr));
        char charItem = (char) 97;
        System.out.println(charItem);

        //char数组转string
        char[] helloChar={'a','a'};
        String newChars=String.valueOf(helloChar);
        System.out.println(newChars);

        //二维的Set
        Set[][] setArr=new HashSet[2][2];
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                setArr[i][j]=new HashSet<Integer>();
            }
        }

        ((HashSet<Integer>)setArr[0][0]).add(2);
        System.out.println(Arrays.toString(setArr[0][0].toArray()));
        int intvalue=2;
    }
}
