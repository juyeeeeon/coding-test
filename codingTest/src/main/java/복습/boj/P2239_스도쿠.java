package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class P2239_스도쿠 {
    static boolean find;
    static int[][] map;
    static ArrayList<int[]> blank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        blank = new ArrayList<>();
        map = new int[10][10];
        for (int i = 1; i <= 9; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 1; j <= 9; j++) {
                map[i][j] = charArray[j-1]-'0';
                if (map[i][j] == 0) {
                    blank.add(new int[]{i, j});
                }
            }
        }

        find = false;
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

        boolean[] rowVisited = getRowVisited(r, c);
        boolean[] colVisited = getColVisited(r, c);
        boolean[] threeVisited = getThreeVisited(r, c);

        for (int i = 1; i <= 9 && !find; i++) {
            if (!rowVisited[i] && !colVisited[i] && !threeVisited[i]) {
                map[r][c] = i;
                dfs(idx + 1);
                map[r][c] = 0; //!!!! 0으로 원복해줘야 다음 dfs 때 visited 배열 구할 수 있음
            }
        }
    }

    private static boolean[] getThreeVisited(int r, int c) {
        boolean[] visited = new boolean[10];
        int startRow = ((r - 1) / 3) * 3 + 1;
        int startCol = ((c - 1) / 3) * 3 + 1;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                visited[map[i][j]] = true;
            }
        }

        return visited;
    }

    private static boolean[] getRowVisited(int r, int c) {
        boolean[] visited = new boolean[10];
        for (int col = 1; col <= 9; col++) {
            if (map[r][col] != 0) visited[map[r][col]] = true;
        }
        return visited;
    }

    private static boolean[] getColVisited(int r, int c) {
        boolean[] visited = new boolean[10];
        for (int row = 1; row <= 9; row++) {
            if (map[row][c] != 0) visited[map[row][c]] = true;
        }
        return visited;
    }
}
