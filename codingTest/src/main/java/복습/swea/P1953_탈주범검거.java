package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1953_탈주범검거 {
    static int T, N, M, R, C, L, answer;
    static int[][] map;
    static boolean[][] visited;
    static int[][] pipe = {{}, {1, 2, 3, 4}, {1, 2}, {3, 4}, {1, 4}, {2, 4}, {2, 3}, {1, 3}}; //1:상 2:하 3:좌 4:우
    static int[][] deltas = {{}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상, 하, 좌, 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[N][M];

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            answer = 0;
            visited = new boolean[N][M];

            bfs();

            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{R, C, 1});
        visited[R][C] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[2] > L) continue;
            answer++;

            int curPipe = map[cur[0]][cur[1]];
            for (int d : pipe[curPipe]) {
                int nr = cur[0] + deltas[d][0];
                int nc = cur[1] + deltas[d][1];

                if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] != 0 && isConnected(cur[0], cur[1], nr, nc)) {
                    queue.add(new int[]{nr, nc, cur[2] + 1});
                    visited[nr][nc] = true;
                }
            }
        }
    }

    private static boolean isConnected(int r, int c, int nr, int nc) {
        boolean isConnected = false;
        int nextPipe = map[nr][nc];
        for (int d : pipe[nextPipe]) {
            if ((nr + deltas[d][0] == r) && (nc + deltas[d][1] == c)) {
                isConnected = true;
            }
        }

        if (isConnected) return true;
        return false;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
