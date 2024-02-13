package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P16435_스네이크버드 {
    static int N, L;
    static int[] fruitsHeight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        fruitsHeight = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fruitsHeight[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(fruitsHeight);

        for (int i = 0; i < fruitsHeight.length; i++) {
            if (fruitsHeight[i] <= L) L++;
            else break;
        }

        System.out.println(L);
    }
}
