package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P5643_키순서 {
    static int T, N, M, answer, cnt;
    static boolean[] visited;
    static ArrayList<Integer>[] arr, reverse;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            arr = new ArrayList[N + 1];
            reverse = new ArrayList[N + 1];
            for (int i = 0; i < N + 1; i++) {
                arr[i] = new ArrayList<>();
                reverse[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                arr[s].add(e);
                reverse[e].add(s);
            }

            answer = 0;
            for (int i = 1; i <= N; i++) {
                cnt = 0;
                visited = new boolean[N + 1];
                bfs(i, arr);
                bfs(i, reverse);

                if (cnt == N-1) answer++;
            }

            System.out.println("#" + test_case + " " + answer);

        }
    }

    private static void bfs(int i, ArrayList<Integer>[] lists) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(i);
        visited[i] = true; //해도 되고 안해도 되고

        while (!queue.isEmpty()) {
            Integer cur = queue.poll();

            for (Integer next : lists[cur]) {
                if (visited[next]) continue;
                visited[next] = true;
                cnt++;
                queue.add(next);
            }
        }
    }
}
