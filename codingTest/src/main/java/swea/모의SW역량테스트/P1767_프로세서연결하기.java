package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 미완성
 */
public class P1767_프로세서연결하기 {
    static int T, N;
    static int minLen = Integer.MAX_VALUE;
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
            cores = new ArrayList<>();
            map = new int[N][N];
            visited = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] == 1) {
                        if (!(r == 0 || r == N-1 || c == 0 || c == N-1)) cores.add(new Core(r, c)); //가장자리에 위치한 코어가 아닐 때
                        visited[r][c] = true;
                    }
                }
            }


            generateSubset();

            System.out.println("#" + test_case + " " + minLen);
        }
    }

    /**
     * core을 선택하는 경우의 수(부분집합)
     */
    private static void generateSubset() {
        for (int i = 1; i < (1 << cores.size()); i++) {
            selectedCores = new ArrayList<>();
            for (int j = 0; j < cores.size(); j++) {
                if ((i & (1 << j)) != 0) {
                    selectedCores.add(cores.get(j));
                }
            }
            boolean[][] copyVisited = copyVisited(visited);
            dfs(0, 0, copyVisited);
        }
    }

    private static void dfs(int idx, int len, boolean[][] copyVisited) {
        if (idx == selectedCores.size()) {
            minLen = Math.min(minLen, len);
            return;
        }

        int r = selectedCores.get(idx).r;
        int c = selectedCores.get(idx).c;

        for (int d = 0; d < deltas.length; d++) {
            int nr = r;
            int nc = c;
            int tmp = 0;
            boolean connected = false;

            while (true) {
                nr += deltas[d][0];
                nc += deltas[d][1];

                if (!isValid(nr, nc)) { //범위를 벗어났다 = 연결 가능하다
                    connected = true;
                    break;
                }
                if (copyVisited[nr][nc]) break; //다른 코어나 전선을 만났을 때
                copyVisited[nr][nc] = true;

                tmp++; //전선 길이
            }

            if (connected) dfs(idx + 1, len + tmp, copyVisited(copyVisited));

        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

    private static boolean[][] copyVisited(boolean[][] visited) {
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
