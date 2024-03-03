package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1953_탈주범검거 {
    static int T, N, M, R, C, L;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            visited = new boolean[N][M];
            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs();

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j]) cnt++;
                }
            }

            System.out.println("#" + test_case + " " + cnt);
        }
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{R, C, 1});
        visited[R][C] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int time = cur[2];

            if (time >= L) continue;

            int[][] tunnel = findTunnel(map[r][c]);
            for (int d = 0; d < tunnel.length; d++) {
                int nr = r + tunnel[d][0];
                int nc = c + tunnel[d][1];

                //지금 파이프와 다음 파이프가 연결 되어 있으면
                if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] != 0 && isConnected(nr, nc, r, c)) {
                    queue.add(new int[]{nr, nc, time + 1});
                    visited[nr][nc] = true;
                }
            }
        }

    }

    private static boolean isConnected(int nr, int nc, int r, int c) {
        boolean isConnected = false;

        int[][] nextTunnel = findTunnel(map[nr][nc]);
        for (int dir = 0; dir < nextTunnel.length; dir++) {
            if ((nr + nextTunnel[dir][0] == r) && (nc + nextTunnel[dir][1] == c)) {
                isConnected = true;
            }
        }

        return isConnected;
    }

    private static int[][] findTunnel(int idx) {
        if (idx == 1) return new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상하좌우
        if (idx == 2) return new int[][] {{-1, 0}, {1, 0}}; //상하
        if (idx == 3) return new int[][] {{0, -1}, {0, 1}}; //좌우
        if (idx == 4) return new int[][] {{-1, 0}, {0, 1}}; //상우
        if (idx == 5) return new int[][] {{1, 0}, {0, 1}}; //하우
        if (idx == 6) return new int[][] {{1, 0}, {0, -1}}; //하좌
        if (idx == 7) return new int[][] {{-1, 0}, {0, -1}}; //상좌
        return null;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
