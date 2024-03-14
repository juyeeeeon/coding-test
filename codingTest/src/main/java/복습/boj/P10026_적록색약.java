package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P10026_적록색약 {
    static int N;
    static char[][] map, map2;
    static boolean[][] visited;
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        map2 = new char[N][N];


        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'R') map2[i][j] = 'G';
                else map2[i][j] = map[i][j];
            }
        }

        int cnt = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, map, map[i][j]);
                    cnt++;
                }
            }
        }

        int cnt2 = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, map2, map2[i][j]);
                    cnt2++;
                }
            }
        }

        System.out.println(cnt + " " + cnt2);
    }

    private static void dfs(int r, int c, char[][] m, char rgb) {
        if (visited[r][c]) return;
        visited[r][c] = true;

        for (int d = 0; d < deltas.length; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];

            if (isValid(nr, nc) && m[nr][nc] == rgb) {
                dfs(nr, nc, m, rgb);
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
