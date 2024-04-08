package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P21608_상어초등학교 {
    static int N, totalSatisfaction;
    static PriorityQueue<Seat> pq;
    static int[] order;
    static int[][] map, arr;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());

        order = new int[N * N + 1];
        map = new int[N][N];
        arr = new int[N * N + 1][4];

        for (int i = 1; i < N * N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            order[i] = s;
            for (int j = 0; j < 4; j++) {
                arr[s][j] = Integer.parseInt(st.nextToken());
            }
        }

        totalSatisfaction = 0;
        turn(1);

        System.out.println(totalSatisfaction);

    }

    private static void turn(int cnt) {
        if (cnt == N * N + 1) {
            getSatisfaction();
            return;
        }

        getPosition(cnt);
        Seat cur = pq.poll();
        map[cur.r][cur.c] = order[cnt];
        turn(cnt + 1);
    }

    private static void getPosition(int cnt) {
        pq = new PriorityQueue<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 0) {
                    int likes = 0;
                    int empty = 0;
                    for (int d = 0; d < deltas.length; d++) {
                        int nr = r + deltas[d][0];
                        int nc = c + deltas[d][1];

                        if (isValid(nr, nc)) {
                            if (map[nr][nc] == 0) empty++;
                            else if (likes(cnt, map[nr][nc])) likes++;
                        }
                    }
                    pq.add(new Seat(r, c, likes, empty));
                }
            }
        }
    }

    private static boolean likes(int cnt, int j) {
        for (int num : arr[order[cnt]]) {
            if (num == j) return true;
        }
        return false;
    }

    private static void getSatisfaction() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {

                int likes = 0;
                for (int d = 0; d < deltas.length; d++) {
                    int nr = r + deltas[d][0];
                    int nc = c + deltas[d][1];

                    if (isValid(nr, nc)) {
                        for (int near : arr[map[r][c]]) {
                            if (near == map[nr][nc]) likes++;
                        }
                    }
                }

                if (likes > 0) totalSatisfaction += (int)Math.pow(10, likes-1);

            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

    private static class Seat implements Comparable<Seat>{
        int r;
        int c;
        int likes;
        int empty;

        public Seat() {
        }

        public Seat(int r, int c, int likes, int empty) {
            this.r = r;
            this.c = c;
            this.likes = likes;
            this.empty = empty;
        }

        @Override
        public int compareTo(Seat o) {
            if (this.likes != o.likes) {
                return o.likes - this.likes; //좋아하는 학생이 인접한 칸에 몇 명인지
            }

            if (this.empty != o.empty) {
                return o.empty - this.empty; //인접한 칸에 비어있는 칸이 몇 개인지
            }

            if (this.r != o.r) {
                return this.r - o.r; //행의 번호가 가장 작은 칸
            }

            return this.c - o.c; //열의 번호가 가장 작은 칸
        }
    }

}
