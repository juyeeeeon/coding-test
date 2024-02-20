package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2636_치즈 {
    static int R, C;
    static int time = 0;
    static int pieceOfCheese = 0;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상 하 좌 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        while (!allZero()) { //치즈가 존재할 때까지(= map이 모두 0이 아닐 때까지)
            time++;
            pieceOfCheese = count1(); //치즈조각의 개수(= 1의 개수)
            visited = new boolean[R][C]; //방문배열 초기화
            BFS(0, 0);

/*            System.out.println("=====" + time + "====");
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }*/
        }

        System.out.println(time);
        System.out.println(pieceOfCheese);
    }

    private static void BFS(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < deltas.length; d++) { //상하좌우 탐색
                int nr = cur[0] + deltas[d][0];
                int nc = cur[1] + deltas[d][1];

                if (isValid(nr, nc) && !visited[nr][nc]) {
                    if (map[nr][nc] == 0) { //치즈가 존재하지 않은 영역
                        queue.add(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    } else { //치즈가 존재하는 영역
                        visited[nr][nc] = true; //방문처리해서 다시 방문하지 않게
                        map[nr][nc] = 0; //치즈가 녹음
                    }
                }
            }
        }
    }

    /**
     *
     * @return 치즈조각의 개수(= 1의 개수)
     */
    private static int count1() {
        int cnt = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 1) cnt++;
            }
        }
        return cnt;
    }

    /**
     *
     * @return 치즈가 다 녹았는지(= map이 모두 0인지)
     */
    private static boolean allZero() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 1) return false;
            }
        }
        return true;
    }

    private static boolean isValid(int nr, int nc) {
        return nr >= 0 && nc >= 0 && nr < R && nc < C;
    }
}
