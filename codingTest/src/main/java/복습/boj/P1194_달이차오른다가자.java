package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1194_달이차오른다가자 {
    static int N, M, startR, startC, answer;
    static char[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = charArray[j];
                if (map[i][j] == '0') {
                    startR = i;
                    startC = j;
                }
            }
        }

        visited = new boolean[N][M][64];

        answer = -1;
        bfs();
        System.out.println(answer);
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startR, startC, 0, 0}); //startR startC key move
        map[startR][startC] = '.';
        visited[startR][startC][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (map[cur[0]][cur[1]] == '1') {
                answer = cur[3];
                return;
            }

            for (int d = 0; d < deltas.length; d++) {
                int nr = cur[0] + deltas[d][0];
                int nc = cur[1] + deltas[d][1];

                if (isValid(nr, nc) && map[nr][nc] != '#' && !visited[nr][nc][cur[2]]){
                    if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
                        int key = cur[2] | (1 << map[nr][nc] - 'a');
                        queue.add(new int[]{nr, nc, key, cur[3] + 1});
                        visited[nr][nc][key] = true;
                    }
                    else if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
                        if ((cur[2] & (1 << (map[nr][nc] - 'A'))) != 0) {
                            queue.add(new int[]{nr, nc, cur[2], cur[3] + 1});
                            visited[nr][nc][cur[2]] = true;
                        }
                    }
                    else {
                        queue.add(new int[]{nr, nc, cur[2], cur[3] + 1});
                        visited[nr][nc][cur[2]] = true;
                    }
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
