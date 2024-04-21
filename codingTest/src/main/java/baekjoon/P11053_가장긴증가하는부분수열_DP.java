package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Longest Increasing Sequence
 * DP 이용 O(N^2)
 */

public class P11053_가장긴증가하는부분수열_DP {
    static int N;
    static int[] arr, LIS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        LIS = new int[N]; //arr[i]를 부분수열의 마지막으로 포함하는 최대길이

        for (int i = 0; i < N; i++) {
            LIS[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && LIS[j] >= LIS[i]) {
                    LIS[i] = LIS[j] + 1;
                }
            }
        }

        Arrays.sort(LIS);
        System.out.println(LIS[LIS.length - 1]);
    }
}
