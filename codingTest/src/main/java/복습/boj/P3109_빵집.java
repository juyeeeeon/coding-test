package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P3109_빵집 {
    static int R, C, result;
    static boolean finished;
    static char[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 1}, {0, 1}, {1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }

        result = 0;
        visited = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            finished = false;
            dfs(r, 0);
        }

        System.out.println(result);

    }

    private static void dfs(int r, int c) {
        if (c == C - 1) {
            result++;
            finished = true;
            return;
        }

        for (int d = 0; d < deltas.length; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];

            if (isValid(nr, nc) && map[nr][nc] == '.' && !visited[nr][nc]) {
                visited[nr][nc] = true;
                dfs(nr, nc);
                if (finished) return; //!!!!!!!!!
            }
        }


    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
