package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 배달 {
    static int[] distance;
    static ArrayList<Node>[] arr;

    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        arr = new ArrayList[N+1];
        for(int i=0; i<N+1; i++){
            arr[i] = new ArrayList<>();
        }

        for(int[] r : road){
            int s = r[0];
            int e = r[1];
            int c = r[2];

            arr[s].add(new Node(e, c));
            arr[e].add(new Node(s, c));
        }

        distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;

        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.node]) continue;
            visited[cur.node] = true;

            for (Node next : arr[cur.node]) {
                if (distance[next.node] > distance[cur.node] + next.cost){
                    distance[next.node] = distance[cur.node] + next.cost;
                    pq.add(new Node(next.node, distance[next.node]));
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            if (distance[i] <= K) answer++;
        }

        return answer;
    }

    class Node implements Comparable<Node>{
        int node;
        int cost;

        Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}
