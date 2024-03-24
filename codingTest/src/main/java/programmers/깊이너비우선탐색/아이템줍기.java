package programmers.깊이너비우선탐색;

import java.util.*;

/**
 * 직사각형의 좌표를 두배씩 해줘야 한다.
 * 왜냐하면 겹쳤을 때 테두리 따라 이동해야하는데 테두리를 넘어서 이동하기 때문
 */
public class 아이템줍기 {

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}}, 1, 3, 7, 8));
    }
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = Integer.MAX_VALUE;

        boolean[][] visited = new boolean[102][102];


        //테두리 안쪽 visited = true 로
        for (int[] rec : rectangle) {
            for (int x = rec[0] * 2 + 1; x < 2 * rec[2]; x++) {

                for (int y = rec[1] * 2 + 1; y < 2 * rec[3]; y++) {

                    visited[x][y] = true;
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{characterX * 2, characterY * 2, 0});
        visited[characterX * 2][characterY * 2] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            //먼저 도착하는게 가장 짧은 거리임
            if (cur[0] == itemX * 2 && cur[1] == itemY * 2) {
                answer = cur[2];
                break;
            }

            for (int d = 0; d < deltas.length; d++) {
                int nx = cur[0] + deltas[d][0];
                int ny = cur[1] + deltas[d][1];

                if (!visited[nx][ny] && onBorder(rectangle, nx, ny)) {
                    queue.add(new int[]{nx, ny, cur[2] + 1});
                    visited[nx][ny] = true;
                }
            }
        }

        return answer / 2;
    }

    private static boolean onBorder(int[][] rectangle, int nx, int ny) {
        for (int[] rec : rectangle) {
            if (nx >= rec[0] * 2 && nx <= 2 * rec[2] &&
                    ny >= rec[1] * 2 && ny <= 2 * rec[3]) {
                return true;
            }
        }

        return false;
    }
}
