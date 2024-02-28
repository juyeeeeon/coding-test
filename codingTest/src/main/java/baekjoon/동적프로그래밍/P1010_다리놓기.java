package baekjoon.동적프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1010_다리놓기 {
    static int T, K, N;

    static int[][] comb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            comb = new int[N+1][N+1];
            for (int n = 0; n <= N; n++) {
                for (int k = 0; k <= Math.min(n, K); k++) { //n보다 K가 더 큰 경우도 있으므로
                    if (k == 0 || n == k) comb[n][k] = 1;
                    else comb[n][k] = comb[n-1][k-1] + comb[n-1][k];
                }
            }

            System.out.println(comb[N][K]);
        }
    }
}
