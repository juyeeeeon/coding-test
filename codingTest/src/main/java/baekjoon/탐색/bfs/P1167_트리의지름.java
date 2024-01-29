package baekjoon.탐색.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1167_트리의지름 {

    static int[] distance;
    static boolean[] visited;
    static ArrayList<Edge>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());

        distance = new int[V + 1];
        visited = new boolean[V + 1];

        arr = new ArrayList[V+1];
        for (int i = 1; i < V+1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int e = Integer.parseInt(st.nextToken());
                if (e == -1) break;
                int w = Integer.parseInt(st.nextToken());
                arr[s].add(new Edge(e, w));
            }
        }

        BFS(1);
        int max = 1;
        for (int i = 2; i < V + 1; i++) {
            if (distance[max] < distance[i]) {
                max = i;
            }
        }

        distance = new int[V + 1];
        visited = new boolean[V + 1];
        BFS(max);
        Arrays.sort(distance);
        System.out.println(distance[V]);

    }

    private static void BFS(int now) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(now);
        visited[now] = true;

        while (!queue.isEmpty()) {
            Integer v = queue.poll();
            for (Edge edge : arr[v]) {
                if (!visited[edge.vertex]) {
                    visited[edge.vertex] = true;
                    queue.add(edge.vertex);
                    distance[edge.vertex] = edge.weight + distance[v];
                }
            }
        }
    }

    static class Edge {
        int vertex;
        int weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

}
