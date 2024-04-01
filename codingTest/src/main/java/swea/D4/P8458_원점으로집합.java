package swea.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P8458_원점으로집합 {
    static int T, N, max;
    static int[] dots;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            dots = new int[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Math.abs(Integer.parseInt(st.nextToken()));
                int y = Math.abs(Integer.parseInt(st.nextToken()));
                dots[i] = x + y;
            }

            boolean flag = true;
            max = Integer.MIN_VALUE;
            for (int i = 0; i < N-1; i++) {
                if ( dots[i] % 2 != dots[i + 1] % 2) {
                    System.out.println("#" + test_case + " " + -1);
                    flag = false;
                    break;
                } else {
                    max = Math.max(max, dots[i]);
                }
            }
            max = Math.max(max, dots[N - 1]);

            int sum = 0;
            int i = 0;
            while (true) {
                sum += i;
                if (sum >= max && (sum - max) % 2 == 0) break;
                i++;
            }

            if (flag) System.out.println("#" + test_case + " " + i);
        }


    }

}
