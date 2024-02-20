package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P10026_적록색약 {
    static int N;
    static char[][] map1, map2;
    static boolean[][] visited;
    static int result1 = 0;
    static int result2 = 0;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map1 = new char[N][N]; //정상인
        map2 = new char[N][N]; //적록색약

        for (int r = 0; r < N; r++) {
            map1[r] = br.readLine().toCharArray();
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map1[r][c]=='R' || map1[r][c] == 'G') map2[r][c] = 'R';
                else map2[r][c] = map1[r][c];
            }
        }

        //정상인의 구역의 수
        visited = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (visited[r][c]) continue;
                result1++;
                dfs(r, c, map1);
            }
        }

        //적록색약의 구역의 수
        visited = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (visited[r][c]) continue;
                result2++;
                dfs(r, c, map2);
            }
        }

        System.out.println(result1 + " " + result2);
    }

    private static void dfs(int row, int col, char[][] map) {
        if (visited[row][col]) return;

        visited[row][col] = true;

        for (int d = 0; d < deltas.length; d++) { //상하좌우 탐색
            int nr = row + deltas[d][0];
            int nc = col + deltas[d][1];

            if (isValid(nr, nc) && !visited[nr][nc] && map[row][col] == map[nr][nc]) { //전의 탐색했던 색과 같으면
                dfs(nr, nc, map);
            }
        }

    }

    public static boolean isValid(int nr, int nc) {
        return nr >= 0 && nc >= 0 && nr < N && nc < N;
    }
}
