package org.example.탐색.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1260_DFS와BFS {
    static boolean[] visited;
    static ArrayList<Integer>[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            arr[s].add(e);
            arr[e].add(s);
        }

        for (int i = 1; i < N + 1; i++) {
            Collections.sort(arr[i]);
        }

        visited = new boolean[N + 1];
        DFS(V);
        System.out.println();

        visited = new boolean[N + 1];
        BFS(V);
        System.out.println();

    }

    private static void BFS(int v) {
        if (visited[v]) return;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        System.out.print(v+" ");

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            for (Integer vertex : arr[node]) {
                if (visited[vertex]) continue;
                visited[vertex] = true;
                queue.add(vertex);
                System.out.print(vertex+" ");
            }
        }
    }

    private static void DFS(int v) {
        if (visited[v]) {
            return;
        }

        visited[v] = true;
        System.out.print(v+" ");
        for (int node : arr[v]) {
            if (!visited[node]) {
                DFS(node);
            }
        }
    }
}
