package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1194_달이차오른다가자 {
    static int N, M, minMove;
    static int[] startPosition;
    static char[][] map;
    static boolean[][][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        startPosition = new int[2];
        map = new char[N][M];

        for (int r = 0; r < N; r++) {
            char[] charArray = br.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                map[r][c] = charArray[c];
                if (map[r][c] == '0') {
                    startPosition[0] = r;
                    startPosition[1] = c;
                    map[r][c] = '.';
                }
            }
        }

        minMove = Integer.MAX_VALUE;
        visited = new boolean[N][M][64]; //a~f : 2^6=64
        bfs();

        if (minMove == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minMove);

    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startPosition[0], startPosition[1], 0, 0}); //[r, c, move, key]
        visited[startPosition[0]][startPosition[1]][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int move = cur[2];
            int key = cur[3];

            for (int d = 0; d < deltas.length; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];

                if (isValid(nr, nc) && map[nr][nc] != '#' && !visited[nr][nc][key]) {
                    if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
                        int newKey = key | (1 << (map[nr][nc] - 'a'));
                        queue.add(new int[]{nr, nc, move + 1, newKey});
                        visited[nr][nc][newKey] = true;

                    } else if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F' && (key & (1<<(map[nr][nc] - 'A'))) != 0 ) {
                        queue.add(new int[]{nr, nc, move + 1, key});
                        visited[nr][nc][key] = true;

                    } else if(map[nr][nc] == '.'){
                        queue.add(new int[]{nr, nc, move + 1, key});
                        visited[nr][nc][key] = true;
                    }
                    else if (map[nr][nc] == '1') {
                        minMove = move + 1;
                        return;
                    }
                }
            }

        }

    }


    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
