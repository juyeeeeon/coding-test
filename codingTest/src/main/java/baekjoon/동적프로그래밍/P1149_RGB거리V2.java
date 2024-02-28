package baekjoon.동적프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1149_RGB거리V2 {
    static int N;
    static int[][] memoization;
    static int[][] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        memoization = new int[N + 1][3];
        costs = new int[N + 1][3]; //[][0]:R, [][1]:G, [][2]:B


        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int costR = Integer.parseInt(st.nextToken());
            int costG = Integer.parseInt(st.nextToken());
            int costB = Integer.parseInt(st.nextToken());

            costs[i][0] = Math.min(costs[i-1][1], costs[i-1][2]) + costR;
            costs[i][1] = Math.min(costs[i-1][0], costs[i-1][2]) + costG;
            costs[i][2] = Math.min(costs[i-1][0], costs[i-1][1]) + costB;
        }

        System.out.println(Math.min(costs[N][0], Math.min(costs[N][1], costs[N][2])));
    }
}
