package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P9205_맥주마시면서걸어가기 {
    static int t, n;
    static String answer;
    static int houseX, houseY, rockX, rockY;
    static boolean[] visited;
    static int[][] convi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        t = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= t; test_case++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            houseX = Integer.parseInt(st.nextToken());
            houseY = Integer.parseInt(st.nextToken());

            convi = new int[n][2];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                convi[i][0] = Integer.parseInt(st.nextToken());
                convi[i][1] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            rockX = Integer.parseInt(st.nextToken());
            rockY = Integer.parseInt(st.nextToken());


            answer = "sad";
            visited = new boolean[n];
            bfs();

            System.out.println(answer);

        }
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{houseX, houseY});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (getDistance(cur[0], cur[1], rockX, rockY) <= 1000) { //(getDistance(cur[0], cur[1], rockX, rockY) / 50 <= 20) 하면 소숫점 버리기 때문에 안됌!!!!!
                answer = "happy";
                return;
            }

            for (int i = 0; i < n; i++) {
                if (visited[i]) continue;
                if (getDistance(cur[0], cur[1], convi[i][0], convi[i][1]) <= 1000) {
                    visited[i] = true;
                    queue.add(new int[]{convi[i][0], convi[i][1]});
                }
            }
        }

    }

    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

}
