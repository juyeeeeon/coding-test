package swea.D5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1247_최적경로V2 {
    static int T, N;
    static int company_x, company_y; //회사의 x, y좌표
    static int home_x, home_y; //집의 x, y좌표
    static int[][] map;
    static boolean[] visited; //고객 방문 배열
    static int minDistance; //최소거리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][2]; //[][0]:고객의 x좌표 [][1]:고객의 y좌표

            StringTokenizer st = new StringTokenizer(br.readLine());
            company_x = Integer.parseInt(st.nextToken());
            company_y = Integer.parseInt(st.nextToken());

            home_x = Integer.parseInt(st.nextToken());
            home_y = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                map[i][0] = Integer.parseInt(st.nextToken());
                map[i][1] = Integer.parseInt(st.nextToken());
            }

            visited = new boolean[N];
            minDistance = Integer.MAX_VALUE;
            recursion(company_x, company_y, 0, 0); //회사에서 출발

            System.out.println("#" + test_case + " " + minDistance);
        }
    }

    /**
     *
     * @param x
     * @param y
     * @param distance
     * @param cnt : 방문횟수
     */
    private static void recursion(int x, int y, int distance, int cnt) {
        if (cnt == N) { //모든 고객의 집을 방문하였다면(방문횟수cnt을 기저조건으로)
            minDistance = Math.min(minDistance, distance + Math.abs(x-home_x) + Math.abs(y-home_y));
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue; //고객의 집을 이미 방문하였다면

            int nx = map[i][0];
            int ny = map[i][1];
            visited[i] = true;
            recursion(nx, ny, distance + Math.abs(x - nx) + Math.abs(y - ny), cnt + 1);
            visited[i] = false;
        }

    }
}
