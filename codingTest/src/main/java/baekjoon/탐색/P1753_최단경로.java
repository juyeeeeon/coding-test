package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 다익스트라(ElogV)
 * 시작점으로부터 PriorityQueue을 사용하여 Distance 배열 업데이트
 *
 * 1) 인접리스트로 그래프 구현
 * 2) 최단거리배열 초기화 : 출발노드 = 0, 이외는 무한으로
 * 3) PriorityQueue을 이용하여 최단거리배열에서 값이 가장 작은 노드 고르기
 * 4) 최단거리배열 업데이트하기
 * 5) 모든 노드가 처리될 때까지 반복
 */
public class P1753_최단경로 {
    static int V, E, K;
    static int[] distance;
    static boolean[] visited;
    static PriorityQueue<Node> pq;
    static ArrayList<Node>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());


        arr = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr[u].add(new Node(v, w));
        }

        visited = new boolean[V + 1];
        distance = new int[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[K] = 0;

        bfs();

        for (int i = 1; i < distance.length; i++) {
            if (distance[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(distance[i]);
        }

    }

    private static void bfs() {
        pq = new PriorityQueue<>();
        pq.add(new Node(K, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.v]) continue;
            visited[cur.v] = true;

            for (Node node : arr[cur.v]) {
                if (distance[node.v] > distance[cur.v] + node.distance) {
                    distance[node.v] = distance[cur.v] + node.distance;
                    pq.add(new Node(node.v, distance[node.v])); //주어진 출발점에서 현재까지 node.v까지의 거리의 최솟값
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int v;
        int distance; //주어진 출발점에서 현재까지 v까지의 거리의 최솟값

        public Node(int v, int w) {
            this.v = v;
            this.distance = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
}
