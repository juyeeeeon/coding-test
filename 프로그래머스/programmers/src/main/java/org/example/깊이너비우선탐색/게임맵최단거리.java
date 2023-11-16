package org.example.깊이너비우선탐색;


import java.util.LinkedList;
import java.util.Queue;

public class 게임맵최단거리 {

    static int N, M;
    static boolean[][] visited;
    int[] r = new int[]{0, -1, 1, 0};
    int[] c = new int[]{1, 0, 0, -1};

    public int solution(int[][] maps) {
        int answer = 0;

        N = maps.length;
        M = maps[0].length;

        visited = new boolean[N][M];

        BFS(maps, 0, 0);

        if (maps[N - 1][M - 1] == 1) answer = -1;
        else answer = maps[N - 1][M - 1];


        return answer;
    }

    private void BFS(int[][] maps, int row, int col) {
        if (visited[row][col]) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(row, col));
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nr = now.row + r[k];
                int nc = now.col + c[k];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M && maps[nr][nc] != 0 && !visited[nr][nc]) {
                    maps[nr][nc] = maps[now.row][now.col]+1;
                    visited[nr][nc] = true;
                    queue.add(new Node(nr, nc));
                }
            }
        }

        
    }

    static class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
}
