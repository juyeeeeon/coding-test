package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1987_알파벳 {
    static int R, C, maxResult;
    static char[][] map;
    static ArrayList<Character> prev;
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        prev = new ArrayList<>();

        maxResult = Integer.MIN_VALUE;
        dfs(0,0,1);

        System.out.println(maxResult);

    }

    private static void dfs(int r, int c, int cnt) {
        if (prev.contains(map[r][c])) return;
        prev.add(map[r][c]);

        maxResult = Math.max(maxResult, cnt);

        for (int d = 0; d < deltas.length; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];

            if (isValid(nr,nc)) dfs(nr, nc, cnt + 1);
        }
        prev.remove(prev.size() - 1);//!!!!!!!!!!
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}

