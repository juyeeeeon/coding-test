package baekjoon.동적프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class P10826_피보나치수4 {
    static int N;
    static BigInteger[] fb;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());

        fb = new BigInteger[N + 2]; //N이 0일 때 fb[0], fb[1] 모두 담아놔야 하므로 N+2

        fb[0] = BigInteger.valueOf(0);
        fb[1] = BigInteger.valueOf(1);

        for(int i = 2; i <= N; i++) {
            fb[i] = fb[i - 1].add(fb[i - 2]);
        }

        System.out.println(fb[N]);
    }
}
