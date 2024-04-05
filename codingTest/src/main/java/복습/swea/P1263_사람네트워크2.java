package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 플로이드 워샬
 */
public class P1263_사람네트워크2 {
    static int T, answer, N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if (i != j && map[i][j] == 0) {
                        map[i][j] = Integer.MAX_VALUE / 2 - 1;
                    }
                }
            }


            for (int K = 0; K < N; K++) {
                for (int S = 0; S < N; S++) {
                    for (int E = 0; E < N; E++) {
                        map[S][E] = Math.min(map[S][E], map[S][K] + map[K][E]);
                    }
                }
            }

            answer = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                int sum = 0;
                for (int j = 0; j < N; j++) {
                    sum += map[i][j];
                }
                answer = Math.min(answer, sum);
            }
            System.out.println("#" + test_case + " " + answer);
        }
    }
}
