package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10971_외판원순회2 {
    static int N;
    static int minCost = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //임의의 점 1에서 출발
        dfs(1, 0, 1);

        System.out.println(minCost);
    }

    private static void dfs(int city, int cost, int cnt) {
        if (cnt == N) {
            if (map[city][1] != 0) {
                minCost = Math.min(minCost, cost + map[city][1]);
            }
            return;
        }

        if (visited[city]) return;
        visited[city] = true;

        for (int to = 1; to < N + 1; to++) {
            if (!visited[to] && map[city][to] != 0) {
                dfs(to, cost + map[city][to], cnt + 1);
            }
        }

        visited[city] = false;
    }
}
