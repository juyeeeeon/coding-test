package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2178_미로탐색 {

    static int[] rd = new int[]{-1, 0, 1, 0};
    static int[] cd = new int[]{0, 1, 0, -1};
    static int N, M;
    static int[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String readLine = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(readLine.substring(j, j + 1));
            }
        }
        visited = new boolean[N][M];

        BFS(0,0);

        System.out.println(arr[N-1][M-1]);
    }

    private static void BFS(int row, int col) {


        if (visited[row][col]) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(row, col));
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            Node vertex = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = vertex.row + rd[i];
                int nc = vertex.col + cd[i];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M && arr[nr][nc] != 0 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    arr[nr][nc] = arr[vertex.row][vertex.col]+1;
                    queue.add(new Node(nr, nc));
                }
            }

        }

    }

    private static class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
