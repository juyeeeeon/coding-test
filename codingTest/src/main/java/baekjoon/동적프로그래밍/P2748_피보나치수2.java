package baekjoon.동적프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2748_피보나치수2 {
    static int N;
    static long[] F;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        F = new long[91];

        F[0] = 0;
        F[1] = 1;

        for (int i = 2; i <= N; i++) {
            F[i] = F[i - 1] + F[i - 2];
        }

        System.out.println(F[N]);
    }
}
