package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class P2239_스도쿠 {
    static ArrayList<int[]> blank;
    static int[][] map;
    static boolean find = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[10][10];
        blank = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 1; j <= 9; j++) {
                map[i][j] = charArray[j-1] - '0';
                if (map[i][j] == 0) blank.add(new int[]{i, j}); //0인 수를 blank에 담기
            }
        }

        dfs(0);
    }

    private static void dfs(int idx) {
        if (idx == blank.size()) {
            for (int i = 1; i <= 9; i++) {
                for (int j = 1; j <= 9; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }

            find = true;

            return;
        }


        int r = blank.get(idx)[0];
        int c = blank.get(idx)[1];

        boolean[] visitedRow = getRowVisited(r, c);
        boolean[] visitedCol = getColVisited(r, c);
        boolean[] visitedThree = getThreeVisited(r, c);

        for (int num = 1; num <= 9 && !find; num++) {
            if (!visitedRow[num] && !visitedCol[num] && !visitedThree[num]) {
                map[r][c] = num;
                dfs(idx + 1);
                map[r][c] = 0;
            }
        }
    }

    //해당 좌표에 포함되는 3X3 탐색하여 0이 아닌 수 visit 처리
    private static boolean[] getThreeVisited(int r, int c) {
        boolean[] check = new boolean[10];

        if (r >= 1 && r <= 3) {
            if (c >= 1 && c <= 3) {
                for (int i = 1; i <= 3; i++) {
                    for (int j = 1; j <= 3; j++) {
                        check[map[i][j]] = true;
                    }
                }
            }
            if (c >= 4 && c <= 6) {
                for (int i = 1; i <= 3; i++) {
                    for (int j = 4; j <= 6; j++) {
                        check[map[i][j]] = true;
                    }
                }
            }
            if (c >= 7 && c <= 9) {
                for (int i = 1; i <= 3; i++) {
                    for (int j = 7; j <= 9; j++) {
                        check[map[i][j]] = true;
                    }
                }
            }
        }
        if (r >= 4 && r <= 6) {
            if (c >= 1 && c <= 3) {
                for (int i = 4; i <= 6; i++) {
                    for (int j = 1; j <= 3; j++) {
                        check[map[i][j]] = true;
                    }
                }
            }
            if (c >= 4 && c <= 6) {
                for (int i = 4; i <= 6; i++) {
                    for (int j = 4; j <= 6; j++) {
                        check[map[i][j]] = true;
                    }
                }
            }
            if (c >= 7 && c <= 9) {
                for (int i = 4; i <= 6; i++) {
                    for (int j = 7; j <= 9; j++) {
                        check[map[i][j]] = true;
                    }
                }
            }
        }
        if (r >= 7 && r <= 9) {
            if (c >= 1 && c <= 3) {
                for (int i = 7; i <= 9; i++) {
                    for (int j = 1; j <= 3; j++) {
                        check[map[i][j]] = true;
                    }
                }
            }
            if (c >= 4 && c <= 6) {
                for (int i = 7; i <= 9; i++) {
                    for (int j = 4; j <= 6; j++) {
                        check[map[i][j]] = true;
                    }
                }
            }
            if (c >= 7 && c <= 9) {
                for (int i = 7; i <= 9; i++) {
                    for (int j = 7; j <= 9; j++) {
                        check[map[i][j]] = true;
                    }
                }
            }
        }
        return check;

    }

    //세로방향으로 탐색하여 0이 아닌 수를 visit 처리
    private static boolean[] getColVisited(int r, int c) {
        boolean[] answer = new boolean[10];
        for (int row = 1; row <= 9; row++) {
            if (map[row][c] != 0) answer[map[row][c]] = true;
        }
        return answer;
    }

    //가로방향으로 탐색하여 0이 아닌 수를 visit 처리
    private static boolean[] getRowVisited(int r, int c) {
        boolean[] answer = new boolean[10];
        for (int col = 1; col <= 9; col++) {
            if (map[r][col] != 0) answer[map[r][col]] = true;
        }
        return answer;
    }

}
