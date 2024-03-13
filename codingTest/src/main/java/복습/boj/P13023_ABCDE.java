package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P13023_ABCDE {
    static int N, M;
    static ArrayList<Integer>[] arr;
    static boolean[] visited;
    static boolean result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            arr[s].add(e);
            arr[e].add(s);
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            result = false;

            dfs(i, 0);

            if (result) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);

    }

    private static void dfs(int i, int depth) {
        if (visited[i]) return;
        visited[i] = true;

        if (depth == 4) {
            result = true;
            return;
        }

        for (int num : arr[i]) {
            dfs(num, depth + 1);
        }

        visited[i] = false;
    }
}

