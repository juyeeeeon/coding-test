package baekjoon.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1253_좋다 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int answer = 0;

        long[] arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            long goodNum = arr[i];

            int s = 0;
            int e = arr.length-1;

            while (s < e) {
                if (arr[s] + arr[e] == goodNum) {
                    //음수값도 고려해야 하므로
                    if (s != i && e != i) {
                        answer++;
                        break;
                    }
                    if (s == i) s++;
                    if (e == i) e--;
                }else if (arr[s] + arr[e] < goodNum) s++;
                else if (arr[s] + arr[e] > goodNum) e--;
            }
        }

        System.out.println(answer);

    }
}
