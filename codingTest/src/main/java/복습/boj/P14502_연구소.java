package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P14502_연구소 {
    static int N, M;
    static int maxSafeZone;
    static ArrayList<int[]> blanks;
    static ArrayList<int[]> viruses;
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        blanks = new ArrayList<>();
        viruses = new ArrayList<>();

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) blanks.add(new int[]{i, j});
                if (map[i][j] == 2) viruses.add(new int[]{i, j});
            }
        }

        maxSafeZone = Integer.MIN_VALUE;
        constructWall(0, 0);

        System.out.println(maxSafeZone);
    }

    /**
     * 세개의 벽을 세우는 모든 경우
     *
     * @param idx blanks의 인덱스
     * @param wallCnt 세운 벽의 개수
     */
    private static void constructWall(int idx, int wallCnt) {
        if (wallCnt == 3) { //벽을 3개 세웠다면
            maxSafeZone = Math.max(maxSafeZone, getSafeZoneNum());
            return;
        }

        if (idx >= blanks.size()) return; //idx가 0의 갯수보다 클 경우

        int r = blanks.get(idx)[0];
        int c = blanks.get(idx)[1];

        map[r][c] = 1;
        constructWall(idx + 1, wallCnt + 1); //해당 영역에 벽을 세우는 경우
        map[r][c] = 0;
        constructWall(idx + 1, wallCnt); //해당 영역에 벽을 세우지 않는 경우
    }

    private static int getSafeZoneNum() {
        int[][] spreadMap = spreadVirus();

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (spreadMap[i][j] == 0) cnt++;
            }
        }

        return cnt;
    }

    private static int[][] spreadVirus() {
        int[][] spreadMap = copyMap();
        boolean[][] visited = new boolean[N][M];

        for (int[] virus : viruses) {
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(virus);
            visited[virus[0]][virus[1]] = true;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];

                for (int d = 0; d < deltas.length; d++) {
                    int nr = r + deltas[d][0];
                    int nc = c + deltas[d][1];

                    if (isValid(nr,nc) && !visited[nr][nc] && spreadMap[nr][nc] == 0){
                        spreadMap[nr][nc] = 2;
                        queue.add(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
        }

        return spreadMap;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

    private static int[][] copyMap() {
        int[][] copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        return copyMap;

    }
}
