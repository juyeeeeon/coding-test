package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P13549_숨바꼭질3 {
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
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        queue.add(new int[]{N, 0});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int cur = now[0];
            int time = now[1];

            if (cur == K) {
                minTime = time;
            }

            if (visited[cur]) continue;
            visited[cur] = true;

            int[] deltas = getDeltas(cur);
            for (int d = 0; d < deltas.length; d++) {
                int np = deltas[d];
                if (np < 0 || np > 100000 || visited[np]) continue;
                if (d == 2) queue.add(new int[]{np, time});
                else queue.add(new int[]{np, time + 1});
            }
        }
    }

    private static int[] getDeltas(int n){
        return new int[]{n * 2, n - 1, n + 1};
    }
}
