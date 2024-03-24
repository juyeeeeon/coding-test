package 복습.programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class 게임맵최단거리 {
    static int N, M, answer;
    static boolean[][] visited;
    static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int solution(int[][] maps) {
        answer = Integer.MAX_VALUE;

        N = maps.length;
        M = maps[0].length;
        visited = new boolean[N][M];
        bfs(0, 0, maps);

        if (answer == Integer.MAX_VALUE) return -1;
        return answer;
    }

    private void bfs(int r, int c, int[][] maps) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c, 1});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == N - 1 && cur[1] == M - 1) {
                answer = cur[2];
                return;
            }

            for (int d = 0; d < deltas.length; d++) {
                int nr = cur[0] + deltas[d][0];
                int nc = cur[1] + deltas[d][1];

                if (isValid(nr, nc) && !visited[nr][nc] && maps[nr][nc] == 1) {
                    queue.add(new int[]{nr, nc, cur[2] + 1});
                    visited[nr][nc] = true;
                }
            }
        }
    }

    private boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
