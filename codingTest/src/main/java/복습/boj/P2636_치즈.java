package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2636_치즈 {
    static int N, M, result, time;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (countCheese() > 0) {
            result = countCheese();
            time++;
            visited = new boolean[N][M]; //!!!!!!!!!!!!!!
            bfs();
        }

        System.out.println(time);
        System.out.println(result);
    }

    private static int countCheese() {
        int cnt = 0;
        for (int i = 1; i < N-1; i++) {
            for (int j = 1; j < M-1; j++) {
                if (map[i][j] == 1) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];

            if (map[r][c] == 1) {
                map[r][c] = 0;
                visited[r][c] = true;

                continue; //!!!!!!!!!!!!!!!
            }

            for (int d = 0; d < deltas.length; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];

                if (valid(nr, nc) && !visited[nr][nc]) {
                    queue.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }

        }
    }

    private static boolean valid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

}
