package baekjoon.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * O(N)
 */
public class P1940_주몽_투포인터 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int answer = 0;
        Arrays.sort(arr);

        int s = 0;
        int e = arr.length-1;

        while (s < e) {
            int sum = arr[s] + arr[e];
            if (sum == M) {
                answer++;
                s++;
            }
            if (sum < M) {
                s++;
            }
            if (sum > M) {
                e--;
            }
        }
        System.out.println(answer);
    }
}
