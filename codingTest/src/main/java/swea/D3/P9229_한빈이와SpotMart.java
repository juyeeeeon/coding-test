package swea.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P9229_한빈이와SpotMart {
    static int T, N, M; //테스트케이스횟수, 과자봉지개수, 최대 무게
    static int[] snacks; //과자들의 무게를 담을 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int result = -1;

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            snacks = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                snacks[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(snacks); //과자들의 무게를 오름차순으로 정렬

            //두개의 과자를 고르는 모든 경우의 수
            for (int i = 0; i < N-1; i++) {
                for (int j = i+1; j < N; j++) {
                    int sum = snacks[i] + snacks[j]; // 고른 두 과자의 무게의 합
                    if (sum <= M) { //고른 두 과자의 무게의 합이 최대 무게인 M보다 작거나 같을 때
                        result = Math.max(result, sum); // 최댓값을 저장
                    }
                }
            }

            System.out.println("#" + test_case + " " + result);

        }

    }
}
