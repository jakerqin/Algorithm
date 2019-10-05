package com.qin.algorithm.Prim;

import java.util.Arrays;

public class primAlgorimn {

    public static void main(String[] args) {
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int [][]weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},};

        //创建MGraph对象
        MGraph graph = new MGraph(verxs);
        //创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);
        //输出
        minTree.showGraph(graph);
        minTree.prim(graph,0);
    }
}

class MinTree {

    // 初始化邻接表
    public void createGraph(MGraph graph, int vertex, char[] data, int[][] weight) {
        int i,j;
        for (i = 0; i < vertex; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    // 显示邻接表
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }


    /**
     * prim算法，得到最小生成树
     * @param graph
     * @param v 从那个节点开始
     */
    public void prim(MGraph graph, int v) {
        // 标记哪些节点被访问过。
        boolean[] visited = new boolean[graph.vertexs];

        visited[v] = true;
        //h1 和 h2 记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000; //将 minWeight 初始成一个大数，后面在遍历过程中，会被替换
        //因为有 graph.verxs顶点，普利姆算法结束后，有 graph.verxs-1边
        for (int i = 1; i < graph.vertexs; i++) {
            // 下面双层for循环，遍历邻接表中已被访问过的节点，所连节点的最小权值
            for (int k = 0; k < graph.vertexs; k++) {
                for (int j = 0; j < graph.vertexs; j++) {
                    if (visited[k] == true && visited[j] == false && graph.weight[k][j] < minWeight) {
                        minWeight = graph.weight[k][j];
                        h1 = k;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
            //将当前这个结点标记为已经访问
            visited[h2] = true;
            //minWeight 重新设置为最大值 10000
            minWeight = 10000;

        }


    }
}

class MGraph {
    int vertexs; // 图的节点个数
    char[] data; // 存放节点的数据
    int[][] weight; // 存放边，邻接矩阵

    public MGraph(int vertexs) {
        this.vertexs = vertexs;
        data = new char[vertexs];
        weight = new int[vertexs][vertexs];
    }
}
