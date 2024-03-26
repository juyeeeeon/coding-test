package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P7579_앱 {
    static int N, M, totalCost;
    static int[] m, c;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        totalCost = 0;

        m = new int[N+1];
        c = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            totalCost += c[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1][totalCost + 1]; // i번째 앱까지 탐색하여 j비용을 소모해서 얻은 메모리 최대값
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= totalCost; j++) {
                if (j >= c[i]) {
                    dp[i][j] = dp[i - 1][j - c[i]] + m[i];
                }
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }

        for(int i =0 ;i<=totalCost ; i++) {
            if (dp[N][i] >= M) { //전체를 탐색하고 얻은 메모리가 M이상일 경우의 cost return

                System.out.println(i);
                return;
            }
        }
    }
}
