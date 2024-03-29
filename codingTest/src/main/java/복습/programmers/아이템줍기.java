package 복습.programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class 아이템줍기 {
    static int answer;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        visited = new boolean[102][102];

        for (int[] xyxy : rectangle) {
            int x1 = xyxy[0]*2;
            int y1 = xyxy[1]*2;
            int x2 = xyxy[2]*2;
            int y2 = xyxy[3]*2;

            for (int i = x1 + 1; i < x2; i++) {
                for (int j = y1 + 1; j < y2; j++) {
                    visited[i][j] = true;
                }
            }
        }

        answer = Integer.MAX_VALUE;
        bfs(rectangle, characterX * 2, characterY * 2, itemX * 2, itemY * 2);
        return answer / 2;

    }

    private static void bfs(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{characterX, characterY, 0});
        visited[characterX][characterY] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == itemX && cur[1] == itemY) {
                answer = cur[2];
                return;
            }

            for (int d = 0; d < deltas.length; d++) {
                int nx = cur[0] + deltas[d][0];
                int ny = cur[1] + deltas[d][1];

                if (isValid(nx, ny) && !visited[nx][ny] && onLine(nx, ny, rectangle)) {
                    queue.add(new int[]{nx, ny, cur[2] + 1});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < 102 && y < 102;
    }

    private static boolean onLine(int x, int y, int[][] rectangle) {
        for (int[] xyxy : rectangle) {
            int x1 = xyxy[0]*2;
            int y1 = xyxy[1]*2;
            int x2 = xyxy[2]*2;
            int y2 = xyxy[3]*2;

            if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
                return true;
            }
        }

        return false;
    }
}
