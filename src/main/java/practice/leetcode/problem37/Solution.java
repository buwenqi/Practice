package practice.leetcode.problem37;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wenqi on 2020/8/14.
 */
class Solution {
    public static void main(String[] args){
        char[][] board={{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        Solution solution=new Solution();
        solution.solveSudoku(board);
        System.out.println(Arrays.toString(board));
    }
    public void solveSudoku(char[][] board) {
        Set[][] spaceSets=new HashSet[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                spaceSets[i][j]=new HashSet<Character>();
            }
        }
        Set[] rowSets=new HashSet[9];
        Set[] colSets=new HashSet[9];
        for(int i=0;i<9;i++){
            rowSets[i]=new HashSet<Character>();
            colSets[i]=new HashSet<Character>();
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]!='.'){
                    HashSet<Character> curSet=(HashSet<Character>)spaceSets[i/3][j/3];
                    HashSet<Character> curRowSet=(HashSet<Character>)rowSets[i];
                    HashSet<Character> curColSet=(HashSet<Character>)colSets[j];
                    curSet.add(board[i][j]);
                    curRowSet.add(board[i][j]);
                    curColSet.add(board[i][j]);
                }
            }
        }
        dfsPutValue(board,0,0,spaceSets,rowSets,colSets);
        return;
    }

    public void dfsPutValue(char[][] board,int curRow,int curCol,Set[][] spaceSets,Set[] rowSets,Set[] colSets){
        if (curRow < 0 || curCol < 0 || curRow >= board.length || curCol >= board[0].length) {
            return;
        }

        if (board[curRow][curCol] == '.') {
            HashSet<Character> curSpaceSet = (HashSet<Character>) spaceSets[curRow / 3][curCol / 3];
            HashSet<Character> curRowSet = (HashSet<Character>) rowSets[curRow];
            HashSet<Character> curColSet = (HashSet<Character>) colSets[curCol];
            for (int value = 1; value <= 9; value++) {
                char curChar = (char) ('0' + value);
                if (curSpaceSet.contains(curChar) || curRowSet.contains(curChar) || curColSet.contains(curChar)) {
                    continue;
                } else {
                    //尝试放入，并且递归下一个点
                    System.out.println("curRow:" + curRow + ",curCol:" + curCol + ",choose:" + curChar);
                    board[curRow][curCol] = curChar;
                    curSpaceSet.add(curChar);
                    curRowSet.add(curChar);
                    curColSet.add(curChar);

                    int[] nextPosition = getNextPosition(curRow, curCol, board.length, board[0].length);
                    int nextRow = nextPosition[0];
                    int nextCol = nextPosition[1];
                    dfsPutValue(board, nextRow, nextCol, spaceSets, rowSets, colSets);

                    curSpaceSet.remove(curChar);
                    curRowSet.remove(curChar);
                    curColSet.remove(curChar);
                    board[curRow][curCol] = '.';
                }
            }
        } else {
            int[] nextPosition = getNextPosition(curRow, curCol, board.length, board[0].length);
            int nextRow = nextPosition[0];
            int nextCol=nextPosition[1];
            dfsPutValue(board,nextRow,nextCol,spaceSets,rowSets,colSets);
        }
    }

    public int[] getNextPosition(int curRow,int curCol,int rowSize, int colSize){
        int[] nextPosition=new int[2];
        if(curCol<colSize-1){
            curCol++;
        }else{
            curCol=0;
            curRow++;
        }
        nextPosition[0]=curRow;
        nextPosition[1]=curCol;
        //if(curRow>=1)
        //System.out.println("newRow:"+curRow+",nextCol:"+curCol);
        return nextPosition;
    }
}