package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1012_유기농배추 {
    static int M;
    static int N;
    static int K;
    static int answer;
    static int[][] field;
    static boolean[][] visited;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); //테스트 개수

        for (int i = 0; i < T; i++) {
            answer = 0;

            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); //배추밭 가로길이
            N = Integer.parseInt(st.nextToken()); //배추밭 세로길이
            K = Integer.parseInt(st.nextToken()); //배추 개수

            field = new int[M][N];
            visited = new boolean[M][N];

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());

                field[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
            }

            for (int j = 0; j <M; j++) {
                for (int k = 0; k < N; k++) {
                    if (field[j][k] == 1 && !visited[j][k]) {
                        DFS(j, k);
                        answer++;
                    }
                }
            }

            System.out.println(answer);

        }
    }

    private static void DFS(int row, int col) {
        visited[row][col] = true;

        for (int i = 0; i < 4; i++) {
            int nr = row + dr[i];
            int nc = col + dc[i];

            if (nr >= 0 && nr < M && nc >= 0 && nc < N && field[nr][nc] == 1 && !visited[nr][nc]) {
                DFS(nr, nc);
            }
        }
    }
}
