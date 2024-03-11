package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1697_숨바꼭질 {
    static int N, K, minTime;
    static boolean leftDir;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        minTime = Integer.MAX_VALUE;

        if (N <= K) leftDir = false;
        else leftDir = true;

        BFS();

        System.out.println(minTime);

    }

    private static void BFS() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{N, 0});

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int cur = now[0];
            int time = now[1];

            if (cur == K) {
                minTime = Math.min(minTime, time);
                return;
            }

            pq.add(new int[]{cur - 1, time + 1});
            pq.add(new int[]{- 2 * cur, time + 1});
            pq.add(new int[]{cur + 1, time + 1});
            pq.add(new int[]{2 * cur, time + 1});

        }
    }
}
