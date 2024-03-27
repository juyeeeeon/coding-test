package swea.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 최장증가수열(LIS) 이진탐색으로 구하기  O(NlogN)
 */
public class P3307_최장증가부분수열_이진탐색 {
    static int T, N;
    static int[] arr, lis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            lis = new int[N]; //i번째 자리에 올 수 있는 수 중 가장 작은 값. 담은 수들은 최장증가수열과 상관 없음!

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int size = 1;
            Arrays.fill(lis, Integer.MAX_VALUE);
            lis[0] = arr[0];
            for (int i = 1; i < N; i++) {
                if (lis[size - 1] > arr[i]) {
                    // 이진 탐색으로 O(logN)
                    int index = Arrays.binarySearch(lis, arr[i]);
                    if (index >= 0) { //lis에 arr[i]값이 존재하면
                        lis[index] = arr[i];
                    } else { //lis에 arr[i]값이 존재하지 않는다면 (-insertion position-1)반환
                        lis[-index - 1] = arr[i];
                    }
                } else if (lis[size - 1] < arr[i]) {
                    lis[size++] = arr[i];
                }
            }

            System.out.println("#" + test_case + " " + size);
        }
    }
}
