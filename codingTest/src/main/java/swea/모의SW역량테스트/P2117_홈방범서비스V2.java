package swea.모의SW역량테스트;


import java.util.Scanner;

public class P2117_홈방범서비스V2 {
    static int N, M;
    static int[][] map;
    static int max;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt(); // 도시 크기
            M = sc.nextInt(); // 하나의 집이 지불할 수 있는 비용

            map = new int[N][N];
            int c = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int tmp = sc.nextInt();
                    map[i][j] = tmp; // 집이면 1
                    if (tmp == 1) {
                        c++;
                    }
                }
            }

            max = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (c == max) break;
                    solve(i, j);
                }
            }

            System.out.println("#" + tc + " " + max);
        }
        sc.close();
    }

    private static void solve(int r, int c) {
        for (int k = 1; k <= N + N; k++) {
            int cnt = 0;
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    if ((Math.abs(i - r) + Math.abs(j - c)) <= k - 1)
                        if (map[i][j] == 1)
                            cnt++;

            int cost = k * k + (k - 1) * (k - 1);
            if (cost <= cnt * M)
                if (max < cnt)
                    max = cnt;
        }
    }

}