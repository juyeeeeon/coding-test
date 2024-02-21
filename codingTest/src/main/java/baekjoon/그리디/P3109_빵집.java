package baekjoon.그리디;

import java.io.*;
import java.util.StringTokenizer;

public class P3109_빵집 {
    static int R, C;
    static int result = 0;
    static int[][] deltas = {{-1, 1}, {0, 1}, {1, 1}}; //오른쪽위, 오른쪽, 오른쪽아래 (순서중요)
    static char[][] map;
    static boolean finished; //파이프가 이어졌는지 확인
    static boolean[][] visited; //방문배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int r = 0; r < R; r++) {
            String line = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = line.charAt(c);
            }
        }

        for (int r = 0; r < R; r++) {
            finished = false;
            DFS(r, 0);
        }

        bw.write(Integer.toString(result));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void DFS(int r, int c) {
        if (c == C - 1) { //마지막 열에 도착
            finished = true; //파이프가 이어짐
            result++;
            return;
        }

        visited[r][c] = true;

        for (int d = 0; d < deltas.length; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];

            if (isValid(nr, nc) && map[nr][nc] == '.' && !visited[nr][nc]) {
                DFS(nr, nc);
                if (finished) return;
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
