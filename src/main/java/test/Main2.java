package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main2 {
    /*public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        int[] tree = new int[n];
        int i = 0;
        while (i<n){

        }
        return null;
    }

    private int getSingletonDepth(int currindex,int depth,int[][] edges){
        for (int[] p : edges) {
            for (int i = 0; i < p.length; i++) {
                if (p[i] == currindex){
                    depth++;
                    currindex = p[p.length-i-1];
                }
            }
        }
        return depth;

    }*/
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        int[] degree = new int[n];
        // 待选节点，一开始全部节点，然后将叶子节点逐层删掉
        LinkedList<Integer> result = new LinkedList<>();
        for (int i= 0;i<n;i++) result.push(i);
        if (n<2) return result;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i< n;i++ ){
            graph.add(new ArrayList<>());
        }
        for (int[] p : edges) {
            graph.get(p[0]).add(p[1]);
            graph.get(p[1]).add(p[0]);
            degree[p[0]] +=1;
            degree[p[1]] +=1;
        }
        List<Integer> queue = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.add(i);
                result.remove(i);
            }
        }
        while (result.size() > 2) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                int tmp = queue.remove(0);
                for (int j = 0; j < graph.get(tmp).size();j++) {
                    degree[j] -=1;
                    if (degree[j] == 1) {
                        queue.add(j);
                        result.remove(j);
                    }
                }
            }
        }
        return result;
    }


}
