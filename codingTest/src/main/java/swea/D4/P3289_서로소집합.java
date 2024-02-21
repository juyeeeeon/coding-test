package swea.D4;

import java.io.*;
import java.util.StringTokenizer;

public class P3289_서로소집합 {
    static int T, N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            bw.write("#" + test_case + " ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            parent = new int[N + 1];
            //parent 배열 초기화
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int check = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (check == 0) {
                    union(a, b);
                }
                if (check == 1) {
                    if (find(a) == find(b)) bw.write(Integer.toString(1));
                    else bw.write(Integer.toString(0));
                }
            }

            bw.write('\n');
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int find(int a) {
        if (parent[a] == a) return a; //a가 대표노드면

        return parent[a] = find(parent[a]); //부모노드 업데이트
    }

    private static void union(int a, int b) {
        a = find(a); //a의 대표노드
        b = find(b); //b의 대표노드

        if (a != b) parent[b] = a; //부모노드 업데이트
    }
}
