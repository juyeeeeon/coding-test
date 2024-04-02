package baekjoon.동적프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P10870_피보나치수5 {
    static int N;
    static int[] fb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        fb = new int[N + 2];

        fb[0] = 0;
        fb[1] = 1;

        for (int i = 2; i <= N; i++) {
            fb[i] = fb[i - 1] + fb[i - 2];
        }

        System.out.println(fb[N]);
    }
}
