package baekjoon.구간합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1806_부분합V2 {
    static int N, S, min;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        min = Integer.MAX_VALUE;
        int s = 0;
        int e = 0;
        int sum = 0;

        while (s <= N && e <= N) {
            if (sum < S) {
                sum += arr[e++];
            } else{
                min = Math.min(min, e - s);
                sum -= arr[s++];
            }
        }

        if (min == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(min);
    }

}
