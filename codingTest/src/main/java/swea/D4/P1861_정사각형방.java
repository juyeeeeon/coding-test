package swea.D4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1861_정사각형방 {
    static int T;
    static int N;
    static int maxMove; //최대 이동 가능 횟수
    static int minRoom; //최대 이동 가능 횟수의 가장 작은수의 방의 숫자
    static int[][] map;
    static boolean[][] visited; //방문배열
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상, 하, 좌, 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];


            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            maxMove = 0;
            minRoom = Integer.MAX_VALUE;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    DFS(r, c, 1, map[r][c]);
                }
            }

            bw.write("#" + test_case + " " + minRoom + " " + maxMove + '\n');
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void DFS(int r, int c, int cnt, int roomNo) {
        if (visited[r][c]) return;

        visited[r][c] = true;

        for (int d = 0; d < deltas.length; d++) { //상, 하, 좌, 우 이동
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];

            if (nr >= 0 && nc >= 0 && nr < N && nc < N
                    && !visited[nr][nc] && map[nr][nc] - map[r][c] == 1) { //이동하려는 방에 적힌 숫자가 현재 방에 적힌 숫자보다 정확히 1 더 커야 한다

                DFS(nr, nc, cnt + 1, roomNo);
                if (maxMove < cnt + 1) { //이동 횟수가 더 크다면
                    maxMove = cnt + 1; //최대 이동 횟수 업데이트
                    minRoom = roomNo; //최소 방 숫자 업데이트
                } else if (maxMove == cnt + 1) { //이동 횟수가 같다면
                    if (minRoom > roomNo) { //방 숫자가 더 작다면
                        minRoom = roomNo; //최소 방 숫자 업데이트
                    }
                }

            }
        }

        visited[r][c] = false;
    }
}
