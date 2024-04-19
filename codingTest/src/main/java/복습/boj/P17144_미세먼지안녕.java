package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P17144_미세먼지안녕 {
    static int AP_firstRow, AP_secondRow;
    static int R, C, T, answer;
    static ArrayList<int[]> dusts;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        AP_firstRow = AP_secondRow = Integer.MAX_VALUE;
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (AP_firstRow == Integer.MAX_VALUE && map[r][c] == -1) {
                    AP_firstRow = r;
                    AP_secondRow = r + 1;
                }
            }
        }

//        System.out.println("AP_firstRow = " + AP_firstRow);
//        System.out.println("AP_secondRow = " + AP_secondRow);

        for (int t = 0; t < T; t++) {
            dustSpread();
//            System.out.println("=============");
//            printMap();

            operateAP();
//            System.out.println("after");
//            printMap();
        }

        answer = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] > 0) answer += map[r][c];
            }
        }

        System.out.println(answer);

    }

    private static void dustSpread() {
        dusts = new ArrayList<>();

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] > 0) {
                    dusts.add(new int[]{r, c, map[r][c]});
                    map[r][c] = 0;
                }
            }
        }

        for (int i = 0; i < dusts.size(); i++) {
            int r = dusts.get(i)[0];
            int c = dusts.get(i)[1];
            int amount = dusts.get(i)[2];
            int cnt = 0;

            for (int d = 0; d < deltas.length; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];

                if (isValid(nr, nc) && map[nr][nc] != -1) {
                    map[nr][nc] += amount / 5;
                    cnt++;
                }
            }

            map[r][c] += amount - (amount / 5) * cnt;
        }


    }

    //!!!!!!!!!!! for문 조건 잘 확인하기!
    private static void operateAP() {
        for (int r = AP_firstRow - 1; r > 0; r--) {
            map[r][0] = map[r-1][0];
        }
        for (int c = 0; c < C - 1; c++) {
            map[0][c] = map[0][c + 1];
        }
        for (int r = 0; r < AP_firstRow; r++) {
            map[r][C - 1] = map[r + 1][C - 1];
        }
        for (int c = C - 1; c > 1; c--) {
            map[AP_firstRow][c] = map[AP_firstRow][c - 1];
        }
        map[AP_firstRow][1] = 0;

        for (int r = AP_secondRow + 1; r < R-1; r++) {
            map[r][0] = map[r+1][0];
        }
        for (int c = 0; c < C - 1; c++) {
            map[R-1][c] = map[R-1][c + 1];
        }
        for (int r = R-1; r > AP_secondRow; r--) {
            map[r][C - 1] = map[r - 1][C - 1];
        }
        for (int c = C - 1; c > 1; c--) {
            map[AP_secondRow][c] = map[AP_secondRow][c - 1];
        }
        map[AP_secondRow][1] = 0;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

    private static void printMap() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                System.out.print(map[r][c] + " ");
            }

            System.out.println();
        }
    }
}
