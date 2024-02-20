package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P15686_치킨배달 {
    static final int CHICKEN_SPOT = 3;
    static int N, M;
    static int minChickenStreetSum = Integer.MAX_VALUE;
    static int chickenStreet = 0;
    static int chickenStreetSum = 0; //치킨거리합
    static int[][] map, testMap;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상 하 좌 우
    static boolean[][] visited;
    static ArrayList<int[]> chickensLocation, selectChickens, homeLocation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        testMap = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        chickensLocation = new ArrayList<>(); //치킨집 위치 저장
        homeLocation = new ArrayList<>(); //집 위치 저장

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 2) chickensLocation.add(new int[]{r, c}); //치킨집이면 치킨집 좌표 저장
                if (map[r][c] == 1) homeLocation.add(new int[]{r, c}); //집이면 집 좌표 저장
            }
        }

        selectChickens = new ArrayList<>(); //M개의 치킨집을 담는 배열
        //M개의 치킨집을 고르는 모든 조합
        comb(0, 0);


        System.out.println(minChickenStreetSum);

    }

    /**
     * M개의 치킨집을 고르는 모든 조합
     * @param cnt 지금까지 고른 치킨집의 수
     * @param start
     */
    private static void comb(int cnt, int start) {
        if (cnt == M) { //치킨집 M개가 골라지면
            setTestMap(); //testMap에서 골라진 치킨집을 CHICKEN_SPOT으로 업데이트
            startAtHome(); //집에서부터 짧은 치킨거리 찾기
            return;
        }

        for (int i = start; i < chickensLocation.size(); i++) {
            selectChickens.add(chickensLocation.get(i));
            comb(cnt + 1, i + 1);
            selectChickens.remove(selectChickens.size() - 1); //원복
        }
    }

    private static void setTestMap() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                testMap[i][j] = map[i][j];
            }
        }
        for (int[] selectChicken : selectChickens) {
            testMap[selectChicken[0]][selectChicken[1]] = CHICKEN_SPOT;
        }
    }

    private static void startAtHome() {
        chickenStreetSum = 0; //치킨거리합 초기화

        for (int[] home : homeLocation) {
            chickenStreet = Integer.MAX_VALUE; //치킨거리 초기화
            dfs(home[0], home[1], home[0], home[1]);
            chickenStreetSum += chickenStreet;
        }

        minChickenStreetSum = Math.min(minChickenStreetSum, chickenStreetSum); //최소 치킨거리합 초기화
    }

    /**
     *
     * @param r 탐색할 행
     * @param c 탐색할 열
     * @param hr 집 열
     * @param hc 집 행
     */
    private static void dfs(int r, int c, int hr, int hc) {
        if (visited[r][c]) return;

        //치킨집이면
        if (testMap[r][c] == CHICKEN_SPOT) {
            int distance = getDistance(hr, hc, r, c);
            chickenStreet = Math.min(chickenStreet, distance); //치킨거리 최솟값으로 업데이트
            return;
        }

        visited[r][c] = true;

        for (int d = 0; d < deltas.length; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];

            if (isValid(nr, nc) && !visited[nr][nc])
                dfs(nr, nc, hr, hc);
        }

        visited[r][c] = false; //다른 탐색을 위해 원복
    }

    private static boolean isValid(int nr, int nc) {
        return nr > 0 && nc > 0 && nr <= N && nc <= N;
    }

    private static int getDistance(int r1, int c1, int r2, int c2){
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}

