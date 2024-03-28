package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS
 */
public class P1600_말이되고픈원숭이 {
    static int K, W, H;
    static int minMove = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] horse = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
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
        bfs();

        if (minMove == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minMove);
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 0, 0}); //행, 열, 말처럼 이동한 횟수, 움직인 횟수

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int k = cur[2];
            int move = cur[3];

            if (r == H - 1 && c == W - 1) {
                minMove = move;
                return;
            }

            if (k < K) {
                //말처럼 이동
                for (int d = 0; d < horse.length; d++) {
                    int nr = r + horse[d][0];
                    int nc = c + horse[d][1];

                    if (isValid(nr, nc) && !visited[nr][nc][k + 1] && map[nr][nc] == 0) {
                        queue.add(new int[]{nr, nc, k + 1, move + 1});
                        visited[nr][nc][k + 1] = true;
                    }
                }

            }

            //원숭이처럼 이동
            for (int d = 0; d < deltas.length; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];

                if (isValid(nr, nc) && !visited[nr][nc][k] && map[nr][nc] == 0) {
                    queue.add(new int[]{nr, nc, k, move + 1});
                    visited[nr][nc][k] = true;
                }
            }
        }


    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < H && c < W;
    }
}
