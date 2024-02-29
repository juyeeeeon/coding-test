package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10971_외판원순회2 {
    static int N;
    static int minResult = Integer.MAX_VALUE;
    static int startNode = 0; //임의의 시작점
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        dfs(startNode, 0, 1);

        System.out.println(minResult);

    }


    /**
     *
     * @param node 현재 노드
     * @param cost 현재까지의 비용
     * @param cnt 방문 노드 수
     */
    private static void dfs(int node, int cost, int cnt) {
        if (visited[node]) return;

        if (cnt == N) {
            if (map[node][startNode] != 0){ //마지막노드에서 원점으로 연결되어 있을 때
                minResult = Math.min(minResult, cost + map[node][startNode]);
            }
            return;
        }

        visited[node] = true;

        for (int i = 0; i < N; i++) {
            if (i == node || map[node][i] == 0) continue; //자기자신으로 가거나 갈 수 없는 노드일 경우
            dfs(i, cost + map[node][i], cnt + 1); //다음 노드로 이동
        }
        visited[node] = false;
    }


}
