package practice.leetcode.problem444;

import java.util.*;

/**
 * 444. Sequence Reconstruction 序列重建
 *
 *
 * Check whether the original sequence org can be uniquely reconstructed from the sequences
 * in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104.
 * Reconstruction means building a shortest common supersequence of the sequences in seqs
 * (i.e., a shortest sequence so that all sequences in seqs are subsequences of it).
 * Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
 *
 * Example 1:
 *
 * Input:
 * org: [1,2,3], seqs: [[1,2],[1,3]]
 *
 * Output:
 * false
 *
 * Explanation:
 * [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
 * Example 2:
 *
 * Input:
 * org: [1,2,3], seqs: [[1,2]]
 *
 * Output:
 * false
 *
 * Explanation:
 * The reconstructed sequence can only be [1,2].
 * Example 3:
 *
 * Input:
 * org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
 *
 * Output:
 * true
 *
 * Explanation:
 * The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
 * Example 4:
 *
 * Input:
 * org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
 *
 * Output:
 * true
 *
 * 参考：http://bookshadow.com/weblog/2016/10/30/leetcode-sequence-reconstruction/
 */
public class Solution {

    public static void main(String[] args){
        Solution solution=new Solution();
        Integer[] curOrg={4,1,5,2,6,3};
        List<Integer> org=new ArrayList<>(Arrays.asList(curOrg));
        List<List<Integer>> seqs=new ArrayList<>();
        seqs.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{5,2,6,3})));
        seqs.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{4,1,5,2})));
        //seqs.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{2,3})));
        boolean result=solution.sequenceReconstruction(org,seqs);
        System.out.println(result);
    }

    public boolean sequenceReconstruction(List<Integer> org,List<List<Integer>> seqs){
        //理解：即求seqs序列所组成的有向图的拓扑排序是否可以唯一输出org序列
        //思路：根据seqs建立有向图，进行苛刻的拓扑排序（每次仅允许并且只有一个度数为0的合法选择），否则返回false

        //从1开始存放
        int maxNodeNum=org.size();
        List[] graph=new List[maxNodeNum+1];
        int[] indegree=new int[maxNodeNum+1];
        for(int i=1;i<graph.length;i++){
            graph[i]=new ArrayList<Integer>();
            indegree[i]=0;
        }
        for(List<Integer> seq:seqs){
            int preNode=seq.get(0);
            if(preNode>maxNodeNum) return false;
            for(int i=1;i<seq.size();i++){
                int curNode=seq.get(i);
                if(curNode>maxNodeNum) return false;
                List<Integer> curList=(List<Integer>)graph[preNode];
                curList.add(curNode);
                indegree[curNode]++;
                preNode=curNode;
            }
        }

        //已构建邻接表即相关入度情况，将度数为0的加入队列并检验
        Queue<Integer> queue=new LinkedList<>();
        for(int i=1;i<graph.length;i++){
            if(indegree[i]==0){
                queue.offer(i);
            }
        }
        if(queue.size()>1) return false;
        for(int i=1;i<graph.length;i++){
            //每次必须存在一个入度为0且org位置一致的字符
            if(queue.isEmpty() || queue.size()>1) return false;
            int curNode=queue.poll();
            List<Integer> curList=(List<Integer>)graph[curNode];
            for(Integer curNext: curList){
                indegree[curNext]--;
                if(indegree[curNext]==0) queue.offer(curNext);
            }
        }

        return true;
    }
}
