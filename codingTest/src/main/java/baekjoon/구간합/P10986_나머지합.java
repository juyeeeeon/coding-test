package baekjoon.구간합;

import java.io.*;
import java.util.StringTokenizer;

/**
 * int 사용하면 ArrayIndexOutOfBounds 런타임 에러 발생
 * int 대신 long 쓰기
 *
 */
public class P10986_나머지합 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] S = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            S[i] = S[i - 1] + Integer.parseInt(st.nextToken());
        }

        long answer = 0;
/*
        //이중 for문 : 시간초과
        for (int s = 0; s < N + 1; s++) {
            for (int e = s+1; e < N + 1; e++) {
                if ((S[e] - S[s])%M == 0) answer++;
            }
        }
*/
        /**
         * S는 구간합이므로 구간합S의 각 idx에서 M으로 나눈 나머지값을 카운팅하여
         * 같은 나머지 값을 가진 두 개를 골라 빼면 나머지가 0이 된다.
         *
         * ex> S[1] = 2+2+3 = 7, S[2] = 2+2+3+(-3) = 4, M = 3
         * S[1]%3=1 , S[2]%3=1
         * S[2]-S[1] = 4-7 = -3  =>  -3%3 = 0
         */

        long[] remainder = new long[M];
        for (int i = 1; i < N + 1; i++) {
            int remain = (int) (S[i] % M);
            if (remain == 0) {
                answer++;
            }
            remainder[remain]++;
        }

        for (int i = 0; i < M; i++) {
            if (remainder[i] > 1) {
                answer = answer + (remainder[i] * (remainder[i]-1) / 2);
            }
        }

        System.out.println(answer);
    }
}
