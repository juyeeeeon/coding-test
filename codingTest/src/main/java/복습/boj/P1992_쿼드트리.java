package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1992_쿼드트리 {
    static int N;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        dfs(0, 0, N);
    }

    private static void dfs(int r, int c, int n) {
        if (n == 1) {
            System.out.print(map[r][c]);
            return;
        }

        int sum = getSum(r, c, n);
        if (sum == 0) {
            System.out.print(0);
            return;
        }
        if (sum == n * n) {
            System.out.print(1);
            return;
        }

        System.out.print("(");
        dfs(r, c, n / 2);
        dfs(r, c + n / 2, n / 2);
        dfs(r + n / 2, c, n / 2);
        dfs(r + n / 2, c + n / 2, n / 2);
        System.out.print(")");



    }

    private static int getSum(int r, int c, int n) {
        int sum = 0;
        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                sum += map[i][j] - '0';
            }
        }
        return sum;
    }

}
