package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2563_색종이 {
    private static final int length = 100;
    static int[][] dowhaji = new int[length][length];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            //도화지의 색종이 부분을 1로 바꿈
            for (int x = r; x < r + 10; x++) {
                for (int y = c; y < c + 10; y++) {
                    dowhaji[x][y] = 1;
                }
            }
        }

        //도화지의 1을 모두 더함
        int result = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                result += dowhaji[i][j];
            }
        }

        System.out.println(result);
    }
}
