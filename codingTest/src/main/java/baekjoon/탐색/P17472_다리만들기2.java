package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P17472_다리만들기2 {
    static int N, M;
    static int[] parents;
    static int[][] map;
    static boolean[][] visited;
    static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];


        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        //섬 넘버링
        int number = 1;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 1 && !visited[r][c]) {
                    bfs(r, c, number);
                    number++;
                }
            }
        }

        //모든 다리만들기
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] != 0) {
                    for (int d = 0; d < deltas.length; d++) {
                        dfs(map[r][c], r, c, 0, d);
                    }
                }
            }
        }


        parents = new int[number];
        for (int i = 1; i < number; i++) {
            parents[i] = i;
        }

        int cnt = 0;
        int answer = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (find(cur.s) != find(cur.e)) {
                union(cur.s, cur.e);
                cnt++;
                answer += cur.w;
            }
        }

        if (cnt != number-2) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void union(int s, int e) {
        int rootS = find(s);
        int rootE = find(e);

        if (rootS != rootE) parents[rootE] = rootS;
    }

    private static int find(int i) {
        if (parents[i] == i) return i;

        return parents[i] = find(parents[i]);
    }

    /**
     * 다리만들기
     */
    private static void dfs(int s, int r, int c, int cnt, int d) {
        int nr = r + deltas[d][0];
        int nc = c + deltas[d][1];

        if (isValid(nr, nc) && map[nr][nc] == 0) {
            dfs(s, nr, nc, cnt + 1, d);
        }
        if (isValid(nr, nc) && map[nr][nc] != 0 && map[nr][nc] != s && cnt > 1) {
            pq.add(new Edge(s, map[nr][nc], cnt));
        }
    }

    /**
     * 섬 넘버링
     */
    private static void bfs(int r, int c, int number) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            map[cur[0]][cur[1]] = number;

            for (int d = 0; d < deltas.length; d++) {
                int nr = cur[0] + deltas[d][0];
                int nc = cur[1] + deltas[d][1];

                if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc});
                }
            }
        }

    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

    static class Edge{
        int s;
        int e;
        int w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "s=" + s +
                    ", e=" + e +
                    ", w=" + w +
                    '}';
        }
    }
}
