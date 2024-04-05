package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P3055_탈출 {
    static int R, C;
    static int Sr, Sc, answer;
    static ArrayList<int[]> water;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visited;

    static char[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        water = new ArrayList<>();
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = charArray[j];
                if (map[i][j] == '*') water.add(new int[]{i, j});
                if (map[i][j] == 'S') {
                    Sr = i;
                    Sc = j;
                }
            }
        }

        answer = Integer.MAX_VALUE;
        visited = new boolean[R][C];
        bfs();

        if (answer == Integer.MAX_VALUE) System.out.println("KAKTUS");
        else System.out.println(answer);
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{Sr, Sc, 0});
        visited[Sr][Sc] = true;
        map[Sr][Sc] = '.';

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int t = cur[2];

            if (map[r][c] == 'D') {
                answer = t;
                return;
            }

            flood(t + 1);
            for (int d = 0; d < deltas.length; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];

                if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] != 'X' && map[nr][nc] != '*') {
                    queue.add(new int[]{nr, nc, t + 1});
                    visited[nr][nc] = true;
                }
            }
        }
    }

    private static void flood(int time) {

        boolean[][] visit = new boolean[R][C];
        for (int i = 0; i < water.size(); i++) {

            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{water.get(i)[0], water.get(i)[1], 0});
            visit[water.get(i)[0]][water.get(i)[1]] = true;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];
                int t = cur[2];

                if (t == time) {
                    continue;
                }

                for (int d = 0; d < deltas.length; d++) {
                    int nr = r + deltas[d][0];
                    int nc = c + deltas[d][1];

                    if (isValid(nr, nc) && !visit[nr][nc] && map[nr][nc] != 'X' && map[nr][nc] != 'D') {
                        map[nr][nc] = '*';
                        queue.add(new int[]{nr, nc, t + 1});
                        visit[nr][nc] = true;
                    }
                }
            }
        }

    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
