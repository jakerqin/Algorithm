package test;

import java.util.*;

/**
 * 求是否有环
 */
public class graph {

    public static int[] canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[0];
        }
        int[] inDegree = new int[numCourses];
        for (int[] p : prerequisites) {
            inDegree[p[0]]++;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        // 首先加入入度为 0 的结点
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.addLast(i);
            }
        }
        // 拓扑排序的结果
        List<Integer> res = new ArrayList<>();
        int[] arr = new int[numCourses];
        int j = 0;
        while (!queue.isEmpty()) {
            Integer num = queue.removeFirst();
            res.add(num);
            arr[j++] = num;
            // 把邻边全部遍历一下
            for (int[] p : prerequisites) {
                if (p[1] == num) {
                    inDegree[p[0]]--;
                    if (inDegree[p[0]] == 0) {
                        queue.addLast(p[0]);
                    }
                }
            }
        }
        if(res.size() != numCourses) {
            return new int[0];
        }else{
            return arr;
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {0,1,2},{1,2,3}
        };
    }











        public int[] findOrder(int numCourses, int[][] prerequisites) {

            boolean isPossible = true;
            Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
            int[] indegree = new int[numCourses];
            int[] topologicalOrder = new int[numCourses];

            // Create the adjacency list representation of the graph
            for (int i = 0; i < prerequisites.length; i++) {
                int dest = prerequisites[i][0];
                int src = prerequisites[i][1];
                List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
                lst.add(dest);
                adjList.put(src, lst);

                // Record in-degree of each vertex
                indegree[dest] += 1;
            }

            // Add all vertices with 0 in-degree to the queue
            Queue<Integer> q = new LinkedList<Integer>();
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }

            int i = 0;
            // Process until the Q becomes empty
            while (!q.isEmpty()) {
                int node = q.remove();
                topologicalOrder[i++] = node;

                // Reduce the in-degree of each neighbor by 1
                if (adjList.containsKey(node)) {
                    for (Integer neighbor : adjList.get(node)) {
                        indegree[neighbor]--;

                        // If in-degree of a neighbor becomes 0, add it to the Q
                        if (indegree[neighbor] == 0) {
                            q.add(neighbor);
                        }
                    }
                }
            }

            // Check to see if topological sort is possible or not.
            if (i == numCourses) {
                return topologicalOrder;
            }

            return new int[0];
        }

}
