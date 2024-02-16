package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1987_알파벳 {
    static int R, C;
    static int result = Integer.MIN_VALUE;
    static ArrayList<Character> prev; //지나왔던 알파벳 저장 배열
    static char[][] map;
    static int[][] deltas = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}}; //오, 아, 위, 왼

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        prev = new ArrayList<>();
        map = new char[R][C];

        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }

        DFS(0, 0, 1);

        System.out.println(result);
    }

    /**
     *
     * @param r : 행
     * @param c : 열
     * @param cnt : 현재까지 지나온 칸 수
     */
    private static void DFS(int r, int c, int cnt) {
        //매번 result을 최댓값으로 업데이트
        result = Math.max(result, cnt);

        //지나온 알파벳 저장
        prev.add(map[r][c]);

        //네방향 탐색
        for (int d = 0; d < deltas.length; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];

            if (isValid(nr, nc) && !prev.contains(map[nr][nc])) {
                DFS(nr, nc, cnt + 1);
            }
        }

        //원상복구
        prev.remove(prev.size() - 1);
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < R && col < C;
    }
}
