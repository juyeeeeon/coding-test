package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1504_특정한최단경로 {
    static int N, E, answer;
    static int[] distance, midPoints;
    static ArrayList<Node>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[s].add(new Node(e, c));
            arr[e].add(new Node(s, c));
        }

        midPoints = new int[2];
        st = new StringTokenizer(br.readLine());
        midPoints[0] = Integer.parseInt(st.nextToken());
        midPoints[1] = Integer.parseInt(st.nextToken());

        dijkstra(1);
        int startToM0 = distance[midPoints[0]];
        int startToM1 = distance[midPoints[1]];

//        System.out.println("startToM0 = " + startToM0);
//        System.out.println("startToM1 = " + startToM1);

        dijkstra(midPoints[0]);
        int M0M1 = distance[midPoints[1]];
        int M0ToN = distance[N];

//        System.out.println("M0M1 = " + M0M1);
//        System.out.println("M0ToN = " + M0ToN);

        dijkstra(midPoints[1]);
        int M1ToN = distance[N];

//        System.out.println("M1ToN = " + M1ToN);

        int case1 = startToM0 + M0M1 + M1ToN;
        int case2 = startToM1 + M0M1 + M0ToN;

//        System.out.println("case1 = " + case1);
//        System.out.println("case2 = " + case2);

        answer = Math.min(case1, case2);
        if (answer >= Integer.MAX_VALUE/3-1) System.out.println(-1);
        else System.out.println(answer);
    }

    private static void dijkstra(int start) {
        distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE/3 - 1);

        distance[start] = 0;
        boolean[] visited = new boolean[N + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.v]) continue;
            visited[cur.v] = true;

            for (Node next : arr[cur.v]) {
                if (distance[next.v] > distance[cur.v] + next.c) {
                    distance[next.v] = distance[cur.v] + next.c;
                    pq.add(new Node(next.v, distance[next.v]));
                }
            }
        }
    }

    private static class Node implements Comparable<Node>{
        int v;
        int c;

        Node(int v, int c) {
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }
}
