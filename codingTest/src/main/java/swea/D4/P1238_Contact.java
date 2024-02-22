package swea.D4;

import java.io.*;
import java.util.*;

public class P1238_Contact {
    static final int T = 10;
    static int N, S;
    static boolean[] visited;
    static int maxDepth;
    static int maxValue;
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            visited = new boolean[101];
            arr = new ArrayList[101];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                if (arr[from].contains(to)) continue;
                arr[from].add(to);
            }

            for (int i = 0; i < arr.length; i++) {
                Collections.sort(arr[i]); //오름차순 정렬
            }

            maxDepth = Integer.MIN_VALUE;
            maxValue = Integer.MIN_VALUE;
            bfs(S);

/*            while (!result.isEmpty()) {
                System.out.println(result.poll().value);
            }*/
            bw.write("#" + test_case + " " + maxValue + '\n');
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int s) {
        Queue<Node> queue = new ArrayDeque<>();
        int depth = 0;
        queue.add(new Node(s, depth));
        visited[s] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.depth == maxDepth && cur.value > maxValue) {
                maxValue = cur.value;
            }

            if (cur.depth > maxDepth) {
                maxValue = cur.value;
                maxDepth = cur.depth;
            }

            for (int v : arr[cur.value]) {
                if (visited[v]) continue;
                queue.add(new Node(v, cur.depth + 1));
                visited[v] = true;
            }
        }
    }

    static class Node{
        int value;
        int depth;

        public Node(int value, int depth) {
            this.value = value;
            this.depth = depth;
        }
    }
}
