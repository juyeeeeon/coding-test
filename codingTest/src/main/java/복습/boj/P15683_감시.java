package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P15683_감시 {
    static int N, M;
    static int minBlindSpot;
    static int[][] map;
    static ArrayList<int[]> cctvCollection; //cctv, cctv 행, cctv 열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cctvCollection = new ArrayList<>();

        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] != 0 && map[r][c] != 6) cctvCollection.add(new int[]{map[r][c], r, c});
            }
        }

        minBlindSpot = Integer.MAX_VALUE;
        dfs(0, map);
        System.out.println(minBlindSpot);
    }

    /**
     * 맵 카피
     */
    private static int[][] copyMap(int[][] origin) {
        int[][] tmp = new int[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                tmp[r][c] = origin[r][c];
            }
        }

        return tmp;

    }

    private static void dfs(int cctvCnt, int[][] temp) {
        //모든 cctv의 감시 방향이 결정되면
        if (cctvCnt == cctvCollection.size()) {
            //사각지대 갯수 세기
            int result = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (temp[r][c] == 0) result++;
                }
            }
            //최소 사각지대 갯수 업데이트
            minBlindSpot = Math.min(minBlindSpot, result);
            return;
        }


        int cctv = cctvCollection.get(cctvCnt)[0];
        int cctvR = cctvCollection.get(cctvCnt)[1];
        int cctvC = cctvCollection.get(cctvCnt)[2];

        int[][] deltas = getDeltas(cctv);
        for (int d1 = 0; d1 < deltas.length; d1++) {
            int[][] tmp = copyMap(temp);

            for (int d2 = 0; d2 < deltas[d1].length; d2++) {
                int direction = deltas[d1][d2];
                if (direction == 1) {
                    for (int r = cctvR; r >= 0; r--) {
                        if (tmp[r][cctvC] == 6) break; //벽
                        tmp[r][cctvC] = 9;
                    }
                } else if (direction == 2) {
                    for (int c = cctvC; c < M; c++) {
                        if (tmp[cctvR][c] == 6) break; //벽
                        tmp[cctvR][c] = 9;
                    }
                } else if (direction == 3) {
                    for (int r = cctvR; r < N; r++) {
                        if (tmp[r][cctvC] == 6) break; //벽
                        tmp[r][cctvC] = 9;
                    }
                } else {
                    for (int c = cctvC; c >=0; c--) {
                        if (tmp[cctvR][c] == 6) break; //벽
                        tmp[cctvR][c] = 9;
                    }
                }
            }
            dfs(cctvCnt + 1, tmp); //다음 cctv로, 현재 감시하는 map 넘기기
        }

    }

    /**
     * cctv 별 감시방향
     * 1:위 2:오른쪽 3:아래 4:왼쪽
     */
    private static int[][] getDeltas(int cctv) {
        if (cctv == 1) {
            return new int[][]{{1}, {2}, {3}, {4}};
        } else if (cctv == 2) {
            return new int[][]{{1, 3}, {2, 4}};
        } else if (cctv == 3) {
            return new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 1}};
        } else if (cctv == 4) {
            return new int[][]{{1, 2, 3}, {2, 3, 4}, {3, 4, 1}, {4, 1, 2}};
        } else {
            return new int[][]{{1, 2, 3, 4}};
        }
    }
}
