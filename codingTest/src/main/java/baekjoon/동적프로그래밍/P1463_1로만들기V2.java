package baekjoon.동적프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *  Memoization 사용하여 연산시간 줄이기
 *  상향식접근
 */
public class P1463_1로만들기V2 {
    static int N;
    static int[] memoization;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        memoization = new int[N + 1];
        Arrays.fill(memoization, Integer.MAX_VALUE);
        memoization[1] = 0;

        for (int i = 2; i <= N; i++) {
            int tmp1 = Integer.MAX_VALUE, tmp2 = Integer.MAX_VALUE, tmp3 = Integer.MAX_VALUE;
            if (i % 3 == 0) tmp1 = memoization[i / 3] + 1;
            if (i % 2 == 0) tmp2 = memoization[i / 2] + 1;
            tmp3 = memoization[i - 1] + 1;

            memoization[i] = Math.min(tmp1, Math.min(tmp2, tmp3));
        }

        System.out.println(memoization[N]);
    }
}
