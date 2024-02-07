package baekjoon.배열과리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P17406_배열돌리기4 {
    static int N, M, K;
    static int result = Integer.MAX_VALUE;
    static int[] index;
    static ArrayList<int[]> orders; //연산수행순서
    static boolean[] visited;
    static int[][] operator;
    static int[][] A;
    static int[][] cloneA;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N+1][M+1];
        cloneA = new int[N + 1][M + 1];
        index = new int[K];
        orders = new ArrayList<>();
        operator = new int[K][3];
        visited = new boolean[K];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                int input = Integer.parseInt(st.nextToken());
                A[i][j] = input;
                cloneA[i][j] = input;
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            operator[i][0] = Integer.parseInt(st.nextToken()); //r
            operator[i][1] = Integer.parseInt(st.nextToken()); //c
            operator[i][2] = Integer.parseInt(st.nextToken()); //s
        }

        perm(0); //순열

        for (int[] idx : orders) {
            initA();
            for (int i : idx) {
                int r = operator[i][0];
                int c = operator[i][1];
                int s = operator[i][2];

                rotate(r - s, c - s, r + s, c + s);
            }
            result = Math.min(result, minRowSum(A));
        }

        System.out.println(result);
    }

    private static void initA() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                A[i][j] = cloneA[i][j];
            }
        }
    }

    private static void perm(int cnt) {
        if (cnt == K) {
            int[] clone = index.clone();
            orders.add(clone);
            return;
        }

        for (int i = 0; i < K; i++) {
            if (visited[i]) continue;
            index[cnt] = i;
            visited[i] = true;
            perm(cnt + 1);
            visited[i] = false;
        }
    }


    private static void rotate(int r1, int c1, int r2, int c2) {
        int rect_n = Math.min((r2 - r1) / 2, (c2 - c1) / 2); //하나의 배열 안에서 돌려야하는 사각형의 개수

        for (int i = 0; i < rect_n; i++) {
            int tmp = A[r1 + i][c1 + i];

            for (int r = r1 + 1 + i; r <= r2 - i; r++) { //가장 왼쪽을 한칸씩 위로
                A[r - 1][c1 + i] = A[r][c1 + i];
            }

            for (int c = c1 + 1 + i; c <= c2 - i; c++) { //가장 아래쪽을 한칸씩 왼쪽으로
                A[r2 - i][c - 1] = A[r2 - i][c];
            }

            for (int r = r2 - 1 - i; r >= r1 + i; r--) { //가장 오른쪽을 한칸씩 아래로
                A[r + 1][c2 - i] = A[r][c2 - i];
            }

            for (int c = c2 - 1 - i; c >= c1 + 1 + i; c--) { //가장 위쪽을 한칸씩 오른쪽으로
                A[r1 + i][c + 1] = A[r1 + i][c];
            }

            A[r1 + i][c1 + 1 + i] = tmp;
        }
    }

    private static int minRowSum(int[][] map) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < map.length; i++) {
            int sum = 0;
            for (int j = 1; j < map[i].length; j++) {
                sum += map[i][j];
            }

            min = Math.min(min, sum);
        }

        return min;
    }


}
