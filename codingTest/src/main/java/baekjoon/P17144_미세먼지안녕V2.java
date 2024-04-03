package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P17144_미세먼지안녕V2 {
    static int R, C, T, AP1, AP2;
    static ArrayList<int[]> mise;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        mise = new ArrayList<>();
        map = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {

            miseSpread();

            operatePurifierUp();
            operatePurifierDown();

            mise = new ArrayList<>();
            blankToMap();
        }

        int sum = 0;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] != 0 && map[r][c] != -1) {
                    sum += map[r][c];
                }
            }
        }

        System.out.println(sum);

    }

    private static void blankToMap() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                map[r][c] = blank[r][c];
                if (map[r][c] != -1 && map[r][c] > 0) mise.add(new int[]{r, c, map[r][c]});
            }
        }
    }

    private static void operatePurifierDown() {
        for (int r = AP2 + 1; r < R - 1; r++) {
            blank[r][0] = blank[r + 1][0];
        }

        for (int c = 0; c < C - 1; c++) {
            blank[R - 1][c] = blank[R - 1][c + 1];
        }

        for (int r = R-1; r > AP2; r--) {
            blank[r][C - 1] = blank[r - 1][C - 1];
        }

        for (int c = C-1; c > 1 ; c--) {
            blank[AP2][c] = blank[AP2][c-1];
        }
        blank[AP2][1] = 0;
    }

    private static void operatePurifierUp() {
        for (int r = AP1-1; r >0 ; r--) {
            blank[r][0] = blank[r-1][0];
        }

        for (int c = 0; c < C-1; c++) {
            blank[0][c] = blank[0][c+1];
        }

        for (int r = 0; r < AP1; r++) {
            blank[r][C - 1] = blank[r + 1][C - 1];
        }

        for (int c = C-1; c > 1; c--) {
            blank[AP1][c] = blank[AP1][c - 1];
        }
        blank[AP1][1] = 0;
    }

    private static void miseSpread() {
        for (int i = 0; i < mise.size(); i++) {
            int r = mise.get(i)[0];
            int c = mise.get(i)[1];
            int amount = mise.get(i)[2];

            int cnt = 0;

            for (int d = 0; d < deltas.length; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];

                if (isValid(nr,nc) && blank[nr][nc] != -1) {
                    blank[nr][nc] += amount / 5;
                    cnt++;
                }
            }

            blank[r][c] += amount  - amount/ 5 * cnt;
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

}
