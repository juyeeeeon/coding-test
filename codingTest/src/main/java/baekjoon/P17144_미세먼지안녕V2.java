package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P17144_미세먼지안녕V2 {
    static int R, C, T, AP1, AP2;
    static ArrayList<int[]> dust;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == -1) {
                    if (AP1 != 0) AP2 = r;
                    else AP1 = r;
                }
            }
        }

        for (int i = 0; i < T; i++) {
            dust = new ArrayList<>();

            //미세먼지 위치와 양 담기
            setDustInfo();

            //미세먼지 퍼트리기
            miseSpread();

            operatePurifierUp();
            operatePurifierDown();
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

    private static void setDustInfo() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] > 0) {
                    dust.add(new int[]{r, c, map[r][c]});
                    map[r][c] = 0;
                }
            }
        }
    }

    private static void miseSpread() {
        for (int i = 0; i < dust.size(); i++) {
            int r = dust.get(i)[0];
            int c = dust.get(i)[1];
            int amount = dust.get(i)[2];

            int cnt = 0;

            for (int d = 0; d < deltas.length; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];

                if (isValid(nr,nc) && map[nr][nc] != -1) {
                    map[nr][nc] += amount / 5;
                    cnt++;
                }
            }

            map[r][c] += amount  - amount/ 5 * cnt;
        }
    }

    private static void operatePurifierDown() {
        for (int r = AP2 + 1; r < R - 1; r++) {
            map[r][0] = map[r + 1][0];
        }

        for (int c = 0; c < C - 1; c++) {
            map[R - 1][c] = map[R - 1][c + 1];
        }

        for (int r = R-1; r > AP2; r--) {
            map[r][C - 1] = map[r - 1][C - 1];
        }

        for (int c = C-1; c > 1 ; c--) {
            map[AP2][c] = map[AP2][c-1];
        }
        map[AP2][1] = 0;
    }

    private static void operatePurifierUp() {
        for (int r = AP1-1; r >0 ; r--) {
            map[r][0] = map[r-1][0];
        }

        for (int c = 0; c < C-1; c++) {
            map[0][c] = map[0][c+1];
        }

        for (int r = 0; r < AP1; r++) {
            map[r][C - 1] = map[r + 1][C - 1];
        }

        for (int c = C-1; c > 1; c--) {
            map[AP1][c] = map[AP1][c - 1];
        }
        map[AP1][1] = 0;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

}
