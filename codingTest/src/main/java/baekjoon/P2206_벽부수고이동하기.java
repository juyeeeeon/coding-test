package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2206_벽부수고이동하기 {
    static int N, M, answer;
    static int[][] map;
    static boolean[][][] visited;
    static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        for (int i = 1; i < N + 1; i++) {
            char[] charArr = br.readLine().toCharArray();
            for (int j = 1; j < M + 1; j++) {
                map[i][j] = charArr[j - 1] - '0';
            }
        }

//        printMap();

        visited = new boolean[N + 1][M + 1][2];
        answer = Integer.MAX_VALUE;

        bfs();
        if (answer == Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{1, 1, 1, 0});
        visited[1][1][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == N && cur[1] == M) {
                answer = cur[2];
                return;
            }

            for (int d = 0; d < deltas.length; d++) {
                int nr = cur[0] + deltas[d][0];
                int nc = cur[1] + deltas[d][1];

                if (isValid(nr, nc)) {
                    if (map[nr][nc] == 0 && !visited[nr][nc][cur[3]]) {
                        visited[nr][nc][cur[3]] = true;
                        queue.add(new int[]{nr, nc, cur[2] + 1, cur[3]});
                    } else if (map[nr][nc] == 1 && cur[3] == 0 && !visited[nr][nc][1]) {
                        visited[nr][nc][1] = true;
                        queue.add(new int[]{nr, nc, cur[2] + 1, 1});
                    }
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 1 && c >= 1 && r <= N && c <= M;
    }

    private static void printMap() {
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < M + 1; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

}
