package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P15686_치킨배달 {
    static int N, M;
    static int cityChickenDistance;
    static int[][] map;
    static ArrayList<int[]> houses, chickens, selectedChickens;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        houses = new ArrayList<>();
        chickens = new ArrayList<>();
        selectedChickens = new ArrayList<>();
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) houses.add(new int[]{i, j});
                if (map[i][j] == 2) chickens.add(new int[]{i, j});
            }
        }

        cityChickenDistance = Integer.MAX_VALUE;

        selectMChickens(0, 0);

        System.out.println(cityChickenDistance);
    }

    /**
     * M개의 치킨집을 고르는 조합
     */
    private static void selectMChickens(int start, int cnt) {
        if (cnt == M) {
            findChickenDistance();
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            selectedChickens.add(chickens.get(i));
            selectMChickens(i + 1, cnt + 1);
            selectedChickens.remove(selectedChickens.size() - 1);
        }

    }

    private static void findChickenDistance() {

        int tempCityChickenDistance = 0;
        for (int i = 0; i < houses.size(); i++) {
            int[] house = houses.get(i);
            int hr = house[0];
            int hc = house[1];

            int chickenDistanceFromHouse = Integer.MAX_VALUE;
            for (int j = 0; j < selectedChickens.size(); j++) {
                int[] chicken = selectedChickens.get(j);
                int cr = chicken[0];
                int cc = chicken[1];

                int distance = getDistance(hr, hc, cr, cc);
                chickenDistanceFromHouse = Math.min(chickenDistanceFromHouse, distance);
            }
            tempCityChickenDistance += chickenDistanceFromHouse;
        }

        cityChickenDistance = Math.min(cityChickenDistance, tempCityChickenDistance);
    }

    private static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}
