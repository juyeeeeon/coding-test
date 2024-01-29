package baekjoon.자료구조.구간합;

import java.io.*;
import java.util.StringTokenizer;

public class P11660_구간합구하기5V1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] sMap = new int[N + 1][N + 1];

        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= N; col++) {
                sMap[row][col] += sMap[row][col-1] + Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int sum = 0;

            for (int row = x1; row <= x2; row++) {
                sum += sMap[row][y2] - sMap[row][y1-1];
            }

            bw.write(Integer.toString(sum) + '\n');
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
