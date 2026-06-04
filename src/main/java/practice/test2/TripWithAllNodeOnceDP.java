package practice.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TripWithAllNodeOnceDP {
    public static void main(String[] args)
    {
        String[][] trips=new String[][]{{"A","B"},{"A","C"},{"B","A"},{"B","C"}};
        List<String> result = getTripPath(trips, "A");
        System.out.println(Arrays.toString(result==null?null:result.toArray()));

        String[][] trips2=new String[][]{{"A","B"},{"A","C"}};
        List<String> result2 = getTripPath(trips2, "A");
        System.out.println(Arrays.toString(result2==null?null:result2.toArray()));
    }

    public static List<String> getTripPath(String[][] trips, String start)
    {
        Map<String, Integer> nodeToIndex=new HashMap<>();
        List<String> indexToNode=new LinkedList<>();
        int nodeCount=0;
        for(String[] trip:trips)
        {
            if(!nodeToIndex.containsKey(trip[0]))
            {
                nodeToIndex.put(trip[0],nodeCount++);
                indexToNode.add(trip[0]);
            }
            if(!nodeToIndex.containsKey(trip[1]))
            {
                nodeToIndex.put(trip[1],nodeCount++);
                indexToNode.add(trip[1]);
            }

        }

        List<Integer>[] graph=new List[nodeCount];
        for(int i=0;i<nodeCount;i++) graph[i]=new ArrayList<>();
        for(String[] trip:trips)
        {
            int from=nodeToIndex.get(trip[0]);
            int to = nodeToIndex.get(trip[1]);
            graph[from].add(to);
        }
        int startIndex=nodeToIndex.get(start);
        List<String> result = getPathWithDPMethold(nodeCount,startIndex,nodeToIndex,indexToNode,graph);
        return result;
    }

    public static List<String> getPathWithDPMethold(int nodeCount,int startIndex,Map<String, Integer> nodeToIndex,List<String> indexToNode,List<Integer>[] graph)
    {
        //[i][j]代表以j结尾的经过i集合的是不是一条合法的路径
        int maskCount=(1<<nodeCount);
        boolean[][] dp = new boolean[maskCount][nodeCount];
        //start自己到自己是合法的路径
        dp[1<<startIndex][startIndex]=true;
        int[][] parents=new int[maskCount][nodeCount];
        for(int[] parent:parents) Arrays.fill(parent, -1);

        //遍历所有mask的集合
        for(int mask=0;mask<maskCount;mask++)
        {
            for(int i=0;i<nodeCount;i++)
            {
                //如果到i本身就不合法，直接跳过
                if(!dp[mask][i]) continue;
                //i should in the mask collection
                if((mask&(1<<i))==0) continue;
                // i所有的邻接边遍历
                for(int j:graph[i])
                {
                    //j should not in the mask collection
                    if((mask&(1<<j))!=0) continue;
                    int nextMask=(mask|(1<<j));
                    //之前没更新过true的才更新
                    if(!dp[nextMask][j])
                    {
                        dp[nextMask][j]=true;
                        parents[nextMask][j]=i;
                    }
                }
            }
        }

        int end=-1;
        for(int i=0;i<nodeCount;i++)
        {
            if(dp[maskCount-1][i])
            {
                end=i;
                break;
            }
        }
        if(end==-1) return null;
        List<String> resuList =new ArrayList<>();
        int curNode=end;
        int curMask=maskCount-1;
        while (curNode!=-1) {
            resuList.add(indexToNode.get(curNode));
            int preNode=parents[curMask][curNode];
            curMask=(curMask^(1<<curNode));
            curNode=preNode;
        }
        return resuList.reversed();
    }
}
