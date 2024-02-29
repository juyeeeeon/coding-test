package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1767_프로세서연결하기V2 {
    static int N, max, totalCount, min, map[][]; //멕시노스크기, 최대코어수, 비가장자리코어수, 최소전선길이합, 멕시노스셀정보
    static final int[][] deltas = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static ArrayList<int[]> list; //비가장자리 코어 리스트

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            list = new ArrayList<>();

            //값 초기화
            max = 0;
            totalCount = 0;
            min = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] =Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1 && i > 0 && i < N - 1 && j > 0 && j < N - 1) {//가장자리에 위치하지 않은 코어이면
                        list.add(new int[]{i, j});
                        totalCount++;
                    }
                }
            } //멕시노스 셀 정보 입력 및 비가장자리 코어 리스트 생성

            go(0, 0, 0);

            System.out.println("#" + test_case + " " + min);
        }
    }

    /**
     * @param idx
     * @param cCnt 처리한 코어 갯수
     * @param lCnt 전선길이 합
     */
    static void go(int idx, int cCnt, int lCnt) { //현재 코어로 전선처리 시도
        if (cCnt + (totalCount - idx) < max) return; //가지치기

        if (idx == totalCount) {
            if (max < cCnt) {
                max = cCnt;
                min = lCnt;
            } else if (max == cCnt) {
                if (min > lCnt) {
                    min = lCnt;
                }
            }

            return;
        }

        int[] cur = list.get(idx);
        int r = cur[0];
        int c = cur[1];

        //사방으로 시도
        for (int d = 0; d < 4; d++) {
            if (isAvailable(r, c, d)) {
                int len = setStatus(r, c, d, 2);//전선 놓기
                go(idx + 1, cCnt + 1, lCnt + len); //다음 코어 가기
                setStatus(r, c, d, 0); // 전선 지우기
            }
        }

        //전선 놓지 않기
        go(idx + 1, cCnt, lCnt);
    }

    /**
     * r,c 위치에서 d방향으로 전선놓기가 가능한지 체크
     */
    static boolean isAvailable(int r, int c, int d) {
        int nr = r;
        int nc = c;

        while (true) {
            nr += deltas[d][0];
            nc += deltas[d][1];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N) return true;
            if (map[nr][nc] != 0) return false;
        }
    }

    /**
     * r,c위치(코어위치)에서 d방향으로 s(2:전선, 0:빈칸)로 상태 set
     */
    static int setStatus(int r, int c, int d, int s) {
        int nr = r;
        int nc = c;
        int cnt = 0;

        while (true) {
            nr += deltas[d][0];
            nc += deltas[d][1];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N) break;
            map[nr][nc] = s;
            cnt++; //처리한 빈칸의 개수
        }

        return cnt;
    }
}
