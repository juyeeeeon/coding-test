package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Longest Increasing Sequence
 * 이진탐색 이용 O(NlogN)
 */
public class P11053_가장긴증가하는부분수열_이진탐색 {
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

        LIS = new int[N]; //자릿수(=index)의 크기에 올 수 있는 가장 작은 수

        //LIS 배열 초기화
        Arrays.fill(LIS, Integer.MAX_VALUE);
        LIS[0] = arr[0];

        int size = 1; //최장증가수열의 크기
        for (int i = 1; i < N; i++) {
            if (LIS[size - 1] > arr[i]) {
                int index = Arrays.binarySearch(LIS, arr[i]); //LIS에서 arr[i]가 들어갈 index를 구한다.
                if (index < 0) LIS[-index-1] = arr[i]; //arr[i]가 LIS 배열 안에 존재하지 않을 때
            } else if(LIS[size-1] < arr[i]){
                LIS[size++] = arr[i];
            }
        }

        System.out.println(size);
    }
}
