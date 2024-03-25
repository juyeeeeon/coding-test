package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Ttmp {
    static int T, N;
    static int[][] map;
    static ArrayList<int[]> cores;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            map = new int[N][N];
            cores = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1 && i != 0 && i != N - 1 && j != 0 && j != N - 1) {
                        cores.add(new int[]{i, j});
                    }
                }
            }

            if (cores.size() == 0) System.out.println("#" + test_case + " " + 0);
            else {
                dfs(0);
            }

        }

    }

    private static void dfs(int idx) {
        if (idx == cores.size()) {

            return;
        }

        for (int d = 0; d < deltas.length; d++) {
            draw(d, cores[idx], 9);

            draw(d, cores[idx], 0);
        }
    }

}
