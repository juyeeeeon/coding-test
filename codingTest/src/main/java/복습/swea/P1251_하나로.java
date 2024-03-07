package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


//Kruscal Algorithm (for MST)
public class P1251_하나로 {
    static int T, N;
    static double E, minTax;
    static int[] islands_X, islands_Y;
    static int[] parents;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());

            islands_X = new int[N];
            islands_Y = new int[N];
            pq = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                islands_X[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                islands_Y[i] = Integer.parseInt(st.nextToken());
            }

            E = Double.parseDouble(br.readLine());

            for (int i = 0; i < N-1; i++) {
                for (int j = i + 1; j < N; j++) {
                    pq.add(new Edge(i, j, getTax(islands_X[i], islands_Y[i], islands_X[j], islands_Y[j])));
                }
            }

            parents = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }

            int cnt = 0;
            minTax = 0;
            while (cnt < N - 1) {
                Edge cur = pq.poll();

                if (find(cur.v1) == find(cur.v2)) continue;
                union(cur.v1, cur.v2);
                minTax += cur.tax;
                cnt++;
            }

            System.out.println("#" + test_case + " " + Math.round(minTax));
        }
    }

    private static void union(int v1, int v2) {
        int rootV1 = find(v1);
        int rootV2 = find(v2);

        if (rootV1 == rootV2) return;

        parents[rootV2] = rootV1;
    }

    private static int find(int v) {
        if (parents[v] == v)  return v;

        return parents[v] = find(parents[v]);
    }

    private static double getTax(int x1, int y1, int x2, int y2){
        return E * (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    static class Edge implements Comparable<Edge>{
        int v1;
        int v2;
        double tax;

        public Edge(int v1, int v2, double tax) {
            this.v1 = v1;
            this.v2 = v2;
            this.tax = tax;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.tax, o.tax);
        }
    }
}

