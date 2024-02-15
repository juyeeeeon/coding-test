package baekjoon.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1074_Z {
    static long N, r, c, result;
    static long num = 0;
    static boolean find = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        //2^N
        func(0,0, (int) Math.pow(2, N));

        System.out.println(result);

    }

    private static void func(long startRow, long startCol,  long n) {
        if (find) return;

        if (n == 2) {
            if (startRow == r && startCol == c) {
                result = num;
                find = true;
                return;
            }
            num++;


            if (startRow == r && startCol + 1 == c) {
                result = num;
                find = true;
                return;
            }
            num++;

            if (startRow + 1 == r && startCol == c) {
                result = num;
                find = true;
                return;
            }
            num ++;

            if (startRow + 1 == r && startCol + 1 == c) {
                result = num;
                find = true;
                return;
            }
            num++;

            return;
        }

        if (r >= startRow && r < startRow + n / 2 && c >= startCol && c < startCol + n / 2)  //위왼
            func(startRow, startCol, n / 2);
        else
            num += (n / 2) * (n / 2);

        if (r >= startRow && r < startRow + n / 2 && c >= startCol + n/2 && c < startCol + n) //위오
            func(startRow, startCol + n/2, n / 2);
        else
            num += (n / 2) * (n / 2);

        if (r >= startRow + n / 2 && r < startRow + n && c >= startCol && c < startCol + n / 2) //아래왼
            func(startRow + n / 2, startCol,n / 2);
        else
            num += (n / 2) * (n / 2);

        if (r >= startRow + n / 2 && r < startRow + n && c >= startCol + n/2 && c < startCol + n) //아래오
            func(startRow + n / 2, startCol + n/2, n / 2);
        else
            num += (n / 2) * (n / 2);
    }
}
