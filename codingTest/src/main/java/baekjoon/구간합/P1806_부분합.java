package baekjoon.구간합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1806_부분합 {
    static int N, S, min;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] >= S) {
                System.out.println(1);
                return;
            }
        }

        min = Integer.MAX_VALUE;
        int s = 0;
        int e = 1;
        int sum = arr[s] + arr[e];

        while (true) {
            if (sum < S) {
                e++;
                if (e > arr.length-1) break;
                sum += arr[e];
            }

            if (sum >= S) {
                min = Math.min(min, e - s + 1);
                sum -= arr[s];
                s++;
            }

            if (e < s || s >= arr.length - 1) {
                break;
            }
        }

        if (sum >= S) {
            min = Math.min(min, e - s + 1);
        }

        if (min == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(min);
    }

}
