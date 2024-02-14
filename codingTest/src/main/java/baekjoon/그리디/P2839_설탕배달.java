package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2839_설탕배달 {
    static int N;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        while (N > 0) {
            if (N % 5 == 0) { //N이 5로 나누어 떨어질 때
                result += N / 5;
                N %= 5;
            } else {
                N -= 3; //3 횟수 증가
                result++;
            }
        }

        if (N == 0) System.out.println(result); //나누어 떨어졌을 때
        else System.out.println(-1); //나누어 떨이지지 않을 때

    }
}
