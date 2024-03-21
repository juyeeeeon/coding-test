package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5215_햄버거다이어트 {
    static int T, N, L, maxTaste;
    static int[][] ingredients;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            ingredients = new int[N][2]; //[맛점수][칼로리]
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                ingredients[i][0] = Integer.parseInt(st.nextToken());
                ingredients[i][1] = Integer.parseInt(st.nextToken());
            }

            maxTaste = Integer.MIN_VALUE;
            dfs(0, 0, 0);
            System.out.println("#" + test_case + " " + maxTaste);
        }
    }

    private static void dfs(int ingre, int taste, int calorie) {

        if (calorie > L) return;

        if (ingre == N) {
            if (taste > maxTaste) maxTaste = taste;
            return;
        }

        //재료를 골랐을 때
        dfs(ingre + 1, taste + ingredients[ingre][0], calorie + ingredients[ingre][1]);

        //재료를 고르지 않았을 때
        dfs(ingre + 1, taste, calorie);
    }
}
