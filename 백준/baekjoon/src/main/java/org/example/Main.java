package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] row = new int[]{1, 0, -1, 0};
    static int[] col = new int[]{0, 1, 0, -1};

    static int N, M;
    static boolean[][] visited;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];

        for (int i = 0; i < N; i++) {
            String readLine = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i+1][j+1] = Integer.parseInt(readLine.substring(j, j+1));
            }
        }


        visited = new boolean[N + 1][M + 1];

        BFS(1, 1);

        System.out.println(arr[N][M]);

    }

    private static void BFS(int i, int j) {
        if (visited[i][j]) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(i, j));
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nr = row[k] + now.i;
                int nc = col[k] + now.j;

                if (nr >= 1 && nr <= N && nc >= 1 && nc <= M && arr[nr][nc] != 0 && !visited[nr][nc]) {
                    queue.add(new Node(nr, nc));
                    visited[nr][nc] = true;
                    arr[nr][nc] = arr[now.i][now.j] + 1;
                }
            }
        }
    }

    private static class Node {
        int i;
        int j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}