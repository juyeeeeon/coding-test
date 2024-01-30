package swea.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2805_농작물수확하기V2 {
    static int N;
    static int sum;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            //주어진 값을 stream()을 이용하여 이차원배열에 넣는 방법
            for (int r = 0; r < N; r++) {
                map[r] = Arrays.stream(br.readLine().split(""))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            sum = 0;

            int idx = N/2; //주어진 행의 가운데 열의 index
            for (int r = 0; r < N; r++) {
                for (int c = idx; c < N - idx; c++) { //행의 양 끝에서부터 같은 길이(idx)만큼을 제외한 부분의 합을 구함
                    sum += map[r][c];
                }

                if (r < N/2) idx--; //마름모의 가운데를 자른 윗 삼각형
                else idx++; //마름모의 가운데를 자른 아랫 삼각형
            }

            System.out.println("#" + test_case + " " + sum);
        }
    }
}

