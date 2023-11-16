package org.example.깊이너비우선탐색;

import java.util.ArrayList;

public class 네트워크 {
    static int cnt;
    static boolean[] visited;
    static ArrayList<Integer>[] arr;
    public int solution(int n, int[][] computers) {
        int answer = 0;

        visited = new boolean[n];
        arr = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1) {
                    arr[i].add(j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cnt++;
                DFS(i);
            }
        }

        answer = cnt;

        return answer;
    }

    private void DFS(int now) {
        if (visited[now]) return;
        visited[now] = true;

        for (Integer node : arr[now]) {
            if (!visited[node]) {
                DFS(node);
            }
        }
    }
}
