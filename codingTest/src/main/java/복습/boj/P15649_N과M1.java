package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15649_N과M1 {
    static int N, M;
    static int[] buffer;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        buffer = new int[M];
        visited = new boolean[N + 1];
        perm(0);

    }

    private static void perm(int idx) {
        if (idx == M) {
            for (int i : buffer) {
                System.out.print(i + " ");
            }
            System.out.println();

            return;
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            buffer[idx] = i;
            perm(idx + 1);
            visited[i] = false;
        }
    }
}
