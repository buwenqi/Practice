## Java常用数据结构及其操作

### 一维二维数组
- 不同的初始化方式：
```
int arr1=new {1,2,3}
int[][] arr2 = {{1}, {3, 3}};
int[][] arr2_2 = new int[][]{{1,2},{2,3}};
```
- 数组的复制
Arrays的copyOfRange复制部分，from为开始下标，to为结束的下标+1，[from,to)，左闭右开
```
int[] arrayPart1 = Arrays.copyOfRange(array2, 0, 4);
System.out.println("part1:" + Arrays.toString(arrayPart1));
// part1:[0, 1, 2, 3]
```
全部复制，使用copyOf函数
```
int[] arrayAll = Arrays.copyOf(array2, array2.length);
System.out.println("arrayAll:" + Arrays.toString(arrayAll));
```
原生System.arraycopy函数（srcArr,src起始Index,targetArr,target起始index,拷贝的数量）
```
int[] result2 = new int[array2.length-1];
//从array2的index 1开始拷贝result2.length个元素到result2数组中，从0开始放
System.arraycopy(array2, 1, result2, 0, result2.length);
System.out.println(Arrays.toString(result2));
```
- 使用stream函数求最大值，最小值
```
 //array求和
 int[] arr = {1, 2, 3};
 int sum = Arrays.stream(arr).sum();
 System.out.println(sum);
 //array求最大值
 int maxValue = Arrays.stream(arr).max().getAsInt();
 System.out.println("maxValue:" + maxValue);
```

- 使用Arrays sort排序
```
//默认升序，可以用基本类型
Arrays.sort(arr);
//自定义lamda表达式使用Comparator接口实现自定义排序，必须使用类作为排序对象传个Comparator对象
Integer[] boxedArr = {1, 2, 3};
Arrays.sort(boxedArr, (obj1, obj2) -> {
     return obj2 - obj1;
});
```
- 排序二维数组
```
int[][] multiArr = {{1, 4}, {1, 3}};
//根据数组下标为1的值从小到大排序
Arrays.sort(multiArr, (obj1, obj2) -> obj1[1] - obj2[1]);
```
### 使用Set集合去重
- 使用TreeSet获取去重并排序好的Set
```
 int[] arr3 = {1, 3, 2, 1, 3, 5, 1, 6};
 SortedSet<Integer> sortedSet = new TreeSet<>();
 for (int i = 0; i < arr3.length; i++) {
     sortedSet.add(arr3[i]);
 }
 System.out.println(Arrays.toString(sortedSet.toArray()));
// [1, 2, 3, 5, 6]
```
使用HashSet获取的是无序的
Set无法获取下标指定的元素，需要转成List或者Array才能操作

### List集合类的基本操作
- 一维数组转List
Integer类数组转List： 
```
Integer[] arr={1,2,3}
List<Integer> list=new ArrayList<>(Arrays.asList(arr))
```
int基本数据类型数组转List(需要使用stream,boxed函数)：
```
int[] arr={1,2,3}
List<Integer> list=Arrays.stream(arr).boxed().collect(Collectors.toList());
```
- List转一维数组
转Integer数组(需要承接数组)
```
List<Integer> list=new ArrayList()
Integer[] list2Arr1=new Integer[list.size()];
list2Arr1=list.toArray(list2Arr1);
```
转int数组(需要stream mapToInt方法)
```
int[] list2Arr2 = list.stream().mapToInt(item -> item).toArray();
System.out.println(Arrays.toString(list2Arr2));
```
- List批量添加数据方式
写在一行调用多次add方法(初始化时后面跟两层花括号)
```
List<String> test2 = new ArrayList<String>() {{
	add("test1");
}};
```
使用Collecitons的addAll方法
```
Collections.addAll(test2, "test2", "test3");
```
- List的equals和contains方法
equals会调用List中所有的元素按序对比，只有所有的全部相等并且没有剩余才返回true
contains方法调用了equals方法进行判定元素是否在List中存在
- List删除值，需要使用Iterator进行操作
(实现了每次remove一个值后都将后面的值移到前面来, remove需要在next方法后调用)
```
Iterator<String> iterator=stringList.iterator()
while(iterator.hasNext()){
	String curValue=iterator.next();
	iterator.remove()
}
```
- List使用add实现头查法
```
List<String> arrayList=new ArrayList<>(Arrays.asList(new String[]{"abc", "dce"}));
//下标1及其之后的元素会后移
arrayList.add(1,"test");
//linkedList同样可以实现
List<String> linkedList = new LinkedList<>(Arrays.asList(new String[]{"abc", "dce"}));
linkedList.add(1,"test2");
```

- List排序
```
Collections.sort(newList3, (obj1, obj2) -> {
      return obj2 - obj1;
});
```


### List数组（用于数组中存储List）
- List数组，其他类型的集合数组也可参考这个例子
```
List[] listArr3 = new ArrayList[2];
for(int i=0;i<listArr3.length;i++){
   listArr3[i]=new ArrayList<String>();
}
//从list数组总获取对应的list
List<String> curList=(ArrayList<String>)listArr3[1];
Collections.addAll(curList,"test1","test2");
System.out.println(Arrays.toString(listArr3[1].toArray()));
```

### Map字典
- 基本put,get
```
Map<Character, Character> map = new HashMap<>();
//支持key为null
map.put(null, 'a');
// :a
System.out.println(map.get(null));
// : null， key不存在的时候返回null
System.out.println("get not exits: "+map.get('('));
//如果不存在取默认值D
System.out.println("get or default: "+map.getOrDefault('(','D'));
```
- 是否包含Key或者Value
```
//是否包含Key或者Value
if(map.containsKey(null)){
  System.out.println("containsKey:"+null);
}
if(map.containsValue('a')){
  System.out.println("containsValue:"+'a');
}
```
- 使用keySet()或者values获取对应值
```
for(Character key: map.keySet()){
	System.out.print(key+",");
}
System.out.println();
for(Character value:map.values()){
	System.out.print(value+",");
}
System.out.println();
```
- 根据Key或者Value排序
```
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
```
```
//根据Value排序可以使用entrySet()方法获取对应的Map Entry对象Set，
//并将其转成List，使用Collections.sort(List)进行自定义排序
List<Map.Entry<Integer, int[]>> entrySets = new ArrayList<>(map2.entrySet());
Collections.sort(entrySets, (entry1, entry2) -> entry1.getValue()[0] - entry2.getValue()[0]);
```

### Java中堆的实现PriorityQueue
- 基本增加，弹出，删除，遍历操作
```
// 默认小根堆，可以通过传入Comparator对象进行建立大根堆
PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((v1, v2) -> v1.compareTo(v2));
//使用offer添加元素
priorityQueue.offer(4);
priorityQueue.offer(1);
priorityQueue.offer(5);
priorityQueue.offer(6);
//使用poll()获取堆顶元素，并且弹出
System.out.println("poll:" + priorityQueue.poll());
//使用peek()函数获取堆顶元素，不弹出
System.out.println("poll:" + priorityQueue.peek());
//使用remove删除元素
priorityQueue.remove(4);
```

### 字符串相关
- 字符数组和String相互转换
```
//char数组转String
char[] charArr = new char[10];
charArr[0] = 'a';
charArr[1] = 'b';
String resultStr=new String(charArr);
//String转Char数组
```
- 使用StringBuilder操作字符串
```
//使用append添加，使用delteCharAt(index)删除指定位置的字符
 StringBuilder builder = new StringBuilder("abc");
 builder.append("de");
 //: abcde
 System.out.println(builder.toString());
 builder.deleteCharAt(builder.length() - 1);
 //: abcd
 System.out.println(builder.toString());
```
- 字符串取子串，寻找指定char的下标
```
 //String取子串
 String str = "0123456";
 //output:012345
 System.out.println(str.substring(0,6));
 
 //获取某个字符的下标
 //output:0
 System.out.println(str.indexOf('0'));
 //output:-1
 System.out.println(str.indexOf('0', 3));
```

### Stack栈
- stack堆栈相关操作
```
Stack<String> stack = new Stack<>();
stack.push("abc");
if(!stack.isEmpty()) {
   //peek方法应该在不为空的情况下调用，否则会报异常
   System.out.println(stack.peek());
   System.out.println(stack.pop());
}
```

### Queue队列
- 队列一般使用LinkedList()实现类
主要包括，offer,peek,pool等操作
```
 Queue<String> queue=new LinkedList<>();
 queue.offer("test1");
 queue.offer("test2");
 queue.offer("test3");
 if(!queue.isEmpty()){
    System.out.println(queue.peek());
    System.out.println(queue.poll());
 }
```
- LinkedList本质上市双向链表，可以实现两端操作
主要操作是offerLast(等于offer),offerFirst,peekFirst(等于peek),peekLast,pollFirst(等于poll),pollLast
```
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
```
## 双端队列Deque
ArrayDeque是用循环数组实现，没有最大容量限制，会自动扩容，有容量需求的话可以自己实现
主要API包括offerFirst,offerLast,peekFirst,peekLast,pollFirst,pollLast
```
//双端队列
Deque<Integer> deque=new ArrayDeque<>(2);
deque.offerLast(1);
deque.offerLast(2);
deque.offerLast(3);
// output: 1 2 3
while (!deque.isEmpty()){
   System.out.println(deque.pollFirst());
}
```
### 其他小点
- null不能和基本类型比较
```
System.out.println(null == (Character) 'x');
```

- 逻辑运算符优先级注意
```
//位运算的优先级小于==，!=，<等比较操作符
if ((testOperator & 1) == 1) {
      System.out.println("test");
}
//移位运算符优先级小于+，-，*，/
System.out.println((4 << 1) + 1); // 9
System.out.println(4 << 1 + 1); //16
```