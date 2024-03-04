package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1010_다리놓기 {
    static int T, R, N;
    static int[][] comb;
    
    //nCr = n-1Cr-1 + n-1Cr
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            /**
             * nCr 구하기
             */
            comb = new int[N + 1][N + 1];
            for (int n = 0; n <= N; n++) {
                for (int r = 0; r <= Math.min(n, R); r++) {
                    if (r==0 || n==r) comb[n][r] = 1;
                    else comb[n][r] = comb[n-1][r-1] + comb[n-1][r];
                }
            }

            System.out.println(comb[N][R]);
        }
    }
}
