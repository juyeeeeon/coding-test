package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2023_신기한소수 {
    static int N;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        //한자리 소수는 2, 3, 5, 7 뿐이므로,
        //이 숫자에서부터 시작하여 이 숫자 뒤로 숫자를 붙여가며 찾는다.
        //ex> 2.., 3.., 5.., 7..
        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);
    }

    private static void DFS(int number, int cnt) {
        if (cnt == N) {
            if (isPrime(number)) System.out.println(number);
            return;
        }

        for (int i = 1; i < 10; i++) {
            if (i % 2 == 0) continue; //짝수
            if (isPrime(number * 10 + i)) DFS(number * 10 + i, cnt + 1);
        }
    }

    private static boolean isPrime(int number) {
        for (int i = 2; i*i <= number; i++) {
            if (number%i == 0) return false;
        }
        return true;
    }
}
