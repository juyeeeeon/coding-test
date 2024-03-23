package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1949_등산로조성 {
    static int T, N, K, answer;
    static ArrayList<int[]> tops;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            tops = new ArrayList<>();

            int max = 0;

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] > max) max = map[r][c];
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] == max) tops.add(new int[]{r, c});
                }
            }

            answer = Integer.MIN_VALUE;
            for (int i = 0; i < tops.size(); i++) {
                visited = new boolean[N][N];
                dfs(tops.get(i)[0], tops.get(i)[1], 1, 0);
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static void dfs(int r, int c, int len, int kUsed) {
        answer = Math.max(answer, len);

        if (visited[r][c]) return;
        visited[r][c] = true;

        for (int d = 0; d < deltas.length; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];

            if (isValid(nr, nc) && !visited[nr][nc]) {
                if (map[nr][nc] < map[r][c]) {
                    dfs(nr, nc, len + 1, kUsed);
                } else if (kUsed == 0 && map[nr][nc] - K < map[r][c]) {
                    int tmp = map[nr][nc];
                    map[nr][nc] = map[r][c] - 1;
                    dfs(nr, nc, len + 1, 1);
                    map[nr][nc] = tmp;
                }
            }
        }

        visited[r][c] = false;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
