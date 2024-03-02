package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P16236_아기상어 {
    static int N;
    static int time = 0;
    static int shark_X = -1;
    static int shark_Y = -1;
    static int sharkSize = 2;
    static int eat = 0;
    static PriorityQueue<Fish> fishesDistanceFromShark;
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 9) {
                    shark_X = r;
                    shark_Y = c;
                    map[r][c] = 0;
                }
            }
        }

        while (true) {
            //상어가 먹을 수 있는 물고기를 우선순위큐(가까운순)에 넣음
            fishesDistanceFromShark = new PriorityQueue<>();

            //배열을 탐색하여 먹을 수 있는 물고기와 상어의 거리를 fishesDistanceFromShark에 담음
            bfs();

            if (fishesDistanceFromShark.isEmpty()) break;

            Fish fish = fishesDistanceFromShark.poll(); //타겟 물고기

            //상어 이동
            shark_X = fish.r;
            shark_Y = fish.c;

            //물고기 먹음
            map[fish.r][fish.c] = 0;
            eat++;
            if (eat == sharkSize) {
                sharkSize++;
                eat = 0;
            }

            time += fish.distance;
        }

        System.out.println(time);
    }

    private static void bfs() {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{shark_X, shark_Y, 0});
        visited[shark_X][shark_Y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int t = cur[2];

            for (int d = 0; d < deltas.length; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];

                if (isValid(nr, nc) && !visited[nr][nc]) {
                    if (map[nr][nc] == 0 || map[nr][nc] == sharkSize) { //상어가 지나갈 수 있으면
                        queue.add(new int[]{nr, nc, t+1});
                        visited[nr][nc] = true;
                    } else if (map[nr][nc] < sharkSize) { //물고기의 크기가 상어의 크기보다 작으면
                        fishesDistanceFromShark.add(new Fish(nr, nc, map[nr][nc], t + 1));
                    }
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

    private static class Fish implements Comparable<Fish>{
        int r;
        int c;
        int size;
        int distance;

        public Fish(int r, int c, int size) {
            this.r = r;
            this.c = c;
            this.size = size;
        }

        public Fish(int r, int c, int size, int distance) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.distance = distance;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.distance == o.distance){
                if (this.r == o.r) return this.c - o.c;
                else return this.r - o.r;
            }

            return this.distance - o.distance;
        }
    }
}
