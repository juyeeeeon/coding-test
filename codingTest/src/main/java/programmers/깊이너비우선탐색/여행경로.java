package programmers.깊이너비우선탐색;

import java.util.*;

public class 여행경로 {
    static int N;
    static boolean[] visited;
    static ArrayList<String> routes;

    public String[] solution(String[][] tickets) {
        String[] answer = {};

        N = tickets.length;
        visited = new boolean[N];
        routes = new ArrayList<>();

        DFS(tickets, "ICN", 0, "ICN");


        Collections.sort(routes);
        answer = routes.get(0).split(" ");

        return answer;
    }

    private void DFS(String[][] tickets, String now, int depth, String path) {
        if (depth == tickets.length) {
            routes.add(path);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && now.equals(tickets[i][0])) {
                visited[i] = true;
                DFS(tickets, tickets[i][1], depth + 1, path + " " + tickets[i][1]);
                visited[i] = false;
            }
        }

    }

}
