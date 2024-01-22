package baekjoon.탐색.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1167_트리의지름 {
    static int V, maxNode;
    static int maxDistance;
    static boolean[] visited;
    static ArrayList<Node>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        arr = new ArrayList[V + 1];
        for (int i = 1; i < V + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()){
                int e = Integer.parseInt(st.nextToken());
                if (e == -1) break;
                int w = Integer.parseInt(st.nextToken());
                arr[s].add(new Node(e, w));
            }
        }

        visited = new boolean[V + 1];
        DFS(1, 0);



        visited = new boolean[V + 1];
        DFS(maxNode, 0);

        System.out.println(maxDistance);


    }

    private static void DFS(int i, int len) {
        if (maxDistance < len) {
            maxDistance = len;
            maxNode = i;
        }
        visited[i] = true;

        for (Node node : arr[i]) {
            if (!visited[node.vertex]) {
                visited[node.vertex] = true;
                DFS(node.vertex, len + node.weight);
            }
        }
    }

    private static class Node {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}