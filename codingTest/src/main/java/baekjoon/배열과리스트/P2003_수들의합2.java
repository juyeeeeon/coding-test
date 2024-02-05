package baekjoon.배열과리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    백준 2003번
    수들의 합2
 */
public class P2003_수들의합2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int result = 0;

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int sum = arr[i];
            if (sum == M) {
                result++;
                continue;
            }

            for (int j = i+1; j < N; j++) {
                sum += arr[j];

                if (sum == M) {
                    result++;
                    break;
                }

                if (sum > M) break;
            }
        }

        System.out.println(result);
    }

}
