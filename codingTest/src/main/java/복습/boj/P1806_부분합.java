package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1806_부분합 {
    static int N, S, minLen;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
//        arr = new int[N];
        arr = new int[N + 1];


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        minLen = Integer.MAX_VALUE;

        /*int s = 0;
        int e = 0;
        int sum = arr[0];
        while (s <= e && e < N) {
            if (sum < S) {
                if (++e >= N) break;
                sum += arr[e];
            } else {
                minLen = Math.min(minLen, e - s + 1);
                sum -= arr[s++];

                if (s > e) break;
            }
        }*/

        int s = 0;
        int e = 0;
        int sum = 0;

        while (s <= N && e <= N) {
            if (sum < S) {
                sum += arr[e++];
            } else{
                minLen = Math.min(minLen, e - s);
                sum -= arr[s++];
            }
        }

        if (minLen == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(minLen);
    }
}
