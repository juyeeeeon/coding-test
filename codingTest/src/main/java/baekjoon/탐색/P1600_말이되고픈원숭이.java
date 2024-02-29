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

        // 같은 위치에 도달했다고 해도
        // 말처럼 움직인 횟수가 다르다면 그 상태는 서로 다른 상태로 간주
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

        queue.add(new Node(0, 0, 0, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int r = cur.r;
            int c = cur.c;
            int k = cur.k;
            int move = cur.move;

            if (r == H - 1 && c == W - 1) {
                result = Math.min(result, move);
                return;
            }

            if (visited[r][c][k]) continue;
            visited[r][c][k] = true;


            if (k < K) { //말처럼 움직일 수 있으면
                for (int d = 0; d < deltas1.length; d++) { //1씩 움직이는 경우
                    int nr = r + deltas1[d][0];
                    int nc = c + deltas1[d][1];

                    if (isValid(nr, nc) && !visited[nr][nc][k] && map[nr][nc] == 0){
                        queue.add(new Node(nr, nc, k, move + 1));
                    }
                }

                for (int d = 0; d < deltas2.length; d++) { //말처럼 움직이는 경우
                    int nr = r + deltas2[d][0];
                    int nc = c + deltas2[d][1];

                    if (isValid(nr, nc) && !visited[nr][nc][k] && map[nr][nc] == 0) {
                        queue.add(new Node(nr, nc, k + 1, move + 1));
                    }
                }
            } else { //말처럼 움직일 수 없으면
                for (int d = 0; d < deltas1.length; d++) { //1씩 움직이는 경우
                    int nr = r + deltas1[d][0];
                    int nc = c + deltas1[d][1];

                    if (isValid(nr, nc) && !visited[nr][nc][k] && map[nr][nc] == 0) {
                        queue.add(new Node(nr, nc, k, move + 1));
                    }
                }
            }
        }
    }


    private static class Node{
        int r;
        int c;
        int k;
        int move;

        public Node(int r, int c, int k, int move) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.move = move;
        }
    }
    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < H && c < W;
    }
}
