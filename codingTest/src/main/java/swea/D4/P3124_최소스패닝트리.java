package swea.D4;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P3124_최소스패닝트리 {
    static int T;
    static int V, E; //정점의 개수, 간선의 개수
    static int[] parents;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            bw.write("#" + test_case + " ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            //부모배열 초기화
            parents = new int[V + 1];
            for (int i = 1; i < V + 1; i++) {
                parents[i] = i;
            }

            pq = new PriorityQueue<>();

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                pq.add(new Edge(v1, v2, w));
            }

            int usedEdges = 0;
            long totalWeight = 0; //long타입
            while (usedEdges < V - 1) {
                Edge edge = pq.poll();
                int v1 = edge.v1;
                int v2 = edge.v2;
                int weight = edge.weight;

                if (find(v1) != find(v2)) { //싸이클 여부 판별
                    union(v1, v2);
                    usedEdges++;
                    totalWeight += weight;
                }
            }

            bw.write(Long.toString(totalWeight) + '\n');
        }

        bw.flush();
        bw.close();
        br.close();
    }

    //=== union-find 알고리즘 ===
    static int find(int a) {
        if (parents[a] == a) return a;

        return parents[a] = find(parents[a]);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) parents[rootB] = rootA;
    }

    //=========================

    static class Edge implements Comparable<Edge> {
        int v1;
        int v2;
        int weight;

        public Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
