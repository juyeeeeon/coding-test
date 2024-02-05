package baekjoon.조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2004_조합0의개수 {
    static int n; //n 개 중에
    static int m; //중복을 허용하지 않고 m개 뽑기

    /**
     * nCm = n! / ((n-m)! * m!)
     *
     * 끝자리 0의 개수는 2*5의 개수와 같다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int cnt5 = cnt_5(n) - cnt_5(n - m) - cnt_5(m);
        int cnt2 = cnt_2(n) - cnt_2(n - m) - cnt_2(m);

        System.out.println(Math.min(cnt2, cnt5));
    }


    /**
     *
     * @param n
     * @return : 2의 개수
     *
     * n!에서의 2의 개수는 n이 2보다 작을 때까지 2로 나눈 값을 더해준 값과 같다.
     */
    private static int cnt_2(int n) {
        int cnt = 0;

        while (n >= 2) {
            cnt += n / 2;
            n /= 2;
        }

        return cnt;

    }

    /**
     *
     * @param n
     * @return : 5의 개수
     * n!에서의 5의 개수는 n이 5보다 작을 때까지 5로 나눈 값을 더해준 값과 같다.
     */
    private static int cnt_5(int n) {
        int cnt = 0;

        while (n >= 5) {
            cnt += n / 5;
            n /= 5;
        }

        return cnt;
    }
}
