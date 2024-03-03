package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2117_홈방범서비스 {
    static int T, N, M, K, home, maxHome;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            maxHome = Integer.MIN_VALUE; //서비스를 제공받는 집들의 수의 최댓값
            K = 1; //서비스 영역
            while (true) {
                if (K > N + 1) break; //도시를 벗어난 영역에 서비스를 제공해도 운영 비용은 변경되지 않는다.

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        home = 0;
                        bfs(i, j);

                        //손해를 보지 않으면서 홈방범 서비스를 가장 많은 집들에 제공하는 서비스 영역을 찾았을 때,
                        //그 때의 서비스를 제공 받는 집들의 수
                        if (M * home - getOperationFee(K) >= 0) {
                            maxHome = Math.max(maxHome, home);
                        }
                    }
                }

                K++;
            }

            System.out.println("#" + test_case + " " + maxHome);
        }
    }

    private static void bfs(int row, int col) {
        boolean[][] visited = new boolean[N][N];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{row, col, 1}); //행,열,움직인횟수(=k)
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int k = cur[2];

            if (map[r][c] == 1) {
                home++;
            }

            if (k >= K) continue;

            for (int d = 0; d < deltas.length; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];

                if (isValid(nr, nc) && !visited[nr][nc]) {
                    queue.add(new int[]{nr, nc, k+1});
                    visited[nr][nc] = true;
                }
            }
        }

    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

    private static int getOperationFee(int k) {
        return k * k + (k - 1) * (k - 1);
    }
}
