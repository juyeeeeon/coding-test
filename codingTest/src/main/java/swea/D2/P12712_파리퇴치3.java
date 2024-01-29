package swea.D2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P12712_파리퇴치3 {
    static int N;
    static int M;

    static int[] dx1 = {1, 0, -1, 0};
    static int[] dy1 = {0, -1, 0, 1};

    static int[] dx2 = {1, 1, -1, -1};
    static int[] dy2 = {1, -1, 1, -1};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        ArrayList<Integer> result;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            result = new ArrayList<>();
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            int[][] arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int sum1 = arr[i][j];
                    int sum2 = arr[i][j];

                    for (int k = 0; k < 4; k++) {
                        for (int l = 1; l < M; l++) {
                            int nx1 = i + dx1[k] * l;
                            int ny1 = j + dy1[k] * l;

                            int nx2 = i + dx2[k] * l;
                            int ny2 = j + dy2[k] * l;

                            if (nx1 >= 0 && nx1 < N && ny1 >= 0 && ny1 < N) {
                                sum1 += arr[nx1][ny1];
                            }

                            if (nx2 >= 0 && nx2 < N && ny2 >= 0 && ny2 < N) {
                                sum2 += arr[nx2][ny2];
                            }
                        }
                    }

                    result.add(sum1);
                    result.add(sum2);
                }
            }

            bw.write("#" + test_case + " " + Collections.max(result) + "\n");


        }

        bw.flush();
        bw.close();
        br.close();
    }
}