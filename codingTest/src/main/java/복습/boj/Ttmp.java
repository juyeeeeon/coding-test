package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Ttmp {
    static int T, N, minLen, maxNum;
    static int[][] map;
    static ArrayList<int[]> cores;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상 하 좌 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            cores = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] == 1 && r != 0 && r != N - 1 && c != 0 && c != N - 1) {
                        cores.add(new int[]{r, c});
                    }
                }
            }

            minLen = Integer.MAX_VALUE;
            maxNum = Integer.MIN_VALUE;
            if (cores.isEmpty()) System.out.println("#" + test_case + " " + 0);
            else {
                dfs(0, 0, 0);
                System.out.println("#" + test_case + " " + minLen);
            }

        }

    }

    private static void dfs(int idx, int connectedCoreNum, int connectedLine) {
        if (idx == cores.size()) {
            if (connectedCoreNum > maxNum) {
                maxNum = connectedCoreNum;
                minLen = connectedLine;
            }
            if (connectedCoreNum == maxNum && connectedLine < minLen) {
                minLen = connectedLine;
            }

            return;
        }

        for (int d = 0; d < deltas.length; d++) {
            boolean canConnect = canConnect(d, cores.get(idx));
            if(canConnect){
                int cnt = fill(d, cores.get(idx), 9);
                dfs(idx + 1, connectedCoreNum + 1, connectedLine + cnt);
                fill(d, cores.get(idx), 0);
            }
        }
        dfs(idx+1, connectedCoreNum, connectedLine);
    }
    private static boolean canConnect(int d, int[] core) {
        int r = core[0];
        int c = core[1];

        while (true) {
            r += deltas[d][0];
            c += deltas[d][1];

            if(r < 0 || c < 0 || r >= N || c >= N) break;

            if (map[r][c] != 0) return false;
        }

        /* if (d == 0) { //상
            for (int r = core[0]-1; r >= 0; r--) {
                if(map[r][core[1]] != 0) return false;
            }

        } else if (d == 1) { //하
            for (int r = core[0]+1; r < N; r++) {
                if(map[r][core[1]] != 0) return false;
            }

        } else if (d == 2) { //좌
            for (int c = core[1]-1; c >= 0; c--) {
                if(map[core[0]][c] != 0) return false;
            }

        } else { //우
            for (int c = core[1]+1; c <N; c++) {
                if(map[core[0]][c] != 0) return false;
            }

        }
*/
        return true;
    }

    private static int fill(int d, int[] core, int num) {
        int cnt = 0;

        int r = core[0];
        int c = core[1];

        while (true) {
            r += deltas[d][0];
            c += deltas[d][1];

            if(r < 0 || c < 0 || r >= N || c >= N) break;

            cnt++;
            map[r][c] = num;
        }


        /*if (d == 0) { //상
            for (int r = core[0]-1; r >= 0; r--) {
                map[r][core[1]] = num;
                cnt++;
            }
        } else if (d == 1) { //하
            for (int r = core[0]+1; r < N; r++) {
                map[r][core[1]] = num;
                cnt++;
            }

        } else if (d == 2) { //좌
            for (int c = core[1]-1; c >= 0; c--) {
                map[core[0]][c] = num;
                cnt++;
            }

        } else { //우
            for (int c = core[1]+1; c < N; c++) {
                map[core[0]][c] = num;
                cnt++;
            }
        }
*/
        return cnt;
    }
}
