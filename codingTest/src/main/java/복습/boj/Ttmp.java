package 복습.boj;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Ttmp {
    static int K, W, H, minResult;
    static int[][] map;
    static boolean[][][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int[][] horse = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        minResult = Integer.MAX_VALUE;
        visited = new boolean[H][W][K + 1];
        bfs();

        if (minResult == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minResult);
    }

    private static void bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        pq.add(new int[]{0, 0, 0, 0}); //[r][c][몇번움직였는지][말처럼움직인횟수]
        visited[0][0][0] = true;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[0] == H - 1 && cur[1] == W - 1) {
                minResult = cur[2];
                return;
            }

            //말처럼 이동할 때
            if (cur[3] < K) {
                for (int d = 0; d < horse.length; d++) {
                    int nr = cur[0] + horse[d][0];
                    int nc = cur[1] + horse[d][1];

                    if (isValid(nr, nc) && !visited[nr][nc][cur[3] + 1] && map[nr][nc] != 1) {
                        pq.add(new int[]{nr, nc, cur[2] + 1, cur[3] + 1});
                        visited[nr][nc][cur[3] + 1] = true;
                    }
                }
            }

            //원숭이처럼 이동할 때
            for (int d = 0; d < deltas.length; d++) {
                int nr = cur[0] + deltas[d][0];
                int nc = cur[1] + deltas[d][1];

                if (isValid(nr, nc) && !visited[nr][nc][cur[3]] && map[nr][nc] != 1) {
                    pq.add(new int[]{nr, nc, cur[2] + 1, cur[3]});
                    visited[nr][nc][cur[3]] = true;
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < H && c < W;
    }
}
