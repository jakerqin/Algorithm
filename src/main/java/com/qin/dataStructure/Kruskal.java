package com.qin.dataStructure;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * <pre>
 * 使用kruskal算法生成图的MST
 * 其中：
 * 1>.图的顶点存在了数组中，存在是int
 * 2>.图的带权边使用了EdgeNode对象，存在了数组中
 * 3>.边按照权重进行排序利用最小堆进行排序，每次取出最小堆的根节点，便是权最小的边
 * 4>.每次向MST中添加边肯定添加最小权的边，唯一条件便是不构成环
 * 5>.上述不构成环是利用等价类<并查集>实现的
 * 6>.并查集实现：树！每个顶点两个域：parent域&root域!find-union!重量规则！</pre>
 * @author qinlianji
 *
 */
public class Kruskal {

    boolean[] root;
    int[] parent;
    int currentSize=0;
    int maxSize=0;
    EdgeNode[] minHeap=new EdgeNode[20];

    /**
     * 初始化每个顶点为一个类
     * @param verNum 顶点的数量
     */
    public void initialize(int verNum){
        root=new boolean[verNum+1];
        parent=new int[verNum+1];

        for(int vertex=1;vertex<=verNum;vertex++){
            parent[vertex]=1;
            root[vertex]=true;
        }
    }

    /**
     * 寻找某个顶点元素所在的类
     * @param vertex 顶点
     * @return 返回的是顶点所在的类
     */
    public int find(int vertex){
        while(!root[vertex]){
            vertex=parent[vertex];
        }
        return vertex;
    }

    /**
     * 利用重量规则将两个根节点为i,j的类合并
     * @param i 根节点为i
     * @param j 根节点为j
     */
    public void union(int i,int j){
        if(parent[i]<parent[j]){
            parent[j]+=parent[i];
            root[i]=false;
            parent[i]=j;
        }else{
            parent[i]+=parent[j];
            root[j]=false;
            parent[j]=i;
        }
    }

    /**
     * 通过weight构建以EdgeNode为节点的最小堆
     * @param edgeNode为带权的边集
     */
    public void createMinHeap(EdgeNode[] edgeNode){
        currentSize=edgeNode.length;
        maxSize=minHeap.length;
        if(currentSize>=maxSize){
            maxSize*=2;
            minHeap=new EdgeNode[maxSize];
        }
        for(int i=0;i<currentSize;i++)
            minHeap[i+1]=edgeNode[i];

        int y,c;
        for(int i=currentSize/2;i>=1;i--){
            EdgeNode node=minHeap[i];
            y=node.weight;
            c=2*i;
            while(c<currentSize){
                if(c<=currentSize && minHeap[c].weight>minHeap[c+1].weight)
                    c++;
                if(minHeap[c].weight>=y)
                    break;
                minHeap[c/2]=minHeap[c];
                c=c*2;
            }
            minHeap[c/2]=node;
        }
    }

    /**
     * 最小堆删除两种思路，一种和前面一样，就是一直跟踪放在根节点的那个最后一个节点最终插入的位置
     * 另一种思路便是每一次完成完整的交换然后下一一层在进行同样处理
     */
    public EdgeNode deleteMinHeap(){
        if(currentSize<1)
            System.out.println("堆已经为空！无法执行删除");
        EdgeNode node=minHeap[1];
        minHeap[1]=minHeap[currentSize];
        currentSize-=1;

        int c=2,j=1;
        EdgeNode node1=minHeap[currentSize+1];
        while(c<=currentSize){
            if(c<currentSize && minHeap[c].weight>minHeap[c+1].weight)
                c++;
            if(node1.weight<=minHeap[c].weight)
                break;
            minHeap[j]=minHeap[c];
            j=c;
            c=c*2;
        }
        minHeap[j]=node1;
        return node;
    }

    /**
     * 根据图的顶点集合带权边集生成MST
     * @param verArray 顶点集
     * @param edgeNode 带权边集
     */
    public void minSpanningTree(int[] verArray,EdgeNode[] edgeNode){
        ArrayList<EdgeNode> nodeList=new ArrayList<EdgeNode>();

        initialize(verArray.length);
        createMinHeap(edgeNode);

        for(int i=1;i<=currentSize;i++){
            System.out.println(minHeap[i].u+" "+minHeap[i].v+" "+minHeap[i].weight);
        }

        for(int i=0;i<edgeNode.length;i++){
            EdgeNode node=deleteMinHeap();
            int jRoot=find(node.u);
            int kRoot=find(node.v);
            if(jRoot!=kRoot){
                nodeList.add(node);
                union(jRoot,kRoot);
            }
        }
        System.out.println("使用Kruskal算法得到图的最小生成树为：");
        for(int i=0;i<nodeList.size();i++){
            System.out.println(nodeList.get(i).u+" "+nodeList.get(i).v+" "+nodeList.get(i).weight);
        }
    }

    public static void main(String[] args) {
        System.out.println("请输出图的顶点数和边数：");
        @SuppressWarnings("resource")
        Scanner scan=new Scanner(System.in);
        int verNum=scan.nextInt();
        int edgeNum=scan.nextInt();

        int[] verArray=new int[verNum];
        System.out.println("请依次输入顶点：");
        for(int i=0;i<verNum;i++){
            int vertex=scan.nextInt();
            verArray[i]=vertex;
        }

        EdgeNode[] edgeNode=new EdgeNode[edgeNum];
        System.out.println("请依次输入边的顶点和权重：");
        for(int i=0;i<edgeNum;i++){
            int u=scan.nextInt();
            int v=scan.nextInt();
            int weight=scan.nextInt();
            EdgeNode node=new EdgeNode();
            node.u=u;
            node.v=v;
            node.weight=weight;
            edgeNode[i]=node;
        }
        Kruskal kruskal=new Kruskal();
        kruskal.minSpanningTree(verArray,edgeNode);
    }
}
