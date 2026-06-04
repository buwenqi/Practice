package practice.test2;
import java.util.*;
/**
 * 要求trips里面每个结点有且仅走一次，如果能返回路径，否则返回null，哈密顿Hamiltonian Path等同问题(NP-Complete问题，跟TSP旅行商问题类似)
 * 时间复杂度O(n!)
 * 空间复杂度n+e
 */
public class TripWithAllNodeOnceDFS {
    public static void main(String[] args)
    {
        String[][] trips=new String[][]{{"A","B"},{"A","C"},{"B","A"},{"B","C"}};
        List<String> result = getTripPath(trips, "A");
        System.out.println(Arrays.toString(result.toArray()));

        String[][] trips2=new String[][]{{"A","B"},{"A","C"}};
        List<String> result2 = getTripPath(trips2, "A");
        System.out.println(Arrays.toString(result2==null?null:result2.toArray()));
    }

    public static List<String> getTripPath(String[][] trips, String start)
    {
        Map<String, List<String>> graph = new HashMap<>();
        HashSet<String> visited=new HashSet<>();
        for(String[] trip:trips)
        {
            List<String> curChildList=graph.computeIfAbsent(trip[0], key->new LinkedList<>());
            curChildList.add(trip[1]);
            //保证每个结点都对应一个list
            graph.computeIfAbsent(trip[1], key->new LinkedList<>());
        }

        if(!graph.containsKey(start)) return null;
        LinkedList<String> path = new LinkedList<>();
        visited.add(start);
        path.add(start);
        if(!dfsGetTripPath(start, graph, visited, path)) return null;
        return path;
    }

    public static boolean dfsGetTripPath(String curNode, Map<String, List<String>> graph, HashSet<String> visited, LinkedList<String> path)
    {
        if(path.size()==graph.size()) return true;
        for(String curChild: graph.getOrDefault(curNode,new ArrayList<>()))
        {
            if(visited.contains(curChild)) continue;
            visited.add(curChild);
            path.offerLast(curChild);
            boolean subResult=dfsGetTripPath(curChild, graph, visited, path);
            if(subResult) return true;
            visited.remove(curChild);
            path.pollLast();
        }
        return false;
    }
    
}
