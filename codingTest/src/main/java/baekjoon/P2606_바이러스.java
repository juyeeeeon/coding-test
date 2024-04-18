package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2606_바이러스 {
    static int N, M, answer;
    static ArrayList<Integer>[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        arr = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            arr[s].add(e);
            arr[e].add(s);
        }

        answer = 0;
        visited = new boolean[N + 1];
        bfs(1);

        System.out.println(answer-1);
    }

    private static void bfs(int i) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(i);
        visited[i] = true;

        while (!queue.isEmpty()) {
            Integer cur = queue.poll();

            answer++;

            for (Integer next : arr[cur]) {
                if (visited[next]) continue;
                queue.add(next);
                visited[next] = true;
            }
        }
    }
}
