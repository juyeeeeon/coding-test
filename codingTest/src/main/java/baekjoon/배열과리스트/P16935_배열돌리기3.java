package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P16935_배열돌리기3 {

    static int N, M, R;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int no = Integer.parseInt(st.nextToken());
            switch (no) {
                case 1:
                    func1();
                    break;
                case 2:
                    func2();
                    break;
                case 3:
                    func3();
                    break;
                case 4:
                    func4();
                    break;
                case 5:
                    func5();
                    break;
                case 6:
                    func6();
                    break;
            }
        }

        print();
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     *  (r1,c1) <-> (r2,c2)
     */
    private static void swap(int r1, int c1, int r2, int c2) {
        int tmp = map[r1][c1];
        map[r1][c1] = map[r2][c2];
        map[r2][c2] = tmp;
    }

    /**
     * 상하 반전
     */
    private static void func1() {
        for (int r = 0; r < N / 2; r++) {
            for (int c = 0; c < M; c++) {
                swap(r, c, N - r - 1, c);
            }
        }
    }

    /**
     * 좌우 반전
     */
    private static void func2() {
        for (int c = 0; c < M / 2; c++) {
            for (int r = 0; r < N; r++) {
                swap(r, c, r, M - c - 1);
            }
        }
    }

    /**
     * 오른쪽으로 90도 회전
     */
    private static void func3() {
        int[][] newMap = new int[M][N];

        Queue<Integer> queue = new ArrayDeque<>();

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                queue.add(map[row][col]);
            }
        }
        for (int col = N - 1; col >= 0; col--) {
            for (int row = 0; row < M; row++) {
                newMap[row][col] = queue.poll();
            }
        }

        int tmp = N;
        N = M;
        M = tmp;

        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                map[row][col] = newMap[row][col];
            }
        }
    }

    /**
     * 왼쪽으로 90도 회전
     */
    private static void func4() {
        Queue<Integer> queue = new ArrayDeque<>();

        int[][] newMap = new int[M][N];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                queue.add(map[row][col]);
            }
        }

        for (int col = 0; col < N; col++) {
            for (int row = M - 1; row >= 0; row--) {
                newMap[row][col] = queue.poll();
            }
        }

        int tmp = N;
        N = M;
        M = tmp;

        map = new int[N][M];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                map[row][col] = newMap[row][col];
            }
        }
    }

    /**
     * 1번 그룹의 부분 배열을 2번 그룹 위치로, 2번을 3번으로, 3번을 4번으로, 4번을 1번으로 이동
     */
    private static void func5() {
        int[][] newMap = new int[N][M];

        for (int row = 0; row < N / 2; row++) {
            for (int col = 0; col < M / 2; col++) {
                newMap[row][col + M / 2] = map[row][col];
            }
        }

        for (int row = 0; row < N/2; row++) {
            for (int col = M / 2; col < M; col++) {
                newMap[row + N / 2][col] = map[row][col];
            }
        }

        for (int row = N / 2; row < N; row++) {
            for (int col = M / 2; col < M; col++) {
                newMap[row][col - M / 2] = map[row][col];
            }
        }

        for (int row = N / 2; row < N; row++) {
            for (int col = 0; col < M / 2; col++) {
                newMap[row - N / 2][col] = map[row][col];
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = newMap[i][j];
            }
        }


    }

    /**
     * 1번 그룹의 부분 배열을 4번 그룹 위치로, 4번을 3번으로, 3번을 2번으로, 2번을 1번으로 이동
     */
    private static void func6() {
        int[][] newMap = new int[N][M];

        for (int row = 0; row < N / 2; row++) {
            for (int col = 0; col < M / 2; col++) {
                newMap[row + N / 2][col] = map[row][col];
            }
        }

        for (int row = N / 2; row < N; row++) {
            for (int col = 0; col < M / 2; col++) {
                newMap[row][col + M / 2] = map[row][col];
            }
        }

        for (int row = N / 2; row < N; row++) {
            for (int col = M / 2; col < M; col++) {
                newMap[row - N / 2][col] = map[row][col];
            }
        }

        for (int row = 0; row < N/2; row++) {
            for (int col = M / 2; col < M; col++) {
                newMap[row][col - M / 2] = map[row][col];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = newMap[i][j];
            }
        }
    }
}
