package baekjoon.동적프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 피보나치 수열은 1,500,000 마다 주기를 가짐(=피사노 주기)
 */
public class P2749_피보나치수3 {
    static long N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(reader.readLine());
        long index = N % 1500000;

        long[] fb = new long[(int)index + 1];

        fb[0] = 0;
        fb[1] = 1;

        for(int i = 2; i <= index; i++) {
            fb[i] = (fb[i - 1] + fb[i - 2]) % 1000000;
        }

        System.out.println(fb[(int)index]);
    }
}
