package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class P17136_색종이붙이기 {
    static int[][] map;
    static int[] remain = { 0, 5, 5, 5, 5, 5 };
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        map = new int[10][10];
        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = Integer.MAX_VALUE;

        DFS(0, 0, 0);

        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }

        bw.write(ans + "\n");
        bw.close();
        br.close();
    }

    // DFS + 백트래킹
    public static void DFS(int r, int c, int cnt) {
        // 맨 끝점에 도달하였을 경우, ans와 cnt 비교하고 종료.
        if (r >= 9 && c > 9) {
            ans = Math.min(ans, cnt);
            return;
        }

        // 최솟값을 구해야하는데 ans보다 cnt가 커지는 순간
        // 더 이상 탐색할 필요가 없어짐.
        if (ans <= cnt) {
            return;
        }

        // 아래 줄로 이동.
        if (c > 9) {
            DFS(r + 1, 0, cnt);
            return;
        }

        if (map[r][c] == 1) {
            for (int size = 5; size >= 1; size--) {
                if (remain[size] > 0 && canAttach(r, c, size)) {
                    attach(r, c, size, 0); // 색종이를 붙임.
                    remain[size]--;
                    DFS(r, c + 1, cnt + 1);
                    attach(r, c, size, 1); // 색종이를 다시 뗌.
                    remain[size]++;
                }
            }
        } else { // 오른쪽으로 이동.
            DFS(r, c + 1, cnt);
        }
    }

    // 색종이를 붙이는 함수.
    public static void attach(int r, int c, int size, int state) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                map[i][j] = state;
            }
        }
    }

    // 색종이를 붙일 수 있는지 확인.
    public static boolean canAttach(int r, int c, int size) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (!isValid(i, j)) {
                    return false;
                }

                if (map[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < 10 && j < 10;
    }

}
