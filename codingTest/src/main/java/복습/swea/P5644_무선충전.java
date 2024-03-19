package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5644_무선충전 {
    static int T, M, A, result;
    static int[][] P1, P2;
    static int[][] BC;
    static int[][] deltas = {{0, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            P1 = new int[M+1][2];
            P2 = new int[M+1][2];

            P1[0][0] = 1;
            P1[0][1] = 1;

            P2[0][0] = 10;
            P2[0][1] = 10;

            BC = new int[A][4]; // !!!!!!!!!!!!! y좌표(=row) x좌표(=col) !!!!!!!!!!!!!!충전범위 처리량

            st = new StringTokenizer(br.readLine());
            for (int time = 1; time <= M; time++) {
                int num = Integer.parseInt(st.nextToken());
                move(P1, num, time);
            }

            st = new StringTokenizer(br.readLine());
            for (int time = 1; time <= M; time++) {
                int num = Integer.parseInt(st.nextToken());
                move(P2, num, time);
            }

            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    BC[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            result = 0;
            timeTrace();
            System.out.println("#" + test_case + " " + result);
        }
    }

    private static void timeTrace() {
        for (int t = 0; t <= M; t++) {
            result += maxChargeAtT(t);
        }
    }

    private static int maxChargeAtT(int t) {
        int max = 0;

        for (int bc1 = 0; bc1 < A; bc1++) {
            int distance1 = getDistance(BC[bc1][1], BC[bc1][0], P1[t][0], P1[t][1]);
            int amount1 = 0;
            if (distance1 <= BC[bc1][2]) {
                amount1 = BC[bc1][3];
            }

            for (int bc2 = 0; bc2 < A; bc2++) {

                int distance2 = getDistance(BC[bc2][1], BC[bc2][0], P2[t][0], P2[t][1]);
                int amount2 = 0;
                if (distance2 <= BC[bc2][2]) {
                    amount2 = BC[bc2][3];
                }

                int sum = 0;
                if (bc1 != bc2) sum = amount1 + amount2; //다른 BC이면
                else sum = Math.max(amount1, amount2); //같은 BC이면 합은 같으므로

                max = Math.max(max, sum);
            }
        }

        return max;
    }

    private static void move(int[][] p, int num, int time) {
        if (num == 0) { //이동하지 않음
            p[time][0] = p[time - 1][0];
            p[time][1] = p[time - 1][1];
        }

        if (num == 1) { //상
            p[time][0] = p[time - 1][0] - 1;
            p[time][1] = p[time - 1][1];

        }

        if (num == 2) { //우
            p[time][0] = p[time - 1][0];
            p[time][1] = p[time - 1][1] + 1;
        }

        if (num == 3) { //하
            p[time][0] = p[time - 1][0] + 1;
            p[time][1] = p[time - 1][1];
        }

        if (num == 4) { //좌
            p[time][0] = p[time - 1][0];
            p[time][1] = p[time - 1][1] - 1;
        }
    }

    private static int getDistance(int bc_x, int bc_y, int p_x, int p_y) {
        return Math.abs(bc_x - p_x) + Math.abs(bc_y - p_y);
    }
}
