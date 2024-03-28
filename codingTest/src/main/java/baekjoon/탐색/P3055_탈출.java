package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P3055_탈출 {
    static int R, C, Drow, Dcol, Srow, Scol, minTime;
    static ArrayList<int[]> water;
    static char[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        water = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = charArray[j];
                if (map[i][j] == 'D') {
                    Drow = i;
                    Dcol = j;
                }
                if (map[i][j] == 'S') {
                    Srow = i;
                    Scol = j;
                }
                if (map[i][j] == '*') {
                    water.add(new int[]{i, j});
                }
            }
        }

        minTime = Integer.MAX_VALUE;
        bfs();

        if (minTime == Integer.MAX_VALUE) System.out.println("KAKTUS");
        else System.out.println(minTime);

    }

    private static void bfs() {
        boolean[][] visited = new boolean[R][C];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{Srow, Scol, 0});
        visited[Srow][Scol] = true;


        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == Drow && cur[1] == Dcol) {
                minTime = cur[2];
                return;
            }

            fill(cur[2]+1, '*');
            for (int d = 0; d < deltas.length; d++) {
                int nr = cur[0] + deltas[d][0];
                int nc = cur[1] + deltas[d][1];

                if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] != 'X' && map[nr][nc] != '*') {
                    queue.add(new int[]{nr, nc, cur[2] + 1});
                    visited[nr][nc] = true;
                }
            }
//            fill(cur[2] + 1, '.');
        }
    }

    private static void fill(int i, char c) {
        for (int[] cur : water) {

            boolean[][] visited = new boolean[R][C];

            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{cur[0], cur[1], 0});
            visited[cur[0]][cur[1]] = true;

            while (!queue.isEmpty()) {
                int[] poll = queue.poll();

                map[poll[0]][poll[1]] = c;
                if (poll[2] == i) continue;

                for (int d = 0; d < deltas.length; d++) {
                    int nr = poll[0] + deltas[d][0];
                    int nc = poll[1] + deltas[d][1];

                    if (isValid(nr, nc) && map[nr][nc] != 'D' && map[nr][nc] != 'X' && !visited[nr][nc]) {
                        queue.add(new int[]{nr, nc, poll[2] + 1});
                        visited[nr][nc] = true;
                    }
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
