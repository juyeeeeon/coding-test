package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1247_최적경로 {
    static int T, N, min;
    static int[] company, house;
    static boolean[] visited;
    static int[][] customers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());

            customers = new int[N][2];
            company = new int[2];
            house = new int[2];

            StringTokenizer st = new StringTokenizer(br.readLine());
            company[0] = Integer.parseInt(st.nextToken());
            company[1] = Integer.parseInt(st.nextToken());
            house[0] = Integer.parseInt(st.nextToken());
            house[1] = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                customers[i][0] = Integer.parseInt(st.nextToken());
                customers[i][1] = Integer.parseInt(st.nextToken());
            }

            min = Integer.MAX_VALUE;
            visited = new boolean[N];
            dfs(company[0], company[1], 0, 0);
            System.out.println("#" + test_case + " " + min);

        }
    }

    private static void dfs(int x, int y, int cnt, int distance) {
        if (cnt == N) {
            min = Math.min(min, distance + getDistance(x, y, house[0], house[1]));
            return;
        }

        for (int cus = 0; cus < N; cus++) {
            int cx = customers[cus][0];
            int cy = customers[cus][1];

            if (!visited[cus]){
                visited[cus] = true;
                dfs(cx, cy, cnt + 1, distance + getDistance(x, y, cx, cy));
                visited[cus] = false;
            }
        }


    }

    private static int getDistance(int x, int y, int cx, int cy) {
        return Math.abs(x - cx) + Math.abs(y - cy);
    }
}
