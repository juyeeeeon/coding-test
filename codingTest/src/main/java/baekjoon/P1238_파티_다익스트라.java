package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 다익스트라(O(ElogV))
 */
public class P1238_파티_다익스트라 {
    static int N, M, X;
    static int[] distance;
    static boolean[] visited;
    static ArrayList<Node>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[s].add(new Node(e, c));
        }


        int answer = Integer.MIN_VALUE;
        for (int i = 1; i < N + 1; i++) {
            dijkstra(i);
            int iToX = distance[X];
            dijkstra(X);
            int XToI = distance[i];

            answer = Math.max(answer, iToX + XToI);
        }

        System.out.println(answer);

    }

    private static void dijkstra(int start){

        distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0; //!!!!!!!!!!!!

        visited = new boolean[N+1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.vertex]) continue;
            visited[cur.vertex] = true;

            for (Node next : arr[cur.vertex]) {
                if (distance[next.vertex] > distance[cur.vertex] + next.cost) {
                    distance[next.vertex] = distance[cur.vertex] + next.cost;
                    pq.add(new Node(next.vertex, distance[next.vertex]));
                }
            }
        }

    }

    private static class Node implements Comparable<Node>{
        int vertex;
        int cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}
