package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * M개의 치킨집 조합(좌표) => 집(좌표)과 치킨집의 거리의 최소값 => 합
 */
public class P15686_치킨배달 {
    static int N, M;
    static int minChickenStreetSum = Integer.MAX_VALUE; // 최소치킨거리합
    static int chickenStreet, chickenStreetSum; //치킨거리, 치킨거리합
    static int[][] map;
    static ArrayList<int[]> chickenLocation, selectChickens, homeLocation; // 치킨집위치, M개의 치킨집위치, 집위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        chickenLocation = new ArrayList<>(); //치킨집 위치 저장
        selectChickens = new ArrayList<>(); //M개의 치킨집을 담는 배열
        homeLocation = new ArrayList<>(); //집 위치 저장

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) homeLocation.add(new int[]{r, c}); //집이면 집 좌표 저장
                if (map[r][c] == 2) chickenLocation.add(new int[]{r, c}); //치킨집이면 치킨집 좌표 저장
            }
        }

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
            findShortestChickenStreet(); //집에서부터 짧은 치킨거리 찾기
            return;
        }

        for (int i = start; i < chickenLocation.size(); i++) {
            selectChickens.add(chickenLocation.get(i));
            comb(cnt + 1, i + 1);
            selectChickens.remove(selectChickens.size() - 1); //원복
        }
    }


    private static void findShortestChickenStreet() {
        chickenStreetSum = 0; //치킨거리합 초기화
        for (int h = 0; h < homeLocation.size(); h++) { //하나의 집마다
            chickenStreet = Integer.MAX_VALUE; //치킨거리 초기화
            for (int c = 0; c < selectChickens.size(); c++) { //치킨집까지의 거리
                int[] curHome = homeLocation.get(h);
                int[] curChick = selectChickens.get(c);
                int distance = getDistance(curHome[0], curHome[1], curChick[0], curChick[1]);
                chickenStreet = Math.min(chickenStreet, distance); //치킨거리의 최솟값
            }
            chickenStreetSum += chickenStreet;
        }
        minChickenStreetSum = Math.min(minChickenStreetSum, chickenStreetSum); //최소 치킨거리합 초기화
    }

    private static int getDistance(int r1, int c1, int r2, int c2){
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}

