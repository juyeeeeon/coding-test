package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17070_파이프옮기기1_dfs {
    static int N, result;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;
        dfs(1, 2, 0);

        System.out.println(result);

    }

    private static void dfs(int r, int c, int pipe) {
        if (!isValid(r,c)) return;
        if (map[r][c] == 1) return;
        if (pipe == 2 && (map[r-1][c] == 1 || map[r][c-1] == 1)) return;

        if (r == N && c == N) {
            result++;
            return;
        }

        if (pipe == 0) {
            dfs(r, c + 1, 0);
            dfs(r + 1, c + 1, 2);
        } else if (pipe == 1) {
            dfs(r+1, c, 1);
            dfs(r + 1, c + 1, 2);
        } else {
            dfs(r, c + 1, 0);
            dfs(r + 1, c, 1);
            dfs(r + 1, c + 1, 2);
        }
    }

    private static boolean isValid(int r, int c) {
        return r > 0 && c > 0 && r <= N && c <= N;
    }

}
