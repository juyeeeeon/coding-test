package swea.D2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1954_달팽이숫자 {
    static int T; //테스트 케이스의 개수
    static int N; //배열의 크기
    static int[][] map;
    static boolean[][] visited; //방문배열
    static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //오른쪽, 아래, 왼쪽, 위
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];

            int r = 0;
            int c = 0;
            int num = 1;
            map[r][c] = num++;
            visited[r][c] = true;

            //2*N-1 만큼 방향이 바뀜
            for (int i = 0; i < 2 * N - 1; i++) {//오른쪽 > 아래쪽 > 왼쪽 > 위쪽 > 오른쪽 > 아래쪽 > ... 반복
                int d = i % 4;
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];

                if (isRange(nr, nc)) {
                    while (d == 0 && nc < N && !visited[nr][nc]) { //오른쪽으로
                        visited[nr][nc] = true;
                        //좌표 업데이트
                        r = nr;
                        c = nc;
                        map[nr][nc++] = num++;
                    }
                    while (d == 1 && nr < N && !visited[nr][nc]) { //아래쪽으로
                        visited[nr][nc] = true;
                        //좌표 업데이트
                        r = nr;
                        c = nc;
                        map[nr++][nc] = num++;
                    }
                    while (d == 2 && nc >= 0 && !visited[nr][nc]) { //왼쪽으로
                        visited[nr][nc] = true;
                        //좌표 업데이트
                        r = nr;
                        c = nc;
                        map[nr][nc--] = num++;
                    }
                    while (d == 3 && nr < N && !visited[nr][nc]) { //위쪽으로
                        visited[nr][nc] = true;
                        //좌표 업데이트
                        r = nr;
                        c = nc;
                        map[nr--][nc] = num++;
                    }
                }
            }

            System.out.println("#" + test_case);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }


        }
    }

    private static boolean isRange(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
