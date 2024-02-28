package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1600_말이되고픈원숭이 {
    static int K, W, H;
    static int result = Integer.MAX_VALUE;
    static boolean[][][] visited;
    static int[][] deltas1 = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; //아래, 위, 오른쪽, 왼쪽
    static int[][] deltas2 = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visited = new boolean[H][W][K+1];

        for (int r = 0; r < H; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < W; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        if (result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
    }

    private static void bfs() {
        Queue<Node> queue = new ArrayDeque<>();

        queue.add(new Node(0, 0, K, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int r = cur.r;
            int c = cur.c;
            int k = cur.k;
            int cnt = cur.cnt;

            if (r == H - 1 && c == W - 1) {
                result = Math.min(result, cnt);
                return;
            }

            if (visited[r][c][k]) continue;
            visited[r][c][k] = true;


            if (k > 0) {
                for (int d = 0; d < deltas1.length; d++) {
                    int nr = r + deltas1[d][0];
                    int nc = c + deltas1[d][1];

                    if (isValid(nr, nc) && !visited[nr][nc][k] && map[nr][nc] == 0){
                        queue.add(new Node(nr, nc, k, cnt + 1));
                    }
                }

                for (int d = 0; d < deltas2.length; d++) {
                    int nr = r + deltas2[d][0];
                    int nc = c + deltas2[d][1];

                    if (isValid(nr, nc) && !visited[nr][nc][k] && map[nr][nc] == 0) {
                        queue.add(new Node(nr, nc, k - 1, cnt + 1));
                    }
                }
            } else {
                for (int d = 0; d < deltas1.length; d++) {
                    int nr = r + deltas1[d][0];
                    int nc = c + deltas1[d][1];

                    if (isValid(nr, nc) && !visited[nr][nc][k] && map[nr][nc] == 0) {
                        queue.add(new Node(nr, nc, k, cnt + 1));
                    }
                }
            }
        }
    }


    private static class Node{
        int r;
        int c;
        int k;
        int cnt;

        public Node(int r, int c, int k, int cnt) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.cnt = cnt;
        }
    }
    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < H && c < W;
    }
}
