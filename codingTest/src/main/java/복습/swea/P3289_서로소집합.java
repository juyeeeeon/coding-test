package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P3289_서로소집합 {

    static int T, n, m;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            //부모배열 초기화
            parents = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                parents[i] = i;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int inst = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                //합집합
                if (inst == 0) {
                    if (find(a) != find(b)) union(a, b);
                } else {
                    if (find(a) == find(b)) sb.append(1);
                    else sb.append(0);
                }
            }

            System.out.println("#" + test_case + " " + sb.toString());
        }

    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) parents[rootB] = rootA;
    }

    private static int find(int a) {
        if (parents[a] == a) return a;

        return parents[a] = find(parents[a]);
    }
}
