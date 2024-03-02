package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P17471_게리멘더링 {
    static int N;
    static int minPopulDiff = Integer.MAX_VALUE;
    static int[] population;
    static boolean[] area, visited;
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        area = new boolean[N + 1];

        arr = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                arr[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        dfs(1);

        if (minPopulDiff == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minPopulDiff);
    }

    /**
     * 선거구 조합
     * @param city
     */
    private static void dfs(int city) {
        if (city == N + 1) { //모든 도시를 선택하였다면
            if(onlyOneArea()) return; //하나의 선거구만 존재하는 경우
            if (validArea()) { //선거구끼리 연결되었으면
                minPopulDiff = Math.min(minPopulDiff, getPupulDiff()); //인구차이의 최솟값 업데이트
            }
            return;
        }

        area[city] = true;
        dfs(city + 1); //해당 도시가 포함되는 경우
        area[city] = false;
        dfs(city + 1); //해당 도시가 포함 안되는 경우
    }

    /**
     *
     * @return 하나의 선거구만 존재하는지
     */
    private static boolean onlyOneArea() {
        int cnt = 0;
        for (int i = 1; i < N+1; i++) {
            if (area[i]) cnt++;
        }

        if (cnt == 0 || cnt == N) return true;
        return false;
    }

    /**
     *
     * @return 인구 차이
     */
    private static int getPupulDiff() {
        int result1 = 0;
        int result2 = 0;

        for (int i = 1; i < N + 1; i++) {
            if (area[i]) result1 += population[i];
            else result2 += population[i];
        }

        return Math.abs(result1 - result2);

    }

    /**
     *
     * @return 두 선거구가 연결되었는지
     */
    private static boolean validArea() {
        int start1 = 0;
        int start2 = 0;
        for (int i = 1; i < N + 1; i++) {
            if (area[i]) {
                start1 = i;
                break;
            }
        }

        for (int i = 1; i < N + 1; i++) {
            if (!area[i]) {
                start2 = i;
                break;
            }
        }

        visited = new boolean[N + 1];
        bfs(start1, true);
        bfs(start2, false);

        for (int i = 1; i < N + 1; i++) {
            if (!visited[i]) return false;
        }

        return true;
    }

    private static void bfs(int s, boolean party) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty()) {
            Integer cur = queue.poll();

            for (Integer node : arr[cur]) {
                if (!visited[node] && area[node] == party) {
                    queue.add(node);
                    visited[node] = true;
                }
            }
        }
    }
}
