package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2117_홈방범서비스 {
    static int T, N, M, maxHome, cnt;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][N];

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            maxHome = Integer.MIN_VALUE;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    for (int k = 1; k <= N + 1; k++) {
                        visited = new boolean[N][N];
                        cnt = 0;
                        BFS(r, c, k);

                        if (M * cnt - (k * k + (k - 1) * (k - 1)) >= 0) {
                            maxHome = Math.max(maxHome, cnt);
                        }
                    }
                }
            }

            System.out.println("#" + test_case + " " + maxHome);

        }
    }

    private static void BFS(int startR, int startC, int k) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startR, startC, 1});
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (map[cur[0]][cur[1]] == 1) cnt++;

            if (cur[2] == k) continue;

            for (int d = 0; d < deltas.length; d++) {
                int nr = cur[0] + deltas[d][0];
                int nc = cur[1] + deltas[d][1];

                if (isValid(nr, nc) && !visited[nr][nc]) {
                    queue.add(new int[]{nr, nc, cur[2] + 1});
                    visited[nr][nc] = true;
                }
            }
        }
    }

    private static boolean isValid(int nr, int nc) {
        return nr >= 0 && nc >= 0 && nr < N && nc < N;
    }
}
