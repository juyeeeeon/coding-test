package baekjoon.재귀;

import java.io.*;
import java.math.BigInteger;

public class P1914_하노이탑 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        System.out.println(BigInteger.TWO.pow(N).subtract(BigInteger.ONE));

        if (N <= 20) {
            hanoi(N, 1, 2, 3);
        }

        bw.flush();
        bw.close();
        br.close();

    }

    /**
     *
     * @param n: 원판갯수
     * @param start: 시작 기둥
     * @param tmp: 임시 기둥
     * @param end: 목적 기둥
     */
    private static void hanoi(long n, long start, long tmp, long end) throws IOException {
        if ( n == 0 ) return;

        //맨 밑의 원판을 제외한 나머지 원판(n-1)을 start에서 tmp로 이동
        hanoi(n - 1, start, end, tmp);
        //맨 밑의 원판을 start에서 end로 이동
        bw.write(start + " " + end + "\n");
        //tmp로 이동한 원판(n-1)을 end로 이동
        hanoi(n - 1, tmp, start, end);
    }
}

