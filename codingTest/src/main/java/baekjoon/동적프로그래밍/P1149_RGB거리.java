package baekjoon.동적프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1149_RGB거리 {
    static int N;
    static int[][] memoization;
    static int[][] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        memoization = new int[N+1][3];
        costs = new int[N+1][3]; //[][0]:R, [][1]:G, [][2]:B


        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        memoization[1][0] = costs[1][0];
        memoization[1][1] = costs[1][1];
        memoization[1][2] = costs[1][2];

        System.out.println(dp(N));
    }

    private static int dp(int n) {
        for (int i = 1; i <= n; i++) {
            memoization[i][0] = Math.min(memoization[i - 1][1], memoization[i - 1][2]) + costs[i][0];
            memoization[i][1] = Math.min(memoization[i - 1][0], memoization[i - 1][2]) + costs[i][1];
            memoization[i][2] = Math.min(memoization[i - 1][0], memoization[i - 1][1]) + costs[i][2];
        }


        return Math.min(memoization[n][0], Math.min(memoization[n][1], memoization[n][2]));
    }
}
