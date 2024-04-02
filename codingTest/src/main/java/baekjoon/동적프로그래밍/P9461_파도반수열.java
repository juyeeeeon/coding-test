package baekjoon.동적프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9461_파도반수열 {
    static int T, N;
    static long[] padoban;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        padoban = new long[101];
        padoban[1] = 1;
        padoban[2] = 1;

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());

            for (int j = 3; j <= N; j++) {
                padoban[j] = padoban[j - 2] + padoban[j - 3];
            }

            System.out.println(padoban[N]);
        }
    }
}
