package org.example.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1449_수리공항승 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int ptr1 = arr.length-2;
        int ptr2 = ptr1+1;

        int answer = 0;

        while (ptr1 >= 0 && ptr2 >= 0) {
            if (arr[ptr2] - arr[ptr1] <= L - 1) {
                ptr1--;
            } else {
                answer++;
                ptr2 = ptr1;
                ptr1--;
            }
        }

        System.out.println(answer+1);
    }
}
