package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1697_숨바꼭질 {
    static int N, K, minTime;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        minTime = Integer.MAX_VALUE;
        visited = new boolean[100001];

        BFS();

        System.out.println(minTime);

    }

    private static void BFS() {
        Queue<int[]> pq = new ArrayDeque<>();
        pq.add(new int[]{N, 0});
        visited[N] = true;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int cur = now[0];
            int time = now[1];

            if (cur == K) {
                minTime = time;
                return;
            }

            int[] deltas = getDeltas(cur);
            for (int d = 0; d < deltas.length; d++) {
                int np = deltas[d];
                if (np < 0 || np > 100000 || visited[np]) continue;
                pq.add(new int[]{np, time + 1});
                visited[np] = true;
            }
        }
    }

    private static int[] getDeltas(int n){
        return new int[]{n - 1, n + 1, n * 2};
    }
}
