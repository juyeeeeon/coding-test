package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 플로이드워샬(O(N^3))
 *
 * 보통 1초를 1억번 연산으로 잡지만
 * 플로이드워샬은 단순 연산이기 때문에
 * 1초를 10억 연산으로 잡아도 충분히 통과 한다고 합니다.
 *
 * 즉 정점 1000개는 무난하게 통과
 */
public class P1238_파티_플로이드워샬 {
    static int N, M, X;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                if (i != j) {
                    dp[i][j] = Integer.MAX_VALUE / 2 - 1;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dp[s][e] = c;
        }

        for (int K = 1; K < N + 1; K++) {
            for (int S = 1; S < N + 1; S++) {
                for (int E = 1; E < N + 1; E++) {
                    dp[S][E] = Math.min(dp[S][E], dp[S][K] + dp[K][E]);
                }
            }
        }

//        printDp();

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i < N + 1; i++) {
            answer = Math.max(answer, dp[i][X] + dp[X][i]);
        }

        System.out.println(answer);

    }

    private static void printDp() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }
}
