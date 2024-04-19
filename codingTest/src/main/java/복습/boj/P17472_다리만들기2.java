/*
package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P17472_다리만들기2 {
    static int N, M, region;
    static int[] parents;
    static PriorityQueue<int[]> pq;
    static boolean[][] visited;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        region = 1;
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    bfs(i, j); //섬 넘버링
                    region++;
                }
            }
        }

        region -= 1;

        //섬을 연결하는 모든 다리 구하기
        pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    for (int d = 0; d < deltas.length; d++) {
                        makeBridge(i, j, map[i][j], 0 , d);
                    }

                }
            }
        }

        parents = new int[region + 1];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        int answer = 0;
        int numOfBridge = 0;
        while (!pq.isEmpty()) {
            if (numOfBridge == region - 1) {
                break;
            }
            int[] cur = pq.poll();
            int start = cur[0];
            int end = cur[1];
            int len = cur[2];

            if (find(start) != find(end)) {
                union(start, end);
                answer += len;
                numOfBridge++;
            }
        }

        //!!!!!!!!!!!
        if (numOfBridge != region - 1) System.out.println(-1);
        else System.out.println(answer);

    }

    private static void union(int start, int end) {
        int rootStart = find(start);
        int rootEnd = find(end);

        if (rootStart != rootEnd) parents[rootEnd] = rootStart; //!!!!!!!!!
    }

    private static int find(int start) {
        if (parents[start] == start) return start;

        return parents[start] = find(parents[start]);
    }

    private static void makeBridge(int i, int j, int start, int cnt, int d) {
        int nr = i + deltas[d][0];
        int nc = j + deltas[d][1];

        if (!isValid(nr, nc)) return;
        if (map[nr][nc] == start) return;

        if (map[nr][nc] > 0){
            if (cnt <= 1) return;

            pq.add(new int[]{start, map[nr][nc], cnt});
            return;
        }

        makeBridge(nr, nc, start, cnt + 1, d);

    }


    */
/**
     * 섬 넘버링
     *//*

    private static void bfs(int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];

            map[r][c] = region;

            for (int d = 0; d < deltas.length; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];

                if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] != 0) {
                    queue.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }

    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
*/
