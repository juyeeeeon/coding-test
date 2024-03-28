package baekjoon.동적프로그래밍;

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

        dp = new int[N + 1][totalCost + 1]; // 확보할 수 있는 메모리 최대값 = [i번째 앱까지를 고려][소모 가능한 비용]
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= totalCost; j++) {
                dp[i][j] = dp[i-1][j]; //dp[i-1][j] : i번째 앱을 비활성화 하지 않았을 때 메모리 최대값
                if (j>=c[i]) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - c[i]] + m[i]); // 소모 가능한 비용(j)이 i번째 앱의 비용보다 클 때 => i번째 앱을 비활성화 할 수 있을 때
                                                            //확보할 수 있는 메모리 최대값 = i-1번째 앱까지 고려하여 j-c[i]비용을 소모했을 때 메모리 최대값 + i번째 앱의 메모리 크기
            }
        }

        //적은 비용부터 탐색하여 확보할 수 있는 메모리 최대값이 M이상이면 리턴
        for (int i = 0; i <= totalCost; i++) {
            if (dp[N][i] >= M) {
                System.out.println(i);
                return;
            }
        }
    }
}
