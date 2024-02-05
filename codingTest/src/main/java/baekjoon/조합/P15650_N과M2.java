package baekjoon.조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15650_N과M2 {
    static int N; //1 ~ N 까지의 수
    static int M; //길이가 M 인 수열
    static int[] numbers; //조건을 만족하는 수열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[M];

        //조합
        comb(0, 1);
    }

    /**
     *
     * @param len : 기존까지 수열의 길이
     * @param start : 시작할 수열의 값
     */
    private static void comb(int len, int start) {
        if (len == M) {
            for (int number : numbers) {
                System.out.print(number + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i <= N; i++) {
            numbers[len] = i;
            comb(len + 1, i + 1);
        }
    }


}
