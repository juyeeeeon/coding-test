package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1767_프로세서연결하기 {
    static int T, N;
    static int minResult = Integer.MAX_VALUE;
    static int maxConnectedCores = Integer.MIN_VALUE;
    static int[][] map;
    static ArrayList<Core> selectedCores;
    static ArrayList<Core> cores;
    static boolean[][] visited;
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] == 1) {
                        if ((r != 1 && r != N && c != 1 && c != N)) cores.add(new Core(r, c));
                        visited[r][c] = true;
                    }
                }
            }


            comb();
        }
    }

    private static void comb() {
        for (int i = 1; i < (1 << cores.size()); i++) {
            selectedCores = new ArrayList<>();
            for (int j = 0; j < cores.size(); j++) {
                if ((i & (1 << j)) != 0) {
                    selectedCores.add(cores.get(j));
                }
            }

            boolean[][] copyVisited = copyVisited();
            dfs(0, 0);
        }
    }

    private static void dfs(int idx, int cnt) {
        if (idx == selectedCores.size()) {
            if (maxConnectedCores)
        }
    }

    private static boolean[][] copyVisited() {
        boolean[][] copyVisited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copyVisited[i][j] = visited[i][j];
            }
        }
        return copyVisited;
    }

    private static class Core {

        int r;
        int c;

        public Core(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
