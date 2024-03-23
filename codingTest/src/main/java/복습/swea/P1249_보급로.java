package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P1249_보급로 {
    static int T, N, answer;
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][] visited;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                char[] charArray = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    map[i][j] = charArray[j] - '0';
                }
            }

            answer = Integer.MAX_VALUE;
            visited = new boolean[N][N];
            bfs();

            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static void bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        pq.add(new int[]{0, 0, 0});
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[0] == N - 1 && cur[1] == N - 1) {
                answer = cur[2];
                return;
            }

            for (int d = 0; d < deltas.length; d++) {
                int nr = cur[0] + deltas[d][0];
                int nc = cur[1] + deltas[d][1];

                if (isValid(nr, nc) && !visited[nr][nc]) {
                    pq.add(new int[]{nr, nc, cur[2] + map[nr][nc]});
                    visited[nr][nc] = true;
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
