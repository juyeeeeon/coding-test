package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P16234_인구이동 {
    static int N, L, R, answer;
    static ArrayList<int[]> continents;
    static boolean[][] visited;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        while (true) {
            boolean canMovie = false;
            visited = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!visited[r][c]) {
                        continents = new ArrayList<>();
                        bfs(r, c);
                        if (continents.size() <= 1) continue;
                        canMovie = true;
                        int population = getPopulation();
                        movePopulation(population);
                    }
                }
            }

            if (canMovie) answer++;
            else break;
        }

        System.out.println(answer);

    }

    private static void movePopulation(int population) {
        for (int[] continent : continents) {
            map[continent[0]][continent[1]] = population;
        }
    }

    private static int getPopulation() {
        int sum = 0;
        for (int[] continent : continents) {
            sum += map[continent[0]][continent[1]];
        }

        return sum/continents.size();

    }

    private static void bfs(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            continents.add(cur);

            for (int d = 0; d < deltas.length; d++) {
                int nr = cur[0] + deltas[d][0];
                int nc = cur[1] + deltas[d][1];

                if (isValid(nr, nc) && !visited[nr][nc] &&
                        Math.abs(map[nr][nc] - map[cur[0]][cur[1]]) >= L &&
                                Math.abs(map[nr][nc] - map[cur[0]][cur[1]]) <= R) {
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
