package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1767_프로세서연결하기 {
    static int T,N;
    static int maxConnectedCoreNum, minConnectedLen;
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] map;
    static ArrayList<int[]> cores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            cores = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (r > 0 && r < N - 1 && c > 0 && c < N - 1 && map[r][c] == 1) { //가장자리의 코어는 이미 연결되었다치고
                        cores.add(new int[]{r, c}); //가장자리를 제외한 코어의 좌표를 넣음
                    }
                }
            }
    
            //테스트케이스마다 초기화
            maxConnectedCoreNum = Integer.MIN_VALUE;
            minConnectedLen = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            System.out.println("#" + test_case + " " + minConnectedLen);
        }
    }

    /**
     * 
     * @param idx 코어의 idx
     * @param connectedCoreNum 연결된 코어의 개수
     * @param len 현재까지 연결된 전선의 길이
     */
    private static void dfs(int idx, int connectedCoreNum, int len) {
        if (idx == cores.size()) { //모든 코어를 다 돌았으면
            if (connectedCoreNum > maxConnectedCoreNum) { //연결된 코어의 수가 더 크다면 연결코어개수, 전선길이 업데이트
                maxConnectedCoreNum = connectedCoreNum;
                minConnectedLen = len;
            } else if (connectedCoreNum == maxConnectedCoreNum && len < minConnectedLen) { //연결된 코어의 수가 같다면 길이만 업데이트
                minConnectedLen = len;
            }

            return; //잊지말고 넣자
        }

        for (int d = 0; d < deltas.length; d++) { //사방탐색
            if (canConnect(idx, d)) { //idx번째 코어가 d방향으로 연결이 가능하면
                int cnt = fill(idx, d, 2); //전선을 깔음(= 2로 채움)
                dfs(idx + 1, connectedCoreNum + 1, len + cnt); //다음 코어로
                fill(idx, d, 0); //원복
            }
        }
        dfs(idx + 1, connectedCoreNum, len); //코어가 모든 방향으로 연결이 불가능하면 다음 코어로 
    }

    /**
     * 
     * @param idx 번째 코어
     * @param d 방향
     * @param value 로 map을 채움
     * @return
     */
    private static int fill(int idx, int d, int value) {
        int cnt = 0; //전선의 길이

        int r = cores.get(idx)[0];
        int c = cores.get(idx)[1];

        while (true) {
            r += deltas[d][0];
            c += deltas[d][1];

            if (!isValid(r, c)) break; //map의 범위를 벗어나면

            map[r][c] = value;
            cnt++;
        }

        return cnt;
    }

    /**
     * 
     * @param idx 번째 코어
     * @param d 방향
     * @return 연결이 가능한지
     */
    private static boolean canConnect(int idx, int d) {
        int r = cores.get(idx)[0];
        int c = cores.get(idx)[1];

        while (true) {
            r += deltas[d][0];
            c += deltas[d][1];

            if (!isValid(r, c)) break;

            if (map[r][c] != 0) return false;
        }

        return true;
    }

    /**
     * 
     * @param r
     * @param c
     * @return map의 범위 안에 드는지
     */
    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

}
