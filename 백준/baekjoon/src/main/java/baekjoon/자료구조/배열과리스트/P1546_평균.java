package baekjoon.자료구조.배열과리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1546_평균 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];


        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        int M = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num > M) M = num;
            sum += num;
        }

        System.out.println(1.0*sum/M*100/N);
    }
}
