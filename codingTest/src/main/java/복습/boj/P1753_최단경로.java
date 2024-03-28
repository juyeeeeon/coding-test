package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1753_최단경로 {
    static int V, E, K;
    static int[] distance;
    static ArrayList<Node>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        arr = new ArrayList[V + 1];
        for (int i = 1; i < V + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arr[s].add(new Node(e, w));
        }

        distance = new int[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[K] = 0;

        dijkstra();

        for (int i = 1; i < V + 1; i++) {
            if (distance[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(distance[i]);
        }

    }

    private static void dijkstra() {
        boolean[] visited = new boolean[V + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(K, 0));
//        visited[K] = true;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            //한 노드에 대해 여러 경로를 고려하므로, 더 정확한 최단 거리를 계산할 수 있다.
            //한 노드가 우선순위 큐에 여러 번 추가될 수 있으며, 이는 다양한 경로를 통한 최단 거리 계산을 가능
            //노드에 대한 최단 거리가 확정된 후에 방문 처리
            if (visited[cur.vertex]) continue;
            visited[cur.vertex] = true;

            for (Node next : arr[cur.vertex]) {
//                if (!visited[next.vertex]) {
                    if (distance[next.vertex] > distance[cur.vertex] + next.weight) {
                        distance[next.vertex] = distance[cur.vertex] + next.weight;
                        pq.add(new Node(next.vertex, distance[next.vertex]));
//                        visited[next.vertex] = true;
//                    }
                }
            }
        }
    }

    private static class Node implements Comparable<Node>{
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
