package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2667_단지번호붙이기 {
    static int N, danji;
    static int[] count;
    static int[][] map;
    static boolean[][] visitied;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            char[] charArr = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = charArr[j] - '0';
            }
        }

        danji = 1;
        visitied = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visitied[i][j]) {
                    dfs(i, j);
                    danji++;
                }
            }
        }

        count = new int[danji];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    count[map[i][j]]++;
                }
            }
        }

        System.out.println(danji - 1);
        Arrays.sort(count);
        for (int i =1; i < count.length; i++) {
            System.out.println(count[i]);
        }

    }

    private static void dfs(int i, int j) {
        if (visitied[i][j]) return;
        visitied[i][j] = true;

        map[i][j] = danji;

        for (int d = 0; d < deltas.length; d++) {
            int nr = i + deltas[d][0];
            int nc = j + deltas[d][1];

            if (isValid(nr, nc) && !visitied[nr][nc] && map[nr][nc] == 1) {
                dfs(nr, nc);
            }

        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
