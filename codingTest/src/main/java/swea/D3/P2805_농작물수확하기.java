package swea.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2805_농작물수확하기 {
    static int N;
    static int sum;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for (int r = 0; r < N; r++) {
                String line = br.readLine();
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(line.substring(c, c + 1));
                }
            }

            sum = 0;

            //마름모의 가운데를 자름

            //위의 삼각형
            add1(0, N / 2, N / 2, N / 2 + 1);
            //아래의 삼각형
            add2(N / 2 + 1, N - 1, 1, N - 1);

            System.out.println("#" + test_case + " " + sum);
        }
    }

    /**
     *
     * @param row: 현재 행
     * @param endRow: 끝 행
     * @param s: 더하기 시작할 index
     * @param e: 더하기를 마칠 index
     */
    private static void add1(int row, int endRow, int s, int e) {
        if (row > endRow) return;

        for (int c = s; c < e; c++) {
            sum += map[row][c];
        }

        add1(row + 1, endRow, s - 1, e + 1);
    }

    private static void add2(int row, int endRow, int s, int e) {
        if (row > endRow) return;

        for (int c = s; c < e; c++) {
            sum += map[row][c];
        }

        add2(row + 1, endRow, s + 1, e - 1);
    }
}

