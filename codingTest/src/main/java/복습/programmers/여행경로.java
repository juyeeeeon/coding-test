package 복습.programmers;

import java.util.*;

public class 여행경로 {
    static int N;
    static ArrayList<String> results;
    static boolean[] visited;

    public String[] solution(String[][] tickets) {

        N = tickets.length;

        results = new ArrayList<>();
        visited = new boolean[N];
        dfs(tickets, "ICN", 0, "ICN");

        Collections.sort(results);

        return results.get(0).split(" ");
    }

    private void dfs(String[][] tickets, String cur, int cnt, String route) {
        if (cnt == N) {
            results.add(route);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (tickets[i][0].equals(cur) && !visited[i]) {
                visited[i] = true;
                dfs(tickets, tickets[i][1], cnt + 1, route + " " + tickets[i][1]);
                visited[i] = false;
            }
        }
    }

}
