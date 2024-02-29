package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1767_프로세서연결하기 {
    static int N, count;
    static int maxCoreNum;
    static int result;
    static int[][] map;
    static ArrayList<int[]> cores;
    static final int[][] deltas = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            cores = new ArrayList<>();
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] =Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1 && i != 0 && i != N - 1 && j != 0 && j != N - 1) //가장자리에 위치하지 않은 코어이면
                        cores.add(new int[]{i, j});
                }
            }

            //값 초기화
            maxCoreNum = Integer.MIN_VALUE;
            result = Integer.MAX_VALUE;

            dfs(0, 0, 0);
            System.out.println("#" + test_case + " " + result);
        }
    }

    /**
     *
     * @param idx cores의 인덱스
     * @param coreNum 현재까지 선택된 코어의 개수
     * @param len 현재까지 연결된 코어의 전선의 길이
     */
    private static void dfs(int idx, int coreNum, int len) {
        if (idx == cores.size()) { //모든 코어를 탐색하였다면
            if (maxCoreNum < coreNum) { //선택된 코어의 수가 maxCoreNum보다 크면
                maxCoreNum = coreNum; //maxCoreNum, result 업데이트
                result = len;
            } else if (maxCoreNum == coreNum) { //선택된 코어의 수가 maxCoreNum과 같다면
                if (result > len) result = len; //현재까지 연결된 코어의 전선의 길이가 result 보다 작으면 업데이트
            }
            return;
        }

        for (int d = 0; d < 4; d++) { //네방향 탐색
            if (isConnect(cores.get(idx), d)) { // idx번째 코어가 d방향으로 연결가능하면
                fill(cores.get(idx), d, 2); //idx번째 코어를 d방향으로 연결(전선을 2로 채움)
                dfs(idx + 1, coreNum + 1, len + count); // idx번째 코어를 선택할 경우
                fill(cores.get(idx), d, 0); //원복
            }
        }

        dfs(idx + 1, coreNum, len); // idx번째 코어를 선택 안할 경우
    }

    private static void fill(int[] core, int d, int value) {
        count = 0;

        int r = core[0] + deltas[d][0];
        int c = core[1] + deltas[d][1];
        while (r >= 0 && c >= 0 && r < N && c < N) {
            map[r][c] = value;
            count++;
            r = r + deltas[d][0];
            c = c + deltas[d][1];
        }
    }

    /**
     *
     * @param core 선택된 코어
     * @param d 전선의 방향
     * @return 연결가능여부
     */
    private static boolean isConnect(int[] core, int d) {
        int r = core[0] + deltas[d][0];
        int c = core[1] + deltas[d][1];

        while (r >= 0 && c >= 0 && r < N && c < N) {
            if (map[r][c] != 0) return false;
            r = r + deltas[d][0];
            c = c + deltas[d][1];
        }
        return true;
    }
}
