package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P14510_나무높이 {
    static int T, N, two, one, answer, max;
    static int[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            answer = 0;
            max = 0;

            N = Integer.parseInt(br.readLine());
            trees = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
                if (trees[i] > max) max = trees[i];
            }


            one = two = 0;

            for (int i = 0; i <N; i++) {
                int diff = max - trees[i];

                if (diff != 0) {
                    two += diff / 2;
                    one += diff % 2;
                }
            }

            while ((two - one) > 1) {
                one += 2;
                two -= 1;
            }

            if (one == two) answer = one * 2;
            else if(one < two) answer = two * 2;
            else answer = one * 2 - 1;

            /*while (true) {
                if (one == two || one == two + 1) {
                    answer = one + two;
                    break;
                } else if (one + 1 == two) {
                    answer = one + two + 1;
                    break;
                }else{
                    one += 2;
                    two -= 1;
                }
            }*/

            System.out.println("#" + test_case + " " + answer);
        }
    }
}
