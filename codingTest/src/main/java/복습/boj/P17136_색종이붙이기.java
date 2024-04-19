package 복습.boj;

import java.io.*;
import java.util.StringTokenizer;

public class P17136_색종이붙이기 {
    static int answer;
    static int[] remain = {0, 5, 5, 5, 5, 5};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        map = new int[10][10];
        for (int r = 0; r < 10; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 10; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        dfs(0, 0, 0);

        if (answer == Integer.MAX_VALUE) bw.write(Integer.toString(-1));
        else bw.write(Integer.toString(answer));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int r, int c, int cnt) {
        //가지치기(백트래킹)
        if (answer <= cnt) return;

        if (r == 10) {
            answer = Math.min(answer, cnt);
            return;
        }

        if (map[r][c] == 1) {
            for (int k = 5; k >= 1; k--) {
                if (remain[k] > 0 && canAttach(r, c, k)) {
                    attachOrDeattach(r, c, k, 0);
                    remain[k]--;

                    if (c + 1 == 10) dfs(r + 1, 0, cnt + 1);
                    else dfs(r, c + 1, cnt + 1);

                    attachOrDeattach(r, c, k, 1);
                    remain[k]++;
                }
            }
        } else {
            if (c + 1 == 10) dfs(r + 1, 0, cnt);
            else dfs(r, c + 1, cnt);
        }
    }

    private static boolean canAttach(int sr, int sc, int k) {
        for (int r = sr; r < sr + k; r++) {
            for (int c = sc; c < sc + k; c++) {
                if (!isValid(r, c) || map[r][c] == 0) return false;
            }
        }
        return true;
    }

    private static void attachOrDeattach(int sr, int sc, int k, int i) {
        for (int r = sr; r < sr + k; r++) {
            for (int c = sc; c < sc + k; c++) {
                map[r][c] = i;
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < 10 && c < 10;
    }
}
