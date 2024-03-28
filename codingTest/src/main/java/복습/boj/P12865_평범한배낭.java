package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Knapsack 문제
 */
public class P12865_평범한배낭 {
    static int N, K;
    static int[][] dp;
    static int[][] items;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        items = new int[N+1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            items[i][0] = Integer.parseInt(st.nextToken()); //무게
            items[i][1] = Integer.parseInt(st.nextToken()); //가치
        }

        //dp : i번째 아이템을 포함하여 남은 무게가 k일 때 최대 가치
        dp = new int[N+1][K+1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i][j] = dp[i - 1][j]; //i번째 아이템을 포함하지 않았을 때 최대 가치
                if (items[i][0] <= j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - items[i][0]] + items[i][1]); //( , i번째 아이템을 포함하였을 때 최대 가치)
                }
            }
        }


        System.out.println(dp[N][K]);

    }
}
