package org.example.탐색.dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P13023_ABCDE {
    static int N;
    static int M;
    static boolean arrive;
    static boolean[] visited;
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            arr[s].add(e);
            arr[e].add(s);
        }

        visited = new boolean[N];

        for (int i = 0; i < arr.length; i++) {
            DFS(i, 1);
            if (arrive) break;
        }

        if (arrive) {
            bw.write(Integer.toString(1));
        }else bw.write(Integer.toString(0));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void DFS(int now, int depth) {
        if (depth == 5 || arrive) {
            arrive = true;
            return;
        }

        visited[now] = true;

        for (Integer node : arr[now]) {
            if (!visited[node]) {
                DFS(node, depth+1);
            }
        }

        visited[now] = false;
    }
}
