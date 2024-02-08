package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P4012_요리사 {
    static int T, N, result;
    static int[][] map;
    static int[] ingreNo; //재료번호를 담는 배열
    static int[] anotherIngreNo; //ingreNo을 제외한 재료번호를 담는 배열


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ingreNo = new int[N / 2];
            anotherIngreNo = new int[N / 2];

            result = Integer.MAX_VALUE;
            comb(0, 0);

            System.out.println("#" + test_case + " " + result);

        }

    }

    /**
     * 조합 구하기
     *
     * @param cnt : 현재까지 구한 조합원소의 개수
     * @param start
     */
    private static void comb(int cnt, int start) {
        if (cnt == N / 2) { // N/2개의 원소를 갖는 조합구하기
            int sumA = 0;
            int sumB = 0;

            //ingreNo에 있는 모든 재료의 시너지의 합을 구함
            for (int i = 0; i < ingreNo.length-1; i++) {
                for (int j = i+1; j < ingreNo.length; j++) {
                     sumA += map[ingreNo[i]][ingreNo[j]] + map[ingreNo[j]][ingreNo[i]];
                }
            }

            int ptr = 0;
            for (int i = 0; i < N; i++) {
                if (!isContain(i, ingreNo)) anotherIngreNo[ptr++] = i; //i가 ingreNo에 포함되지 않으면 anotherIngreNo에 저장
            }

            //anotherIngreNo에 있는 모든 재료의 시너지의 합을 구함
            for (int i = 0; i < anotherIngreNo.length - 1; i++) {
                for (int j = i + 1; j < anotherIngreNo.length; j++) {
                    sumB += map[anotherIngreNo[i]][anotherIngreNo[j]] + map[anotherIngreNo[j]][anotherIngreNo[i]];
                }
            }

            //절댓값의 최솟값 구하기
            result = Math.min(result, Math.abs(sumA - sumB));

            return;
        }

        for (int i = start; i < N; i++) {
            ingreNo[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    /**
     *
     * @param i
     * @param ingreNo
     * @return: i가 ingreNo에 포함되는지
     */
    private static boolean isContain(int i, int[] ingreNo) {
        for (int num : ingreNo) {
            if (num == i) return true;
        }

        return false;
    }


}
