package baekjoon.동적프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *  Memoization 사용하여 연산시간 줄이기
 *  상향식접근
 */
public class P1463_1로만들기 {
    static int N;
    static int[] memoization;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        memoization = new int[N + 1];
        Arrays.fill(memoization, Integer.MAX_VALUE);
        memoization[1] = 0;

        System.out.println(dp(N));
    }

    private static int dp(int num) {
        if (num > 1 && memoization[num] == Integer.MAX_VALUE) {
            int tmp1 = Integer.MAX_VALUE;
            if (num % 3 == 0) {
              tmp1 = dp(num/3) + 1;
            }

            int tmp2 = Integer.MAX_VALUE;
            if (num % 2 == 0) {
              tmp2 = dp(num/2) + 1;
            }

            int tmp3 = dp(num-1) + 1;

            memoization[num] = Math.min(Math.min(tmp1, tmp2), tmp3);
        }

        return memoization[num];
    }
}
