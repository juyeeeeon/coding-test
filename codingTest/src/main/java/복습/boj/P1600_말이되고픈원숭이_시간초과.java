package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DFS : 시간초과 -> 가중치 없는 그래프의 최단경로는 무조건 BFS
 */
public class P1600_말이되고픈원숭이_시간초과 {
    static int K, W, H;
    static int minMove = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] deltas1 = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] deltas2 = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        for (int r = 0; r < H; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < W; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[H][W][K+1];
        dfs(0, 0, 0, 0);

        if (minMove == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minMove);
    }

    private static void dfs(int r, int c, int k, int move) {
        if (r == H - 1 && c == W - 1) {
            minMove = Math.min(minMove, move);
            return;
        }

        if (k < K) {
            //원숭이처럼 이동
            for (int d = 0; d < deltas1.length; d++) {
                int nr = r + deltas1[d][0];
                int nc = c + deltas1[d][1];

                if (isValid(nr, nc) && !visited[nr][nc][k] && map[nr][nc] == 0) {
                    visited[nr][nc][k] = true;
                    dfs(nr, nc, k, move + 1);
                    visited[nr][nc][k] = false;
                }
            }

            //말처럼 이동
            for (int d = 0; d < deltas2.length; d++) {
                int nr = r + deltas2[d][0];
                int nc = c + deltas2[d][1];

                if (isValid(nr, nc) && !visited[nr][nc][k] && map[nr][nc] == 0) {
                    visited[nr][nc][k] = true;
                    dfs(nr, nc, k + 1, move + 1);
                    visited[nr][nc][k] = false;
                }
            }

        } else {
            //원숭이처럼 이동
            for (int d = 0; d < deltas1.length; d++) {
                int nr = r + deltas1[d][0];
                int nc = c + deltas1[d][1];

                if (isValid(nr, nc) && !visited[nr][nc][k] && map[nr][nc] == 0) {
                    visited[nr][nc][k] = true;
                    dfs(nr, nc, k, move + 1);
                    visited[nr][nc][k] = false;
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < H && c < W;
    }
}
