package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2573_빙산 {
    static int N, M, time;
    static ArrayList<int[]> icebergs;
    static boolean[][] visited;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

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

        time = 0;
        while (true) {
            setIcebergs();

            /*
            System.out.println(time);
            printmap();
            */

            if (isDivided()) {
                System.out.println(time);
                return;
            }

            if (icebergs.isEmpty()) {
                System.out.println(0);
                return;
            }

            melting();


            time++;
        }
    }

    private static void printmap() {
        System.out.println("=================");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=================");
    }

    private static void melting() {
        int[] count = new int[icebergs.size()];

        for (int i = 0; i < icebergs.size(); i++) {
            int r = icebergs.get(i)[0];
            int c = icebergs.get(i)[1];

            for (int d = 0; d < deltas.length; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];

                if (isValid(nr, nc) && map[nr][nc] == 0) {
                    count[i]++;
                }
            }
        }

        for (int i = 0; i < icebergs.size(); i++) {
            int r = icebergs.get(i)[0];
            int c = icebergs.get(i)[1];

            map[r][c] -= count[i];
            if (map[r][c] < 0) map[r][c] = 0;
        }

    }

    private static void setIcebergs() {
        icebergs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) icebergs.add(new int[]{i, j});
            }
        }
    }

    private static boolean isDivided() {
        int cnt = 0;
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    if (cnt > 0) return true;
                    cnt++;
                    bfs(i, j);
                }
            }
        }

        return false;
    }

    private static void bfs(int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int d = 0; d < deltas.length; d++) {
                int nr = cur[0] + deltas[d][0];
                int nc = cur[1] + deltas[d][1];

                if (isValid(nr, nc) && map[nr][nc] > 0 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
