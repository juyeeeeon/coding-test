package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P5656_벽돌깨기 {
    static int T, N, W, H, minBricks;
    static int[] drop;
    static int[][] map, origin;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new int[H][W];
            origin = new int[H][W];
            for (int r = 0; r < H; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < W; c++) {
                    origin[r][c] = Integer.parseInt(st.nextToken());

                }
            }

            minBricks = Integer.MAX_VALUE;
            drop = new int[N];

            //떨어뜨릴 구슬 중복순열 구하기
            dfs(0);

            System.out.println("#" + test_case + " " + minBricks);
        }
    }

    /**
     * 떨어뜨릴 구슬 중복순열
     * */
    private static void dfs(int idx) {
        if (idx == N) {
            //map 초기화
            initMap();

            for (int c : drop) {
                int r = 0;
                while (r < H && map[r][c] == 0) {
                    r++;
                }

                if (r >= H) continue;

                bfs(r, c, map[r][c]); //구슬로 벽돌 깨뜨리기

                setMap(); //아래로 모음
            }

            minBricks = Math.min(minBricks, getBricks()); //남아있는 벽돌의 최소값 구하기

            return;
        }


        for (int c = 0; c < W; c++) {
            drop[idx] = c;
            dfs(idx + 1);
        }
    }

    /**
     * 벽돌의 갯수
     */
    private static int getBricks() {
        int sum = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] != 0) {
                    sum++;
                }
            }
        }
        return sum;
    }

    /**
     * map 초기화
     */
    private static void initMap() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                map[i][j] = origin[i][j];
            }
        }
    }

    /**
     * 벽돌 아래로 모으기
     */
    private static void setMap() {
        for (int i = H-1; i > 0; i--) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 0) {
                    int r = i;

                    int idx = i-1;
                    while (idx >= 0 && map[idx][j] == 0) {
                        idx--;
                    }
                    if (idx<0) continue;
                    map[r][j] = map[idx][j];
                    map[idx][j] = 0;
                }
            }
        }
    }

    /**
     * 구슬로 벽돌 깨뜨리기
     */
    private static void bfs(int r, int c, int limit) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c, limit});
        map[r][c] = 0; //벽돌이 깨짐

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 1; i < cur[2]; i++) { //벽돌이 깨졌을 때 범위
                for (int d = 0; d < deltas.length; d++) { //상하좌우 탐색
                    int nr = cur[0] + deltas[d][0] * i;
                    int nc = cur[1] + deltas[d][1] * i;

                    if (isValid(nr, nc) && map[nr][nc] != 0) { //범위 안에 있는 벽돌은 다시 상하좌우로 범위만큼 깨짐
                        queue.add(new int[]{nr, nc, map[nr][nc]});
                        map[nr][nc] = 0;
                    }
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < H && c < W;
    }
}
