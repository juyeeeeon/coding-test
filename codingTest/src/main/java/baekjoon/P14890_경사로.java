package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14890_경사로 {
    static int N, L, answer;
    static boolean[][] visited;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        garo();
        sero();

        System.out.println(answer);
    }

    private static void garo() {
        for (int i = 0; i < N; i++) {
            visited = new boolean[N][N];
            boolean ok = true;

            for (int j = 0; j < N - 1; j++) {
                if (map[i][j] == map[i][j + 1]) continue;

                if (map[i][j] + 1 == map[i][j + 1]) { //오르막
                    if (canLeft(i, j)) {
                        for (int k = j; k > j - L; k--) {
                            visited[i][k] = true;
                        }
                    } else {
                        ok = false;
                        break;
                    }
                } else if (map[i][j] - 1 == map[i][j + 1]) { //내리막
                    if (countRight(i, j + 1)) {
                        for (int k = j + 1; k < j + L + 1; k++) {
                            visited[i][k] = true;
                        }
                    } else {
                        ok = false;
                        break;
                    }
                } else {
                    ok = false;
                    break;
                }
            }

            if (ok) answer++;
        }
    }

    private static void sero() {
        for (int j = 0; j < N; j++) {
            visited = new boolean[N][N];
            boolean ok = true;
            for (int i = 0; i < N - 1; i++) {
                if (map[i][j] == map[i + 1][j]) continue;

                if (map[i][j] + 1 == map[i + 1][j]) { //오르막
                    if (canUp(i, j)) {
                        for (int k = i; k > i - L; k--) {
                            visited[k][j] = true;
                        }
                    } else {
                        ok = false;
                        break;
                    }
                } else if (map[i][j] - 1 == map[i + 1][j]) { //내리막

                    if (canDown(i + 1, j)) {
                        for (int k = i + 1; k < i + L + 1; k++) {
                            visited[k][j] = true;
                        }
                    } else {
                        ok = false;
                        break;
                    }
                } else {
                    ok = false;
                    break;
                }
            }

            if (ok) answer++;
        }
    }

    private static boolean canDown(int i, int j) {
        int cnt = 0;
        int num = map[i][j];
        int ptr = i;
        while (true) {
            if (isValid(ptr, j) && num == map[ptr][j] && !visited[ptr][j]) {
                cnt++;
                ptr++;
            } else {
                break;
            }

            if (cnt == L) return true;
        }
        return false;
    }

    private static boolean canUp(int i, int j) {
        int cnt = 0;
        int num = map[i][j];
        int ptr = i;
        while (true) {
            if (isValid(ptr, j) && num == map[ptr][j] && !visited[ptr][j]) {
                cnt++;
                ptr--;
            } else {
                break;
            }

            if (cnt == L) return true;
        }
        return false;
    }

    private static boolean countRight(int i, int j) {
        int cnt = 0;
        int num = map[i][j];
        int ptr = j;
        while (true) {
            if (isValid(i, ptr) && num == map[i][ptr] && !visited[i][ptr]) {
                cnt++;
                ptr++;
            } else {
                break;
            }

            if (cnt == L) return true;
        }

        return false;
    }

    private static boolean canLeft(int i, int j) {
        int cnt = 0;
        int num = map[i][j];
        int ptr = j;
        while (true) {
            if (isValid(i, ptr) && num == map[i][ptr] && !visited[i][ptr]) {
                cnt++;
                ptr--;
            } else {
                break;
            }

            if (cnt == L) return true;
        }

        return false;
    }

    private static boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < N && j < N;
    }

}
