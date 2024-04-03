package swea.D4;

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
    static ArrayList<Integer>[] arr;
    static ArrayList<Integer>[] reverse;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            arr = new ArrayList[N + 1];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = new ArrayList<>();
            }
            reverse = new ArrayList[N + 1];
            for (int i = 0; i < reverse.length; i++) {
                reverse[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int small = Integer.parseInt(st.nextToken());
                int big = Integer.parseInt(st.nextToken());
                arr[small].add(big);
                reverse[big].add(small);
            }

            answer = 0;

            for (int i = 1; i <= N ; i++) {
                cnt = 0;

                visited = new boolean[N + 1];

                bfs(arr, i);
                visited[i] = false;
                cnt--;
                bfs(reverse, i);

                if (cnt == N) answer++;
//                if (allVisited()) answer++;

            }
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static boolean allVisited() {
        for (int i = 1; i < N+1; i++) {
            if (!visited[i]) return false;
        }

        return true;
    }


    private static void bfs(ArrayList<Integer>[] list, int i) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(i);
        visited[i] = true;
        cnt++;

        while (!queue.isEmpty()) {
            Integer cur = queue.poll();

            for (Integer next : list[cur]) {
                if (visited[next]) continue;
                queue.add(next);
                visited[next] = true;
                cnt++;
            }
        }
    }
}
