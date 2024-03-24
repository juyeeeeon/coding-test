package 복습.swea;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2117_홈방범서비스2 {
    static int T, N, M, maxHome, cnt;
    static ArrayList<int[]> homes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            homes = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num == 1) homes.add(new int[]{r, c});
                }
            }

            maxHome = Integer.MIN_VALUE;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    for (int k = 1; k <= N + 1; k++) {
                        cnt = 0;
                        for (int[] home : homes) {
                            if (getDistance(home[0], home[1], r, c) + 1 <= k) {
                                cnt++;
                            }
                        }
                        if (cnt * M - (k * k + (k - 1) * (k - 1)) >= 0) {
                            maxHome = Math.max(maxHome, cnt);
                        }
                    }
                }
            }

            bw.write("#" + test_case + " " + maxHome + "\n");

        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}
