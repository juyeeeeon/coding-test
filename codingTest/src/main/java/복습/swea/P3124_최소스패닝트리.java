package 복습.swea;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P3124_최소스패닝트리 {
    static int T, V, E;
    static long result;
    static int[] parents;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            parents = new int[V + 1];
            for (int i = 1; i < V + 1; i++) {
                parents[i] = i;
            }

            pq = new PriorityQueue<>();
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                pq.add(new Edge(s, e, w));
            }


            int cnt = 0;
            result = 0;
            while (cnt < V-1) {
                Edge edge = pq.poll();

                if (find(edge.v1) != find(edge.v2)) {
                    result += edge.w;
                    union(edge.v1, edge.v2);
                    cnt++;
                }
            }

            bw.write("#" + test_case + " " + result + "\n");
//            System.out.println("#" + test_case + " " + result);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int find(int v) {
        if (parents[v] == v) return v;

        return parents[v] = find(parents[v]);
    }

    private static void union(int v1, int v2) {
        int rootV1 = find(v1);
        int rootV2 = find(v2);

        if (rootV1 != rootV2) parents[rootV2] = rootV1;
    }

    private static class Edge implements Comparable<Edge>{
        int v1;
        int v2;
        int w;

        public Edge(int v1, int v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}
