package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P5656_벽돌깨기 {
    static int T, N, W, H, minBricks;
    static int[] marbleOrder;
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

            origin = new int[H][W];
            map = new int[H][W];
            for (int r = 0; r < H; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < W; c++) {
                    origin[r][c] = map[r][c] = Integer.parseInt(st.nextToken());
                }
            }


            minBricks = Integer.MAX_VALUE;

            //구슬의 중복순열 구하기
            marbleOrder = new int[N];
            marblePerm(0);

            System.out.println("#" + test_case + " " + minBricks);

        }
    }

    /**
     * 구슬 떨어뜨리는 위치 구하기(중복순열 구하기)
     */
    private static void marblePerm(int cnt) {
        if (cnt == N) { //구슬의 중복순열을 모두 구했다면

            originToMap(); //map 리셋

            drop(); //구슬 떨어뜨리기
            minBricks = Math.min(minBricks, countBricks()); //벽돌의 최소 개수 구하기

            return;
        }

        for (int c = 0; c < W; c++) {
            marbleOrder[cnt] = c;
            marblePerm(cnt + 1);
        }
    }

    private static void originToMap() {
        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                map[r][c] = origin[r][c];
            }
        }
    }

    /**
     * 벽돌의 개수 구하기
     */
    private static int countBricks() {
        int cnt = 0;

        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                if (map[r][c] != 0) cnt++;
            }
        }

        return cnt;
    }

    /**
     * 구슬 떨어뜨리기
     */
    private static void drop() {
        for (int c = 0; c < marbleOrder.length; c++) {
            int r = 0;
            while (r < H && map[r][marbleOrder[c]] == 0) {
                r++;
            }

            if (r >= H) continue;

            //벽돌 깨뜨리기
            crash(r, marbleOrder[c]);

            //벽돌을 아래로 모으기
            setMap();
        }


    }

    private static void setMap() {
        for (int c = 0; c < W; c++) {
            Queue<Integer> queue = new ArrayDeque<>();
            for (int r = H - 1; r >= 0; r--) {
                if (map[r][c] == 0) continue;
                queue.add(map[r][c]);
            }

            for (int r = H - 1; r >= 0; r--) {
                if (!queue.isEmpty()){
                    int poll = queue.poll();
                    map[r][c] = poll;
                } else{
                    map[r][c] = 0;
                }
            }
        }
    }

    //벽돌 깨뜨리기
    private static void crash(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c, map[r][c]});
        map[r][c] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 1; i < cur[2]; i++) {
                for (int d = 0; d < deltas.length; d++) {
                    int nr = cur[0] + deltas[d][0] * i;
                    int nc = cur[1] + deltas[d][1] * i;

                    if (isValid(nr, nc) && map[nr][nc] != 0) {
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
