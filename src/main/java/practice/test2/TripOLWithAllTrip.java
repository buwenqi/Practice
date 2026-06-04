package practice.test2;
import java.util.*;

/**
 * 要求一次从start走到所有的路径trip，欧拉路径等同问题
 */
public class TripOLWithAllTrip {

    public static void main(String[] args)
    {
        String[][] trips=new String[][]{{"A","B"},{"A","C"},{"B","D"}};
        List<String> result = getPathGoThroughAllTrips(trips,"A");
        System.out.println(result==null?"null":Arrays.toString(result.toArray()));
    }

    public static List<String> getPathGoThroughAllTrips(String[][] trips, String startNode)
    {
        Map<String,LinkedList<String>> graph =new HashMap<>();
        Map<String,Integer> indegree=new HashMap<>();
        Map<String,Integer> outdegree=new HashMap<>();
        for(String[] trip:trips)
        {
            LinkedList<String> curChild=graph.computeIfAbsent(trip[0],key->new LinkedList<>());
            curChild.add(trip[1]);
            indegree.put(trip[1], indegree.getOrDefault(trip[1],0)+1);
            outdegree.put(trip[0], outdegree.getOrDefault(trip[0],0)+1);
        }

        int startCount=0;
        int endCount=0;
        //startCount and endCount at most 1
        for(String nodeName: graph.keySet())
        {
            int curOutdegree=outdegree.getOrDefault(nodeName,0);
            int curIndegree=indegree.getOrDefault(nodeName,0);
            if(curOutdegree-curIndegree==1)
            {
                if(!nodeName.equals(startNode)) return null;
                startCount++;
            }
            else if(curIndegree-curOutdegree==1) endCount++;
            else if(curIndegree!=curOutdegree) return null;
        }
        if(startCount>1|| endCount>1) return null;

        //dfs construct the trace
        List<String> path = new LinkedList<>();
        dfsConstruct(startNode, graph, path);
        if(path.size()!=trips.length+1) return null;
        return path.reversed();
    }

    public static void dfsConstruct(String curNode,Map<String,LinkedList<String>> graph, List<String> path)
    {
        LinkedList<String> curChildList=graph.get(curNode);
        while (curChildList!=null && !curChildList.isEmpty()) {
            String curChild=curChildList.pollFirst();
            dfsConstruct(curChild, graph, path);
        }
        path.add(curNode);
    }

    
}
