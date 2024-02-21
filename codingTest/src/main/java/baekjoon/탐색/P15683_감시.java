package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P15683_감시 {
    /**
     * 0 : 북
     * 1 : 동
     * 2 : 남
     * 3 : 서
     */
    private static final int[][] dir1 = {{0}, {1}, {2}, {3}}; // 1번 CCTV가 한 번에 감시할 수 있는 방향
    private static final int[][] dir2 = {{0, 2}, {1, 3}}; // 2번 CCTV가 한 번에 감시할 수 있는 방향
    private static final int[][] dir3 = {{3, 0}, {0, 1}, {1, 2}, {2, 3}}; // 3번 CCTV가 한 번에 감시할 수 있는 방향
    private static final int[][] dir4 = {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}; // 4번 CCTV가 한 번에 감시할 수 있는 방향
    private static final int[] dir5 = {0, 1, 2, 3}; // 5번 CCTV가 한 번에 감시할 수 있는 방향

    private static int[][] map;
    private static int N;
    private static int M;
    private static int answer;
    private static ArrayList<int[]> cctvCollection;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        answer = Integer.MAX_VALUE;
        cctvCollection = new ArrayList<>();
        boolean[][] visited = new boolean[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] != 0) {
                    visited[r][c] = true; // 이미 CCTV나 벽인 칸은 사각지대가 될 수 없으므로 방문 처리해준다.
                    if (map[r][c] != 6) { // CCTV 좌표 저장
                        cctvCollection.add(new int[]{r, c});
                    }
                }
            }
        }

        findCctv(0, visited, cctvCollection.size());

        System.out.println(answer);
    }

    // 조합을 이용하여 가능한 모든 경우를 계산한다.
    private static void findCctv(int idx, boolean[][] visited, int cctvRemain) {
        if (cctvRemain == 0) { // 모든 CCTV를 찾았으면 탐색을 종료한다.
            answer = Math.min(answer, getNumOfBlindSpot(visited));
            return;
        }

        for (int i = idx; i < cctvCollection.size(); i++) {
            int[] cctvLocation = cctvCollection.get(i);
            int x = cctvLocation[0];
            int y = cctvLocation[1];
            int cctv = map[x][y];

            // search 함수는 방향에 따라 볼 수 있는 모든 방향을 탐색하고, visit 배열을 리턴한다.
            // 다음 칸(idx + 1)부터 아직 발견하지 않은 CCTV를 찾는다. (남아있는 CCTV 개수 - 1)을 인자로 넘긴다.
            if (cctv == 1) {
                for (int[] dir : dir1) {
                    boolean[][] searched = search(x, y, dir, visited);
                    findCctv(i + 1, searched, cctvRemain - 1);
                }
            } else if (cctv == 2) {
                for (int[] dir : dir2) {
                    boolean[][] searched = search(x, y, dir, visited);
                    findCctv(i + 1, searched, cctvRemain - 1);
                }
            } else if (cctv == 3) {
                for (int[] dir : dir3) {
                    boolean[][] searched = search(x, y, dir, visited);
                    findCctv(i + 1, searched, cctvRemain - 1);
                }
            } else if (cctv == 4) {
                for (int[] dir : dir4) {
                    boolean[][] searched = search(x, y, dir, visited);
                    findCctv(i + 1, searched, cctvRemain - 1);
                }
            } else if (cctv == 5) {
                boolean[][] searched = search(x, y, dir5, visited);
                findCctv(i + 1, searched, cctvRemain - 1);
            }

        }
    }

    // CCTV로 볼 수 있는 칸을 기록한다.
    private static boolean[][] search(int x, int y, int[] dir, boolean[][] visit) {
        boolean[][] copyVisited = new boolean[N][M];

        // 원래 visit 배열이 함께 업데이트되지 않도록 배열을 복사한다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyVisited[i][j] = visit[i][j];
            }
        }

        // 갈 수 있는 모든 방향을 탐색한다.
        for (int d : dir) {
            if (d == 0) { // 북
                for (int i = x; i >= 0; i--) {
                    if (map[i][y] == 6) {
                        break;
                    }
                    copyVisited[i][y] = true;
                }
            } else if (d == 1) { // 동
                for (int i = y; i >= 0; i--) {
                    if (map[x][i] == 6) {
                        break;
                    }
                    copyVisited[x][i] = true;
                }
            } else if (d == 2) { // 남
                for (int i = x; i < N; i++) {
                    if (map[i][y] == 6) {
                        break;
                    }
                    copyVisited[i][y] = true;
                }
            } else if (d == 3) { // 서
                for (int i = y; i < M; i++) {
                    if (map[x][i] == 6) {
                        break;
                    }
                    copyVisited[x][i] = true;
                }
            }
        }
        return copyVisited;
    }

    // 사각지대(탐색 때 방문하지 못한 칸 수)를 계산한다.
    private static int getNumOfBlindSpot(boolean[][] visited) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) cnt++;
            }
        }
        return cnt;
    }
}
