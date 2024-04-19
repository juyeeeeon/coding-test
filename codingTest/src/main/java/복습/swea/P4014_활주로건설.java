package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P4014_활주로건설 {
    static int T, N, X, answer;
    static boolean[] visited;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            answer = 0;
            for (int r = 0; r < N; r++) {
                visited = new boolean[N];
                garo(r, 0);
            }

//            System.out.println("garo = " + answer);

            for (int c = 0; c < N; c++) {
                visited = new boolean[N];
                sero(0, c);
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static void sero(int r, int c) {
        if (r == N - 1) {
//            System.out.println("세로 = " + c);
            answer++;
            return;
        }

        if (r + 1 < N && map[r][c] == map[r+1][c]) {
            sero(r+1, c);
        }

        if (r + 1 < N && map[r][c] - 1 == map[r+1][c] && canBuildSero(r+1, c)) {
            sero(r+1, c);
        }

        if (r + 1 < N && map[r][c] + 1 == map[r+1][c] && canBuildSero(r-X+1, c)) {
            sero(r+1, c);
        }

        return;
    }

    private static void garo(int r, int c) {
        if (c == N - 1) {
//            System.out.println("가로 = " + r);
            answer++;
            return;
        }

        if (c + 1 < N && map[r][c] == map[r][c + 1]) {
            garo(r, c + 1);
        }

        if (c + 1 < N && map[r][c] - 1 == map[r][c + 1] && canBuildGaro(r, c + 1)) {
            garo(r, c + 1);
        }

        if (c + 1 < N && map[r][c] + 1 == map[r][c + 1] && canBuildGaro(r, c - X + 1)) {
            garo(r, c + 1);
        }

        return;
    }

    private static boolean canBuildSero(int r, int c) {
        if (!isValid(r, c) || visited[r]) return false; //!!!!!!! 방문되었는지도 확인!
        int tmp = map[r][c];
        for (int i = r+1; i < r + X; i++) {
            if (!isValid(i, c) || visited[i] || tmp != map[i][c]) return false;
        }

        for (int i = r; i < r + X; i++) {
            visited[i] = true;
        }
        return true;
    }

    private static boolean canBuildGaro(int r, int c) {
        if (!isValid(r, c) || visited[c]) return false; //!!!!!!! 방문되었는지도 확인!
        int tmp = map[r][c];
        for (int i = c+1; i < c + X; i++) {
            if (!isValid(r, i) || visited[i] || tmp != map[r][i]) return false;
        }

        for (int i = c; i < c + X; i++) {
            visited[i] = true;
        }
        return true;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
