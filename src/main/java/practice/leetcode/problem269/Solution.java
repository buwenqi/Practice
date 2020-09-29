package practice.leetcode.problem269;

import java.util.*;

/**
 * Created by wenqi on 2020/9/23.
 *
 *  Alien Dictionary 另类字典
 *
 *
 * There is a new alien language which uses the latin alphabet.
 * However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary,
 * where words are sorted lexicographically by the rules of this new language.
 * Derive the order of letters in this language.
 *
 * Example 1:
 *
 * Input:
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 *
 * Output: "wertf"
 * Example 2:
 *
 * Input:
 * [
 *   "z",
 *   "x"
 * ]
 *
 * Output: "zx"
 * Example 3:
 *
 * Input:
 * [
 *   "z",
 *   "x",
 *   "z"
 * ]
 *
 * Output: ""
 *
 * Explanation: The order is invalid, so return "".
 * Note:
 *
 * You may assume all letters are in lowercase.
 * You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 *
 * 参考：https://www.cnblogs.com/grandyang/p/5250200.html
 */
public class Solution {


    public static void main(String[] args){
        List<String> words=new ArrayList<>();
        words.add("wrt");
        words.add("wrf");
        words.add("er");
        words.add("ett");
        //words.add("rftt");
        Solution solution=new Solution();
        System.out.println(solution.alienOrderDFS(words));
    }

    public String alienOrder(List<String> words){
        //思路：很明显的拓扑排序，难点在于如何判定不合法，以及如何从words构建图
        //判定不合法，不合法的字符序列有两种，
        // 一种是出现环，即出现了原来序列的一种逆序,可以通过最终结果字符数量和原有的字符数量对比，必须包含所有的字符数才返回正常
        //另一种是出现了更长的字符串在前（比如aba,在ab前），可以在读入图的时候判定长度
        //从words构建图：由于是拍好序列的，只需从相邻的两个字符中提取对应的值,如何取相邻值的对应字符

        Map<Character,List<Character>> graph=new HashMap<>();
        Map<Character,Integer> indegree=new HashMap<>();
        //最终的结果必须包含所有的字符，用于graph的长度判断
        for(int i=0;i<words.size();i++){
            String curWord=words.get(i);
            for(int j=0;j<curWord.length();j++){
                char curChar=curWord.charAt(j);
                if(!graph.containsKey(curWord.charAt(j))) graph.put(curChar,new ArrayList<Character>());
                if(!indegree.containsKey(curChar)) indegree.put(curChar,0);
            }
        }
        //读入图，并对长度上的不合法判定
        for(int i=0;i<words.size()-1;i++){
            //当前i和i+1之间对比
            int minSize=Math.min(words.get(i).length(),words.get(i+1).length());
            int j=0;
            for(;j<minSize;j++){
                if(words.get(i).charAt(j)!=words.get(i+1).charAt(j)){
                    char curChar=words.get(i).charAt(j);
                    char nextChar=words.get(i+1).charAt(j);
                    List<Character> curList=graph.get(curChar);
                    curList.add(nextChar);
                    int nextIndegree=indegree.get(nextChar);
                    indegree.put(nextChar,nextIndegree+1);
                    break;
                }
            }
            //如果前面字符都相同，但是字典序列在后面的反而更长，则不合法
            if(j==minSize && words.get(i).length()>words.get(i+1).length()) return "";
        }

        //图已建立完毕，开始使用拓扑排序
        StringBuilder builder=new StringBuilder();
        Queue<Character> queue=new LinkedList<>();
        for(Character key: indegree.keySet()){
            if(indegree.get(key)==0){
                queue.offer(key);
            }
        }

        while(!queue.isEmpty()){
            //每一次肯定只有一个度数为0的，不然原来的字符串肯定不合法
            if(queue.size()>1) return "";
            char curChar=queue.poll();
            builder.append(curChar);
            List<Character> curList=graph.get(curChar);
            for(Character subChar:curList){
                int subIndegree=indegree.get(subChar);
                indegree.put(subChar,subIndegree-1);
                if(subIndegree-1==0) queue.offer(subChar);
            }
        }

        return builder.length()==graph.size()?builder.toString():"";
    }

    /**
    * 使用深度优先搜索也可以，方法是每次遍历的一个点的时候检查遍历其前驱节点，向前遍历
     * 如此一来，不论原来从哪个点开始，都可以获得正确的结果，详细参见连接
     * https://www.cnblogs.com/grandyang/p/5250200.html
     *
     * 此方法无法判定多个入度为0的情况，该题目保证其这方面的合法性
     * 例如r->t->f以及w->e会输出rtwef，其为拓扑排序的合法序列，但不是题目的合法序列，题目合法序列具有唯一性
     */
    StringBuilder builder;
    public String alienOrderDFS(List<String> words){
        //由于需要快速遍历遍历当前节点的前面的节点，更改邻接链表的存储方式
        // (List中存储的不是子节点，而是其父节点)
        Map<Character,List<Character>> graph=new HashMap<>();
        Map<Character,Integer> status=new HashMap<>();
        for(String word:words){
            for(int i=0;i<word.length();i++){
                char curChar=word.charAt(i);
                if(!graph.containsKey(curChar)){
                    graph.put(curChar,new ArrayList<Character>());
                    //status有两种状态，0是未访问，1是待子节点访问返回，2是已加入访问完毕并已加入结果集
                    status.put(curChar,0);
                }
            }
        }

        for(int i=0;i<words.size()-1;i++){
            String curWord=words.get(i);
            String nextWord=words.get(i+1);
            int minSize=Math.min(curWord.length(),nextWord.length());
            for(int j=0;j<minSize;j++){
                if(curWord.charAt(j)!=nextWord.charAt(j)){
                    //邻接链表存储的是其父节点的情况，因为深度优先一个点的时候需要穷尽其父节点,这里也需要记录3中状态
                    //后者存储其子节点，用栈存储结果，但是需要标志一个节点的多种状态（未访问，访问子节点状态中，已经加入结果中）
                    List<Character> preList=graph.get(nextWord.charAt(j));
                    preList.add(curWord.charAt(j));
                    break;
                }
            }
        }

        builder=new StringBuilder();
        for(Character item: graph.keySet()){
            if(!dfsIterate(item,graph,status)) return "";
            System.out.println("item:"+item+","+builder.toString());
        }

        return builder.length()==graph.size()?builder.toString():"";
    }

    public boolean dfsIterate(Character item,Map<Character,List<Character>> graph,Map<Character,Integer> status){
        if(status.get(item)==2) return true;
        status.put(item,1);
        for(Character preChar: graph.get(item)){
            //出现了环
            if(status.get(preChar)==1) return false;
            if(!dfsIterate(preChar,graph,status)) return false;
        }
        status.put(item,2);
        builder.append(item);
        return true;
    }

}
