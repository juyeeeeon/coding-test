package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1149_RGB거리 {
    static int N;
    static int[][] houses, memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        houses = new int[N][3];
        memo = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                houses[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        memo[0][0] = houses[0][0];
        memo[0][1] = houses[0][1];
        memo[0][2] = houses[0][2];

        for (int i = 1; i < N; i++) {
            memo[i][0] = Math.min(memo[i-1][1], memo[i-1][2]) + houses[i][0];
            memo[i][1] = Math.min(memo[i-1][0], memo[i-1][2]) + houses[i][1];
            memo[i][2] = Math.min(memo[i-1][0], memo[i-1][1]) + houses[i][2];
        }

        System.out.println(Math.min(memo[N - 1][0], Math.min(memo[N - 1][1], memo[N - 1][2])));
    }
}
