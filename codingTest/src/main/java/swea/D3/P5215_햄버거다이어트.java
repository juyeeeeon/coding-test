package swea.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P5215_햄버거다이어트 {
    static int N, L;
    static int taste, cal;
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
                map[i][0] = Integer.parseInt(st.nextToken());
                map[i][1] = Integer.parseInt(st.nextToken());
            }

            generateSubset();

            Integer maxTaste = Collections.max(result); //맛의 최댓값
            System.out.println("#" + test_case + " " + maxTaste);
        }
    }

    /**
     * 비트카운팅
     * 모든 subset을 구함
     */
    private static void generateSubset() {
        for (int i = 1; i < (1 << N); i++) { //(2^n - 1)개의 총 subset의 수(공집합 제외)
            taste = 0;
            cal = 0;
            for (int jari = 0; jari < N; jari++) { //각 자리 판별
                if ((i & (1 << jari)) != 0) { //1이면
                    taste += map[jari][0];
                    cal += map[jari][1];
                }
            }

            if (cal <= L) result.add(taste); //칼로리가 L 이하여야 한다.
        }
    }
}
