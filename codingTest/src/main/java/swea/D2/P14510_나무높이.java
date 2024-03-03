package swea.D2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14510_나무높이 {
    static int T, N;
    static int maxH, result, even, odd;

    static int[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            trees = new int[N];

            maxH = -1;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
                maxH = Math.max(maxH, trees[i]);
            }

            //나무가 자라야 할 높이에서 필요한 1, 2의 개수 구하기
            even = odd = 0;

            for (int i = 0; i < N; i++) {
                int diff = maxH - trees[i];

                if (diff == 0) continue;

                even += diff / 2;
                odd += diff % 2;
            }

            //2 -> 1로 변경
            while ((even - odd) > 1) {
                even--;
                odd += 2;
            }

            if (even == odd) result = even + odd;
            else if (even > odd) result  = even * 2;
            else result = odd * 2 - 1; //필요한 물의 양이 [1,1,1,2]일 때

            System.out.println("#" + test_case + " " + result);
        }
    }


}
