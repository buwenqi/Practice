package practice.test;

import javax.swing.text.AbstractDocument;
import java.io.BufferedReader;
import java.lang.reflect.WildcardType;

/**
 * Created by wenqi on 2020/8/16.
 * 学习二维数组的不同遍历方式
 */
public class Test2Arr {
    public static void main(String[] args){
        int[][] arr={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16},{17,18,19,20}};
        normalRowIterate(arr);
        circleIterate1(arr);
        circleIterate2(arr);

        //对角线遍历
        leftdiagonalIterate(arr);
        rightDignonalIterate(arr);

        //对角线蛇形遍历
        snakeLeftDiagonalIterate(arr);
    }

    public static void normalRowIterate(int[][] arr){
        int curRow=0;
        int curCol=0;
        int maxColLen=arr[0].length;
        StringBuilder builder=new StringBuilder();
        while (curRow<arr.length){
            builder.append(arr[curRow][curCol]+",");
            if(curCol== maxColLen-1){
                curCol=0;
                curRow++;
            }else{
                curCol++;
            }
            //下一轮的坐标值进行评估是不是需要换行
            if(curRow!=0 && curCol==0){
                builder.deleteCharAt(builder.length()-1);
                builder.append("\n");
            }
        }
        System.out.print(builder.toString());
    }

    /**
     * 回形遍历二维数组，使用标记数组
     * @param arr
     */
    public static void circleIterate1(int[][] arr){
        int rowSize=arr.length;
        int colSize=arr[0].length;
        boolean[][] visited=new boolean[rowSize][colSize];
        //1向右，2往下，3往左，4往上
        int direction=1;
        int curCount=0;
        int curx=0;
        int cury=0;
        StringBuilder result=new StringBuilder();
        while (curCount<rowSize*colSize){
            result.append(arr[curx][cury]+",");
            visited[curx][cury]=true;
            curCount++;
            //判断是否应该转向
            if((cury+1==colSize || visited[curx][cury+1]) && direction==1){
                direction=2;
            }else if((curx+1==rowSize || visited[curx+1][cury]) && direction==2){
                direction=3;
            }else if((cury-1<0 || visited[curx][cury-1]) && direction==3){
                direction=4;
            }else if((curx-1<0 || visited[curx-1][cury]) && direction==4){
                direction=1;
            }

            //开始计算下一个坐标点
            switch (direction){
                case 1:
                    cury++;
                    break;
                case 2:
                    curx++;
                    break;
                case 3:
                    cury--;
                    break;
                case 4:
                    curx--;
                    break;
            }
        }
        System.out.println(result.deleteCharAt(result.length()-1).toString());
    }

    /**
     * 使用层数来定义当前遍历的位置
     * @param arr
     */
    public static void circleIterate2(int[][] arr){
        int rowSize=arr.length;
        int colSize=arr[0].length;

        int curCount=0;
        int curx=0;
        int cury=0;
        //初始层数为0
        //定义在第几层
        int level=0;
        StringBuilder result=new StringBuilder();
        int maxCount=rowSize*colSize;
        while (curCount<maxCount){
            //上层的遍历,列从level->colSize-level-1
            for(int i=level;i<=colSize-level-1 && curCount<maxCount;i++){
                cury=i;
                result.append(arr[curx][cury]+",");
                curCount++;
            }
            //右侧的遍历，行从level+1->rowSize-level-1
            for(int i=level+1;i<=rowSize-level-1 && curCount<maxCount;i++){
                curx=i;
                result.append(arr[curx][cury]+",");
                curCount++;
            }

            //下层的遍历,列从colSize-level-2 -> level
            for(int i=colSize-level-2; i>=level && curCount<maxCount;i--){
                cury=i;
                result.append(arr[curx][cury]+",");
                curCount++;
            }

            //左侧的遍历,列从rowSize-level-2 -> level+1
            for(int i=rowSize-level-2; i>=level+1 && curCount<maxCount;i--){
                curx=i;
                result.append(arr[curx][cury]+",");
                curCount++;
            }
            level++;
        }
        System.out.println(result.deleteCharAt(result.length()-1).toString());
    }

    /**
     * 方向向左的对角线遍历（规律在于行和列的和相同，x+y=constant）,方向向下
     */
    public static void leftdiagonalIterate(int[][] arr){
        int maxRows=arr.length;
        int maxCols=arr[0].length;
        StringBuilder result=new StringBuilder();

        //所有的起始点都在 上+右边界上，总计maxRows+maxCols-1，最后一个的x+y=maxRows+maxCols-2
        int count=0;
        while (count<maxRows+maxCols-1){
            //根据count计算当前的起始点
            // 如果count小于maxCols，则起点x,y应该在上界（0，count）
            // 如果count大于等于maxCols，则x,y起始点应该是右界（count-maxCols+1，maxCols-1)
            int x=(count<maxCols?0:count-maxCols+1);
            int y=(count<maxCols?count:maxCols-1);
            while (x<maxRows && y>=0){
                result.append(arr[x][y]+",");
                x++;
                y--;
            }
            count++;
        }
        System.out.println(result.toString());
    }

    /**
     * 分别遍历右上角和左上角
     * 右下方向，（归类是右对角线上的差相同）
     * @param arr
     */
    public static void rightDignonalIterate(int[][] arr){

        int maxRows=arr.length;
        int maxCols=arr[0].length;
        int upCount=0;
        //右上角
        StringBuilder upResult=new StringBuilder();
        while (upCount<maxCols){
            int x=0;
            int y=upCount;
            while (x<maxRows && y<maxCols){
                upResult.append(arr[x][y]+",");
                x++;
                y++;
            }
            upCount++;
        }
        System.out.println(upResult.toString());

        //左下角
        int downCount=0;
        StringBuilder downResult=new StringBuilder();
        while (downCount<maxRows){
            int x=downCount;
            int y=0;
            while (x<maxRows && y<maxCols){
                downResult.append(arr[x][y]+",");
                x++;
                y++;
            }
            downCount++;
        }
        System.out.println(downResult.toString());
    }


    public static void snakeLeftDiagonalIterate(int[][] arr){
        int maxRows=arr.length;
        int maxCols=arr[0].length;

        int iterateSize=maxRows+maxCols-1;
        int count=0;
        StringBuilder result=new StringBuilder();
        while (count<iterateSize){
            //上行的起始点(左和下边)
            int upRow=(count<maxRows?count:maxRows-1);
            int upCol=(count<maxRows?0:count-maxRows+1);
            while (upRow>=0 && upCol<maxCols){
                result.append(arr[upRow][upCol]+",");
                upRow--;
                upCol++;
            }
            count++;
            if(count>=iterateSize){
                break;
            }
            //下行的起始点（上和右边）
            int downRow=(count<maxCols?0:count-maxCols+1);
            int downCol=(count<maxCols?count:maxCols-1);
            while (downRow<maxRows && downCol>=0){
                result.append(arr[downRow][downCol]+",");
                downRow++;
                downCol--;
            }
            count++;
        }
        System.out.println(result.toString());

    }


    /**
     * 将二维数组旋转90度
     */
    public void rotate(int[][] matrix) {
        //按圈旋转
        int maxIndex=matrix.length-1;
        //最多maxIndex/2圈
        for(int level=0;level<=maxIndex/2;level++){
            for(int j=level;j<maxIndex-level;j++){
                //取出上边的（level,j），存储到历史数组中
                int tmp=matrix[level][j];
                //将左边上的值旋转到上边
                matrix[level][j]=matrix[maxIndex-j][level];
                //将下边的值转到左边
                matrix[maxIndex-j][level]=matrix[maxIndex-level][maxIndex-j];
                //将右边的值放入转到左边
                matrix[maxIndex-level][maxIndex-j]=matrix[j][maxIndex-level];
                //将上面的tmp放入右边
                matrix[j][maxIndex-level]=tmp;
            }
        }
    }

}
