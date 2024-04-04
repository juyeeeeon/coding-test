package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P4014_활주로건설 {
    static int T, N, X, answer;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            answer = 0;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            map = new int[N][N];

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            //가로방향 건설
            for (int r = 0; r < N; r++) {
                boolean canBuild = true;
                visited = new boolean[N][N];

                for (int c = 0; c < N - 1 && canBuild; c++) {
                    if (map[r][c] == map[r][c + 1]) {
                        continue;

                    } else if (map[r][c] + 1 == map[r][c + 1]) { //오르막길
                        for (int i = c; i > c - X; i--) {
                            if (!isValid(r, i) || visited[r][i] || map[r][c] != map[r][i]) {
                                canBuild = false;
                                break;
                            }
                        }

                        if (canBuild) {
                            for (int i = c; i > c - X; i--) {
                                visited[r][i] = true;
                            }
                        }

                    } else if (map[r][c] - 1 == map[r][c + 1]) { //내리막길
                        for (int i = c + 1; i < c + 1 + X; i++) {
                            if (!isValid(r, i) || visited[r][i] || map[r][c + 1] != map[r][i]) {
                                canBuild = false;
                                break;
                            }
                        }

                        if (canBuild) {
                            for (int i = c + 1; i < c + 1 + X; i++) {
                                visited[r][i] = true;
                            }
                        }

                    } else {
                        canBuild = false;
                        break;
                    }
                }

                if (canBuild) {
                    answer++;
                }
            }

            //세로방향 건설
            for (int c = 0; c < N; c++) {
                boolean canBuild = true;
                visited = new boolean[N][N];

                for (int r = 0; r < N - 1 && canBuild; r++) {
                    if (map[r][c] == map[r + 1][c]) {
                        continue;
                    } else if (map[r][c] + 1 == map[r + 1][c]) { //오르막길
                        for (int i = r; i > r - X; i--) {
                            if (!isValid(i, c) || visited[i][c] || map[r][c] != map[i][c]) {
                                canBuild = false;
                                break;
                            }
                        }

                        if (canBuild) {
                            for (int i = r; i > r - X; i--) {
                                visited[i][c] = true;
                            }
                        }

                    } else if (map[r][c] - 1 == map[r + 1][c]) { //내리막길
                        for (int i = r + 1; i < r + 1 + X; i++) {
                            if (!isValid(i, c) || visited[i][c] || map[r + 1][c] != map[i][c]) {
                                canBuild = false;
                                break;
                            }
                        }

                        if (canBuild) {
                            for (int i = r + 1; i < r + 1 + X; i++) {
                                visited[i][c] = true;
                            }
                        }
                    } else {
                        canBuild = false;
                        break;
                    }
                }

                if (canBuild) {
                    answer++;
                }
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

}

