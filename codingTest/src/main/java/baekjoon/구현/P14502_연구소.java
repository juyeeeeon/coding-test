package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P14502_연구소 {
    static int N, M, map[][];
    static int maxSafeNum = Integer.MIN_VALUE;
    static ArrayList<int[]> blanks, viruses;
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        blanks = new ArrayList<>(); //0의 좌표 담기
        viruses = new ArrayList<>(); //2의 좌표 담기

        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 0) blanks.add(new int[]{r, c});
                if (map[r][c] == 2) viruses.add(new int[]{r, c});
            }
        }

        dfs(0, 0);

        System.out.println(maxSafeNum);
    }

    /**
     * map에서 0인 곳에 벽(1)을 3개 세우는 모든 경우의 수에 따라 safezone의 갯수의 최댓값 구하기
     *
     * @param idx blanks의 인덱스
     * @param constructWallNum 벽을 세운 갯수
     */
    private static void dfs(int idx, int constructWallNum) {

        if (constructWallNum == 3) { //벽을 3개 세웠다면
            maxSafeNum = Math.max(maxSafeNum, getSafeNum()); //safezone의 최댓값 구하기
            return;
        }

        if (idx >= blanks.size()) return;

        int[] blank = blanks.get(idx);
        int r = blank[0];
        int c = blank[1];

        map[r][c] = 1;
        dfs(idx + 1, constructWallNum + 1); //벽을 세우는 경우
        map[r][c] = 0;
        dfs(idx + 1, constructWallNum); //벽을 세우지 않는 경우
    }

    /**
     *
     * @return safezone(=0)의 개수
     */
    private static int getSafeNum() {
        int[][] virusSpreadMap = virusSpread();

        int safeNum = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (virusSpreadMap[r][c] == 0) safeNum++;
            }
        }

        return safeNum;

    }

    /**
     *
     * @return 바이러스 분포 후의 map 상태
     */
    private static int[][] virusSpread() {
        int[][] copyMap = copyMap();
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new ArrayDeque<>();

        //모든 바이러스에 대해
        for (int[] virus : viruses) {
            queue.add(virus);
            visited[virus[0]][virus[1]] = true;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];

                for (int d = 0; d < deltas.length; d++) { //사방탐색
                    int nr = r + deltas[d][0];
                    int nc = c + deltas[d][1];

                    if (isValid(nr, nc) && !visited[nr][nc] && copyMap[nr][nc] == 0) {
                        queue.add(new int[]{nr, nc});
                        visited[nr][nc] = true;
                        copyMap[nr][nc] = 2; //바이러스가 퍼짐
                    }
                }

            }
        }

        return copyMap;
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

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
