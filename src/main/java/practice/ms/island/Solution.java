package practice.ms.island;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenqi on 2020/12/15.
 * 岛屿的数量，按行给出的解法
 */
public class Solution {
    public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0) return 0;
        //使用并查集按行统计
        int rows=grid.length;
        int cols=grid[0].length;
        List<Integer> unionFind=new ArrayList<>();

        char[] preRowData=new char[cols];
        char[] curRowData=new char[cols];
        int curRow=0;
        while(curRow<rows){
            //读入每一行
            curRowData=grid[curRow];
            for(int curCol=0;curCol<cols;curCol++){
                int curIndex=curRow*cols+curCol;
                //看左和上的根,进行连通，左边的指向当前，当前的指向上一层
                if(curRowData[curCol]=='1'){
                    //初始化并查集根为-1
                    unionFind.add(-1);
                    //连通上
                    if(curRow>0 && preRowData[curCol]=='1'){
                        int preRowIndex=curIndex-cols;
                        mergeRoot(unionFind,curIndex,preRowIndex);
                    }
                    //连通左边
                    if(curCol>0 && curRowData[curCol-1]=='1'){
                        int preColIndex=curIndex-1;
                        mergeRoot(unionFind,preColIndex,curIndex);
                    }
                }else{
                    //是0的情况标志位-2
                    unionFind.add(-2);
                }
            }
            //System.out.println(Arrays.toString(unionFind.toArray()));
            preRowData=curRowData;
            curRow++;
        }

        //检查所有的1，并且是根的结果是-1的情况
        int ans=0;
        for(int i=0;i<unionFind.size();i++){
            if(unionFind.get(i)==-1) ans++;
        }
        return ans;
    }


    public void mergeRoot(List<Integer> roots,int index1, int index2){
        int root1=findRoot(roots,index1);
        int root2=findRoot(roots,index2);
        //System.out.println(root1+":"+root2);
        if(root1!=root2){
            roots.set(root1,root2);
        }
    }

    public int findRoot(List<Integer> roots, int index){
        if(roots.get(index)==-1) return index;
        int oldRoot=roots.get(index);
        int returnRoot=findRoot(roots,oldRoot);
        roots.set(index,returnRoot);
        return returnRoot;
    }

}
