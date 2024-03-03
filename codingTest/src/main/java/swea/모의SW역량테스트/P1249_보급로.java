package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P1249_보급로 {
    static int T, N;
    static int minTime;
    static boolean[][] visited;
    static int[][] map;
    static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int r = 0; r < N; r++) {
                char[] charArray = br.readLine().toCharArray();
                for (int c = 0; c < N; c++) {
                    map[r][c] = charArray[c] - '0';
                }
            }

            minTime = Integer.MAX_VALUE;
            visited = new boolean[N][N];
            bfs();

            System.out.println("#" + test_case + " " + minTime);
        }
    }

    private static void bfs() {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        queue.add(new int[]{0, 0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            if (cur[0] == N - 1 && cur[1] == N - 1) {
                //우선순위큐를 사용했기 때문에 최초로 도착한 길이 최단임을 보장
                minTime = cur[2];
                return;

                /*minTime = Math.min(minTime, cur[2]);
                continue;*/
            }

            for (int d = 0; d < deltas.length; d++) {
                int nr = cur[0] + deltas[d][0];
                int nc = cur[1] + deltas[d][1];

                if (isValid(nr, nc) && !visited[nr][nc]) {
                    queue.add(new int[]{nr, nc, cur[2] + map[nr][nc]});
                    visited[nr][nc] = true;
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
