package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P9205_맥주마시면서걸어가기 {
    static int t, n, houseX, houseY, rockX, rockY;
    static String answer;
    static boolean[] visited;
    static int[][] convi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        t = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= t; test_case++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            houseX = Integer.parseInt(st.nextToken());
            houseY = Integer.parseInt(st.nextToken());

            convi = new int[n][2];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                convi[i][0] = Integer.parseInt(st.nextToken());
                convi[i][1] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            rockX = Integer.parseInt(st.nextToken());
            rockY = Integer.parseInt(st.nextToken());


            answer = "sad";
            visited = new boolean[n];
            dfs(houseX, houseY);

            System.out.println(answer);

        }
    }

    private static void dfs(int x, int y) {
        if (getDistance(x, y, rockX, rockY) <= 1000) {
            answer = "happy";
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && getDistance(x, y, convi[i][0], convi[i][1]) <= 1000) {
                visited[i] = true;
                dfs(convi[i][0], convi[i][1]);
            }

        }
    }

    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
