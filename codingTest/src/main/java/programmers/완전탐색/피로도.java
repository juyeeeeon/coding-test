package programmers.완전탐색;

import java.util.ArrayList;
import java.util.Collections;

public class 피로도 {
    public static void main(String[] args) {
        System.out.println(solution(80, new int[][]{{80,20},{50,40},{30,10}}));
    }
    static boolean[] visited;
    static ArrayList<Integer> arr;

    public static int solution(int k, int[][] dungeons) {

        visited = new boolean[dungeons.length];
        arr = new ArrayList<>();

        for (int i = 0; i < dungeons.length; i++) {
            DFS(k, i, dungeons, 0);
        }

        Integer max = Collections.max(arr);

        return max;

    }

    private static void DFS(int k, int i, int[][] dungeons, int count) {
        if (visited[i] || k < dungeons[i][0]) return;

        visited[i] = true;
        arr.add(count+1);

        for (int j = 0; j < dungeons.length; j++) {
            if (visited[j]) continue;
            DFS(k - dungeons[i][1], j, dungeons, count + 1);
        }
        visited[i] = false;

    }
}
