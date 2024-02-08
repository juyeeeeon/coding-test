package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// visited[]을 사용하면 쉽게 풀 수 있음
public class P4012_요리사V2 {
    static int T, N;
    static int min;
    static boolean[] visited;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            visited = new boolean[N];
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            min = Integer.MAX_VALUE;
            comb(0, 0);

            System.out.println("#" + test_case + " " + min);
        }
    }

    /**
     *
     * @param cnt : 현재까지 고른 조합의 원소의 개수
     * @param start
     */
    private static void comb(int cnt, int start) {
        if (cnt == N / 2) {
            int sumA = 0;
            int sumB = 0;

            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {

                    if (visited[i] && visited[j]) {
                        sumA += map[i][j] + map[j][i];
                    } else if (!visited[i] && !visited[j]) {
                        sumB += map[i][j] + map[j][i];
                    }
                }
            }

            min = Math.min(min, Math.abs(sumA - sumB));

            return;
        }


        for (int i = start; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            comb(cnt + 1, i + 1);
            visited[i] = false;
        }
    }

}
