package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1949_등산로조성 {
    static int T, N, K;
    static int maxLen;
    static int[][] map, copyMap;
    static boolean[][] visited;
    static ArrayList<int[]> maxTop;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            int maxHeight = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxHeight = Math.max(maxHeight, map[i][j]); //최대 높이 구하기
                }
            }

            maxTop = new ArrayList<>(); //최대 높이의 좌표를 담는 배열
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == maxHeight) maxTop.add(new int[]{i, j});
                }
            }

            //테스트케이스마다 초기화
            maxLen = Integer.MIN_VALUE;

            for (int[] top : maxTop) { //모든 최대 높이 좌표에 대해서
                int r = top[0];
                int c = top[1];

                //좌표마다 초기화
                copyMap = mapToCopyMap();
                visited = new boolean[N][N];
                dfs(r, c, false, 1);
            }

            System.out.println("#" + test_case + " " + maxLen);

        }
    }

    /**
     *
     * @param r 행
     * @param c 열
     * @param kUsed 산을 깎은 여부
     * @param len 등산로 길이
     */
    private static void dfs(int r, int c, boolean kUsed, int len) {
        maxLen = Math.max(maxLen, len); //매번 등산로 최대 길이 업데이트

        if (visited[r][c]) return;
        visited[r][c] = true;

        //사방탐색
        for (int d = 0; d < deltas.length; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];

            if (isValid(nr,nc) && !visited[nr][nc])
                if (copyMap[nr][nc] < copyMap[r][c]) { //내리막길이면
                    dfs(nr, nc, kUsed, len + 1);
                } else if (!kUsed && copyMap[nr][nc] - K < copyMap[r][c]) { //오르막길 && 산을 깎지 않았었고 && 깎으면 내리막길이면
                    for (int k = 1; k <= K; k++) {
                        if (copyMap[nr][nc] - k < copyMap[r][c]) {
                            copyMap[nr][nc] -= k;
                            dfs(nr, nc, true, len + 1);
                            copyMap[nr][nc] += k; //원복
                        }
                    }
                }
        }

        visited[r][c] = false; //원복
    }

    private static int[][] mapToCopyMap() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[i][j] = map[i][j];
            }
        }

        return tmp;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

}
