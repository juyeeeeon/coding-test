package baekjoon.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1074_ZV2 {
    static long N, r, c, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        //2^N
        func(0,0, (int) Math.pow(2, N));
    }

    /**
     *
     * @param startRow : 시작 행
     * @param startCol : 시작 열
     * @param n : 좌표평면의 크기 (n x n)
     */
    private static void func(long startRow, long startCol,  long n) {
        //해당 좌표가 우리가 찾으려는 좌표이면
        if (startRow == r && startCol == c) {
            System.out.println(result);
            return;
        }

        //크기가 n인 좌표평면 안에 우리가 찾으려는 좌표가 존재하지 않으면
        if (!(r >= startRow && r < startRow + n && c >= startCol && c < startCol + n)) {
            result += n * n;
            return;
        }

        func(startRow, startCol, n / 2);
        func(startRow, startCol + n/2, n / 2);
        func(startRow + n / 2, startCol,n / 2);
        func(startRow + n / 2, startCol + n/2, n / 2);
    }
}
