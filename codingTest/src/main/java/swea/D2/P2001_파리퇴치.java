package swea.D2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2001_파리퇴치 {
    static int T;
    static int N, M;
    static int[][] map;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < N; col++) {
                    map[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            max = 0;

            for (int row = 0; row <= N - M; row++) { // M만큼 돌 공간은 남겨줘야함
                for (int col = 0; col <= N - M; col++) {
                    int sum = 0;
                    for (int r = 0; r < M; r++) { // M만큼 돌기
                        for (int c = 0; c < M; c++) {
                            sum += map[row + r][col + c];
                        }
                    }
                    max = Math.max(max, sum);
                }
            }

            /*//슬라이딩 윈도우 초기화
            int initialSum = 0;
            for (int row = 1; row <= M; row++) {
                for (int col = 1; col <= M; col++) {
                    initialSum += map[row][col];
                }
            }


            for (int row = M; row <= N; row++) {
                int sum = initialSum;

                for (int col = M; col <= N; col++) {
                    int prevCol = col - M;

                    if (row == M && col == M) continue;

                    for (int r = row-M; r <= row; r++) {
                        int sum1 = map[r][prevCol];
                        sum -= sum1;
                        int sum2 = map[r][col];
                        sum += sum2;
                    }

                    max = Math.max(max, sum);
                }
            }*/

            System.out.println("#" + test_case + " " + max);
        }
    }
}
