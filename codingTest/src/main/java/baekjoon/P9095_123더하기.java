package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9095_123더하기 {
    static int T, n, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            answer = 0;
            recursion(0);

            System.out.println(answer);
        }

    }

    private static void recursion(int sum) {
        if (sum > n) {
            return;
        }

        if (sum == n) {
            answer++;
            return;
        }

        for (int i = 1; i <= 3; i++) {
            sum += i;
            recursion(sum);
            sum -= i;
        }
    }
}
