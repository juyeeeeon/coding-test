package swea.D4;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Prim 알고리즘 (정점 중심 MST 구하기)
 * ArrayList[] + Priority Queue 사용 => V개의 정점 구하기
 */
public class P3124_최소스패닝트리_prim {
    static int T;
    static long totalWeight;
    static int V, E; //정점의 개수, 간선의 개수
    static boolean[] visited;
    static ArrayList<Node>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            bw.write("#" + test_case + " ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            visited = new boolean[V + 1];

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
                arr[e].add(new Node(s, w));
            }

            totalWeight = 0;

            bfs();

            bw.write(Long.toString(totalWeight) + '\n');
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        //임의의 정점(정점 1)부터 시작
        queue.add(new Node(1,0));
        int vertexCnt = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (visited[cur.vertex]) continue; // queue 안에 Node 중 같은 정점을 향하지만 출발노드가 달라(= weight가 다름) 방문했는지 걸러줘야됨
            visited[cur.vertex] = true;

            totalWeight += cur.weight;
            vertexCnt++;
            if (vertexCnt == V)  break;

            for (Node next : arr[cur.vertex]) {
//                if (visited[next.vertex]) continue;
                queue.add(next);
            }
        }
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
