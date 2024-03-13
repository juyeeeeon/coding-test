package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P5014_스타트링크 {
    static int F, S, G, U, D, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken()); //총 F층
        S = Integer.parseInt(st.nextToken()); //강호 위치
        G = Integer.parseInt(st.nextToken()); //스타링크 위치

        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        result = Integer.MAX_VALUE;
        bfs();
        if (result == Integer.MAX_VALUE) System.out.println("use the stairs");
        else System.out.println(result);

    }

    private static void bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        boolean[] visited = new boolean[F + 1];
        pq.add(new int[]{S, 0});
        visited[S] = true;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int location = cur[0];
            int cnt = cur[1];

            if (location == G) {
                result = cnt;
                return;
            }

            int up = location + U;
            int down = location - D;

            if (up >= 1 && up <= F && !visited[up]) {
                pq.add(new int[]{up, cnt + 1});
                visited[up] = true;
            }
            if (down >= 1 && down <= F && !visited[down]) {
                pq.add(new int[]{down, cnt + 1});
                visited[down] = true;
            }
        }

    }
}
