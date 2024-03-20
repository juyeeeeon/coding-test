package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P4012_요리사 {
    static int T, N, result;
    static boolean[] selected;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            result = Integer.MAX_VALUE;
            selected = new boolean[N];
            dfs(0, 0);

            System.out.println("#" + test_case + " " + result);
        }
    }

    private static void dfs(int cnt, int start) {
        if (cnt == N / 2) {

            int synergyA = 0;
            int synergyB = 0;
            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (selected[i] && selected[j])
                        synergyA += map[i][j] + map[j][i];
                    else if (!selected[i] && !selected[j]) {
                        synergyB += map[i][j] + map[j][i];
                    }
                }
            }

            int diff = Math.abs(synergyA - synergyB);
            result = Math.min(result, diff);

            return;
        }

        for (int i = start; i < N; i++) {
            if (selected[i]) continue;
            selected[i] = true;
            dfs(cnt + 1, i + 1);
            selected[i] = false;
        }
    }
}
