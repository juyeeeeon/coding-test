package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1463_1로만들기 {
    static int N;
    static int[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        memo = new int[N + 1];

        memo[1] = 0;
        for (int i = 2; i <= N; i++) {
            int result1 = Integer.MAX_VALUE;
            if (i%3 == 0) result1 = memo[i/3] + 1;

            int result2 = Integer.MAX_VALUE;
            if (i%2 == 0) result2 = memo[i / 2] + 1;

            memo[i] = Math.min(result1, Math.min(result2, memo[i - 1] + 1));
        }

        System.out.println(memo[N]);

    }
}
