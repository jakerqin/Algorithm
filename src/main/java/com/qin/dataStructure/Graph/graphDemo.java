package com.qin.dataStructure.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 看尚硅谷视频，自己尝试写
 */
public class graphDemo {
    // 存储定点集合
    private ArrayList<String> vertexList;

    // 存储图对应的邻接矩阵
    private int[][] edges ;

    //表示边的数目
    private int numOfEdges;

    //定义给数组boolean[], 记录某个结点是否被访问
    private boolean[] isVisited;

    // 构造器
    public graphDemo(int i) {
        vertexList = new ArrayList<>(i);
        edges = new int[i][i];
        isVisited = new boolean[i];
        numOfEdges = 0;
    }

    /**
     * 返回节点的个数
     * @return
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 返回边的个数
     * @return
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     *  返回结点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回v1和v2的权值
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 插入节点
     * @param name
     */
    public void insertVertex(String name) {
        vertexList.add(name);
    }

    /**
     * 插入边
     * @param v1
     * @param v2
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph() {
        for (int[] arr : edges) {
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 得到第一个临接节点的下标w
     * @return 没有找到返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;

    }

    /**
     * 根据前一个邻接点的下标得到下一个邻接定点
     * @return
     */
    public int getNextNeighbor(int v1,int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历
     * 步骤
     *    访问初始结点v，并标记结点v为已访问。
     *    查找结点v的第一个邻接结点w。
     *    若w存在，则继续执行4，如果w不存在，则回到第1步，将从v的下一个结点继续。
     *    若w未被访问，对w进行深度优先遍历递归（即把w当做另一个v，然后进行步骤123）。
     *    查找结点v的w邻接结点的下一个邻接结点，转到步骤3。
     * @param isVisited
     * @param index
     */
    private void dfs(boolean[] isVisited, int index) {
        System.out.println(getValueByIndex(index) + "-->");
        // 将定点设置成已访问
        isVisited[index] = true;
        // 查找节点V的第一个临接定点w
        int w = getFirstNeighbor(index);
        // 如果w找到
        while (w != -1) {
            if (!isVisited[w]){
                dfs(isVisited, w);
            }
            // 如果w节点已经被访问
            w = getNextNeighbor(index, w);
        }

    }

    /**
     * 对dfs 进行一个重载, 遍历我们所有的结点，并进行 dfs
     */
    public void dfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }


    //对一个结点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i) {
        int u ; // 表示队列的头结点对应下标
        int w ; // 邻接结点w
        //队列，记录结点访问的顺序
        LinkedList queue = new LinkedList();
        //访问结点，输出结点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已访问
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);

        while( !queue.isEmpty()) {
            //取出队列的头结点下标
            u = (Integer)queue.removeFirst();
            //得到第一个邻接结点的下标 w
            w = getFirstNeighbor(u);
            while(w != -1) {//找到
                //是否访问过
                if(!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻结点
                w = getNextNeighbor(u, w); //体现出我们的广度优先
            }
        }

    }

    //遍历所有的结点，都进行广度优先搜索
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for(int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }



    public static void main(String[] args) {

        graphDemo graph = new graphDemo(5);
        String[] nodes = {"A","B","C","D","E"};
        for (String str : nodes) {
            graph.insertVertex(str);
        }
        graph.insertEdge(0, 1, 1); //
		graph.insertEdge(0, 2, 1); //
		graph.insertEdge(1, 2, 1); //
		graph.insertEdge(1, 3, 1); //
		graph.insertEdge(1, 4, 1); //
        graph.dfs();
    }
}
