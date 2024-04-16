package swea.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 최장증가수열(LIS) DB로 구하기  O(N^2)
 */
public class P3307_최장증가부분수열_dp {
    static int T, N;
    static int[] arr, lis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            lis = new int[N]; //arr[i]를 부분 수열의 마지막으로 포함하는 최대 길이

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < N; i++) {
                lis[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (arr[j] < arr[i] && lis[j] >= lis[i]) {
                        lis[i] = lis[j] + 1;
                    }
                }
            }

            Arrays.sort(lis);
            System.out.println("#" + test_case + " " + lis[lis.length - 1]);
        }
    }
}
