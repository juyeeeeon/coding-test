package swea.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1208_Flatten {
    static int dump, output; //주어진 dump 횟수
    static int[] boxesH; //박스의 높이
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            dump = Integer.parseInt(br.readLine());
            boxesH = new int[100];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 100; i++) {
                boxesH[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(boxesH); //박스의 높이를 정렬
            dump(0, test_case);

            System.out.println("#" + test_case + " " + output); //최고점과 최저점의 높이 차를 반환
        }
    }

    /**
     *
     * @param i: 현재까지 dump의 횟수
     * @param test_case: 테스트 케이스
     */
    private static void dump(int i, int test_case) {
        //주어진 덤프 횟수 이내에 평탄화가 완료되면
        // 더 이상 덤프를 수행할 수 없으므로
        int hDiff = boxesH[boxesH.length - 1] - boxesH[0];

        if (hDiff == 0 || hDiff == 1 || i == dump) {
            Arrays.sort(boxesH);
            output = hDiff;
            return;
        }

        //가장 높은 박스와 낮은 박스를 dump
        boxesH[0]++;
        boxesH[boxesH.length - 1]--;

        Arrays.sort(boxesH); //박스의 높이를 정렬

        dump(i+1, test_case);
    }
}

