package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14503_로봇청소기 {
    static int N, M, robotR, robotC, dir, answer;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        st = new StringTokenizer(br.readLine());

        robotR = Integer.parseInt(st.nextToken());
        robotC = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        operate();

        System.out.println(answer);
    }

    private static void operate() {
        if (map[robotR][robotC] == 0) {
            map[robotR][robotC] = 2;
            answer++;
        }

        if (emptyExists()) {
            turnCounterClockWise();

            int nr = robotR + deltas[dir][0];
            int nc = robotC + deltas[dir][1];
            if (isValid(nr, nc) && map[nr][nc] == 0) {
                robotR = nr;
                robotC = nc;
            }
            operate();
        } else {
            int nr = robotR - deltas[dir][0];
            int nc = robotC - deltas[dir][1];

            if (isValid(nr, nc) && map[nr][nc] != 1) {
                robotR = nr;
                robotC = nc;
                operate();
            } else if (isValid(nr, nc) && map[nr][nc] == 1){
                return;
            }
        }

    }

    private static boolean emptyExists() {
        for (int d = 0; d < deltas.length; d++) {
            int nr = robotR + deltas[d][0];
            int nc = robotC + deltas[d][1];

            if (isValid(nr, nc) && map[nr][nc] == 0) {
                return true;
            }
        }

        return false;
    }

    private static void turnCounterClockWise() {
        dir--;
        if (dir == -1) {
            dir = 3;
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

}
