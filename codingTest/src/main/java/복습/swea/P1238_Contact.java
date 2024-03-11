package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1238_Contact {
    static int T = 10;
    static int L, S, maxDepth, maxResult;
    static ArrayList<Integer>[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());

            arr = new ArrayList[101];
            for (int i = 1; i < 101; i++) {
                arr[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                if (arr[from].contains(to)) continue;
                arr[from].add(to);
            }

            visited = new boolean[101];
            maxDepth = Integer.MIN_VALUE;
            maxResult = Integer.MIN_VALUE;
            bfs();

            System.out.println("#" + test_case + " " + maxResult);

        }
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{S, 0});
        visited[S] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int vertex = cur[0];
            int depth = cur[1];

            if (depth == maxDepth) maxResult = Math.max(maxResult, vertex);
            if (depth > maxDepth) {
                maxDepth = depth;
                maxResult = vertex;
            }

            for (int node : arr[vertex]) {
                if (visited[node]) continue;
                visited[node] = true;
                queue.add(new int[]{node, depth + 1});
            }
        }

    }
}
