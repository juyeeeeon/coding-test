package swea.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P5215_햄버거다이어트V2 {
    static int N, L, maxTaste;
    static ArrayList<Integer> result;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());


        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            result = new ArrayList<>();
            map = new int[N][2]; //[][0]:맛점수, [][1]:칼로리
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                map[i][0] = Integer.parseInt(st.nextToken()); //맛점수
                map[i][1] = Integer.parseInt(st.nextToken()); //칼로리
            }

            maxTaste = 0;
            DFS(0, 0, 0);

            System.out.println("#" + test_case + " " + maxTaste);
        }
    }

    /**
     * @param cnt      : 재료의 개수
     * @param tasteSum : 맛 합
     * @param calSum   :칼로리 합
     */
    private static void DFS(int cnt, int tasteSum, int calSum) {
        if (calSum > L) return; //칼로리의 합이 L보다 크면 리턴

        if (tasteSum > maxTaste) maxTaste = tasteSum; //칼로리의 합이 L보다 작고 맛 합이 최대맛합보다 크면 업데이트

        if (cnt == N) return; //둘다 아니고 모든 재료를 돌았으면 리턴

        //재료를 골랐을 때
        DFS(cnt + 1, tasteSum + map[cnt][0], calSum + map[cnt][1]);

        //재료를 고르지 않았을 때
        DFS(cnt + 1, tasteSum, calSum);
    }

}
