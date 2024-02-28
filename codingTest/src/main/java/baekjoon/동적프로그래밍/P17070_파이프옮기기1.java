package baekjoon.동적프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17070_파이프옮기기1 {
    static int N;
    static int[][] map;
    static int[][][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        memo = new int[N + 1][N + 1][3]; //[행][열][0:가로/1:세로/2:대각선]

        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        memo[1][2][0] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 3; j <= N; j++) {
                if (map[i][j] == 1) continue;

                memo[i][j][0] = memo[i][j - 1][0] + memo[i][j - 1][2];
                memo[i][j][1] = memo[i - 1][j][1] + memo[i - 1][j][2];

                if (map[i - 1][j] == 1 || map[i][j - 1] == 1) continue;

                memo[i][j][2] = memo[i - 1][j - 1][0] + memo[i - 1][j - 1][1] + memo[i - 1][j - 1][2];
            }
        }

        System.out.println(memo[N][N][0] + memo[N][N][1] + memo[N][N][2]);
    }
}

