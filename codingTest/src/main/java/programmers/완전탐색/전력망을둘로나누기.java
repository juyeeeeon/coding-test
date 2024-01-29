package programmers.완전탐색;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class 전력망을둘로나누기 {
    static ArrayList<Integer>[] arr;
    static boolean[] visited;
    static Set<Integer> result;

    public static void main(String[] args) {
        System.out.println(solution(9, new int[][]{{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}}));
    }

    public static int solution(int n, int[][] wires) {
        arr = new ArrayList[n + 1];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ArrayList<>();
        }

        result = new HashSet<>();

        for (int i = 0; i < wires.length; i++) {
            int s = wires[i][0];
            int e = wires[i][1];

            arr[s].add(e);
            arr[e].add(s);
        }

        for (int i = 0; i < wires.length; i++) {
            int s = wires[i][0];
            int e = wires[i][1];

            visited = new boolean[n + 1];

            DFS(s, e);

            int cnt = 0;
            for (boolean v : visited) if (v) cnt++;

            result.add(Math.abs(n - 2 * cnt));
        }


        return Collections.min(result);

    }

    private static void DFS(int s, int e) {

        if (visited[s]) return;
        visited[s] = true;

        for (Integer cur : arr[s]) {
            if (visited[cur] || cur == e) continue;
            DFS(cur, e);
        }
    }
}
