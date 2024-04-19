package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P7576_토마토 {
    static int M, N, answer;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        Queue<int[]> queue = new ArrayDeque<>();

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) queue.add(new int[]{i, j, 0}); //익은 토마토 담기
            }
        }

        //익은 토마토가 없을 때 = 토마토가 익지 못하는 상황이면 -1 출력
        if (queue.isEmpty()) {
            System.out.println(-1);
            return;
        }

        //모든 토마토가 익었을 때
        if (queue.size() == M * N) {
            System.out.println(0);
            return;
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int day = cur[2];

            answer = day;

            for (int d = 0; d < deltas.length; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];

                if (isValid(nr, nc) && map[nr][nc] == 0) {
                    map[nr][nc] = 1;
                    queue.add(new int[]{nr, nc, day + 1});
                }
            }
        }

        //토마토가 모두 익지 못하는 상황일 때
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
