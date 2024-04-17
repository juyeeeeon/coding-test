package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1446_지름길 {
    static int N, D;
    static int[] distance;
    static ArrayList<Node>[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        arr = new ArrayList[D + 1];
        for (int i = 0; i < D; i++) {
            arr[i] = new ArrayList<>();
            arr[i].add(new Node(i + 1, 1));
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[s].add(new Node(e, c));
        }

        distance = new int[D + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;

        boolean[] visited = new boolean[D + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.n]) continue;
            visited[cur.n] = true;

            for (Node next : arr[cur.n]) {
                if (distance[next.n]>distance[cur.n]+next.c){
                    distance[next.n] = distance[cur.n]+ next.c;
                    pq.add(new Node(next.n, distance[next.n]));
                }
            }
        }

        System.out.println(distance[D]);


    }

    private static class Node implements Comparable<Node>{
        int n;
        int c;

        Node(int n, int c){
            this.n = n;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return c-o.c;
        }
    }
}
