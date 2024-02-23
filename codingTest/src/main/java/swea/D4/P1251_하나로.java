package swea.D4;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * kruscal's Algorithm
 * Union-Find + PriorityQueue => (V-1)개의 간선 구하기
 *
 * 섬들이 좌표에 고정되어 있으므로
 * 거리(=tax)가 짧은 순으로 우선순위큐에 넣기 때문에
 * 연결선들이 겹칠 걱정 필요없음
 */
public class P1251_하나로 {
    static int T, N;
    static double E, resultMinTax;
    static int[] islands_x;
    static int[] islands_y;
    static PriorityQueue<Edge> pq;
    static int[] parents;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            N = Integer.parseInt(br.readLine());
            islands_x = new int[N];
            islands_y = new int[N];

            //부모배열초기화(for Union-Find)
            parents = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }

            pq = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                islands_x[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                islands_y[i] = Integer.parseInt(st.nextToken());
            }

            E = Double.parseDouble(br.readLine());

            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    double tax = getTax(islands_x[i], islands_y[i], islands_x[j], islands_y[j]);
                    pq.add(new Edge(i, j, tax)); //우선순위큐에 모든 간선의 경우를 넣음
                }
            }

            resultMinTax = 0;
            int cntEdges = 0;
            while (cntEdges < N - 1) { //간선이 N-1개일 때까지
                Edge cur = pq.poll();

                if (find(cur.v1) == find(cur.v2)) continue; //싸이클 판별

                union(cur.v1, cur.v2);
                cntEdges++;
                resultMinTax += cur.tax;
            }

            bw.write("#" + test_case + " " + Math.round(resultMinTax) + '\n');

        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static double getTax(int x1, int y1, int x2, int y2) {
        return E * (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private static int find(int a) {
        if (parents[a] == a) return a;

        return parents[a] = find(parents[a]);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) parents[rootB] = rootA;
    }

    private static class Edge implements Comparable<Edge>{
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
