package swea.D6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 플로이드 워샬(O(N^3))
 */
public class P1263_사람네트워크2 {
    static int T, N;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            dp = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dp[i][j] = Integer.parseInt(st.nextToken());
                    if (i != j && dp[i][j] == 0) dp[i][j] = Integer.MAX_VALUE / 2 - 1; //이어지지 않은 간선은 큰 값을 초기화
                }
            }

            //플로이드 워샬
            for (int k = 0; k < N; k++) {
                for (int s = 0; s < N; s++) {
                    for (int e = 0; e < N; e++) {
                        dp[s][e] = Math.min(dp[s][e], dp[s][k] + dp[k][e]);
                    }
                }
            }

            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                int sum = 0;
                for (int j = 0; j < N; j++) {
                    sum += dp[i][j];
                }
                min = Math.min(min, sum);
            }

            System.out.println("#" + test_case + " " + min);

        }
    }
}
