package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5644_무선충전 {
    static int T, M, A, result; //테스트케이스, 총 이동 시간, BC의 개수, 출력할 결과
    static int[] C, P; //충전범위, 성능
    static int[][] personA, personB, BC; //좌표

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            //[][0]:x좌표 [][1]:y좌표
            personA = new int[M+1][2];
            personB = new int[M+1][2];

            //personA, personB 초기위치
            personA[0][0] = 1;
            personA[0][1] = 1;
            personB[0][0] = 10;
            personB[0][1] = 10;

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                int num = Integer.parseInt(st.nextToken());
                move(num, personA, i);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                int num = Integer.parseInt(st.nextToken());
                move(num, personB, i);
            }

            //[][0]:x좌표 [][1]:y좌표
            BC = new int[A][2];
            C = new int[A]; //충전범위
            P = new int[A]; //처리량

            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                BC[i][0] = Integer.parseInt(st.nextToken());
                BC[i][1] = Integer.parseInt(st.nextToken());
                C[i] = Integer.parseInt(st.nextToken());
                P[i] = Integer.parseInt(st.nextToken());
            }

            result = 0;
            time(0, 0);

            System.out.println("#" + test_case + " " + result);

        }
    }

    private static void time(int t , int amountOfCharge) {
        if (t == M + 1) {
            result = amountOfCharge;
            return;
        }

        time(t + 1, amountOfCharge + findChargeAmountAtT(t));
    }


    /**
     * @return: 현재 좌표에서 personA와 personB의 최대 충전량
     */
    private static int findChargeAmountAtT(int t) {

        int max = 0;

        //personA와 personB로부터 모든 BC들의 최대 충전합 구하기
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < A; j++) {
                int sum = 0;

                int pA = findChargeAmountInCurrentLocation(personA[t][0], personA[t][1], i);
                int pB = findChargeAmountInCurrentLocation(personB[t][0], personB[t][1], j);

                if (i != j) sum = pA + pB; //다른 BC이면
                else sum = Math.max(pA, pB); //같은 BC이면

                if (max < sum) max = sum;
            }
        }

        return max;

    }

    private static int findChargeAmountInCurrentLocation(int personX, int personY, int i) {
        return Math.abs(personX - BC[i][0]) + Math.abs(personY - BC[i][1]) <= C[i] ? P[i] : 0;
    }

    private static void move(int num, int[][] person, int i) {
        if (num == 0) {
            person[i][0] = person[i - 1][0];
            person[i][1] = person[i - 1][1];
        }
        if (num == 1) {
            person[i][0] = person[i - 1][0];
            person[i][1] = person[i - 1][1] - 1;
        }
        if (num == 2) {
            person[i][0] = person[i - 1][0] + 1;
            person[i][1] = person[i - 1][1];
        }
        if (num == 3) {
            person[i][0] = person[i - 1][0];
            person[i][1] = person[i - 1][1] + 1;
        }
        if (num == 4) {
            person[i][0] = person[i - 1][0] - 1;
            person[i][1] = person[i - 1][1];
        }
    }

}
