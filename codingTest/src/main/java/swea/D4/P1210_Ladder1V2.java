package swea.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * visited 배열을 만들고 도착지부터 거꾸로 올라가는게 더 간단함
 */
public class P1210_Ladder1V2 {
    static final int N = 100;
    static final int LADDER = 1;
    static final int EMPTY = 0;
    static final int DESTINATION = 2;
    static int T;
    static boolean[][] visited;
    static int[][] deltas = {{0, -1}, {0, 1}, {-1, 0}}; //왼쪽, 오른쪽, 위
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            br.readLine();

            map = new int[N][N];
            visited = new boolean[N][N];

            int dr = N - 1;
            int dc = -1;

            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < N; col++) {
                    map[row][col] = Integer.parseInt(st.nextToken());
                    if (row == N-1 && map[row][col] == DESTINATION) dc = col;
                }
            }

            while (dr > 0) {
                for (int d = 0; d < deltas.length; d++) { //왼쪽, 오른쪽, 위 탐색
                    int nr = dr + deltas[d][0];
                    int nc = dc + deltas[d][1];

                    if (isRange(nr, nc) && map[nr][nc] == LADDER && !visited[nr][nc]) {
                        //좌표 업데이트
                        dr = nr;
                        dc = nc;
                        visited[dr][dc] = true; //방문처리

                        break;
                    }
                }
            }

            System.out.println("#" + test_case + " " + dc);
        }
    }

    private static boolean isRange(int nr, int nc) {
        return nr >= 0 && nc >= 0 && nr < N && nc < N;
    }
}
