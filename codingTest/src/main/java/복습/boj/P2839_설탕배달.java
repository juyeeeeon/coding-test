package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2839_설탕배달 {
    static int N, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        result = 0;

        while (N > 0) {
            if (N % 5 == 0) {
                result += N / 5;
                N %= 5;
                break;
            } else {
                N -= 3;
                result++;
            }
        }

        if (N < 0) System.out.println(-1);
        else System.out.println(result);
    }
}
