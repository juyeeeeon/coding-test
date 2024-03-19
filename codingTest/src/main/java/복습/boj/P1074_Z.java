package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1074_Z {
    static int N, r, c, num = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        dfs(0, 0, (int) Math.pow(2, N));

    }

    private static void dfs(int row, int col, int n) {
        if (row == r && col == c) {
            System.out.println(num);
            return;
        }

        if (!(r >= row && r < row + n && c >= col && c < col + n)) {
            num += n * n;
            return;
        }

        dfs(row, col, n / 2);
        dfs(row, col + n / 2, n / 2);
        dfs(row + n / 2, col, n / 2);
        dfs(row + n / 2, col + n / 2, n / 2);
    }
}
