package 복습.programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class 네트워크 {
    static ArrayList<Integer>[] arr;
    static boolean[] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0 ;

        arr = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (computers[r][c] == 1) arr[r].add(c);
            }
        }

        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            bfs(i);
            answer++;
        }


        return answer;
    }

    private void bfs(int i) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(i);
        visited[i] = true;

        while (!queue.isEmpty()) {
            Integer cur = queue.poll();

            for (Integer next : arr[cur]) {
                if (visited[next]) continue;
                queue.add(next);
                visited[next] = true;
            }
        }
    }
}
