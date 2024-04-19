package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P4013_특이한자석 {
    static int T, K, answer;
    static boolean[] visited;
    static int[][] magnets;
    static int[] points; //각 자석의 빨간색 화살표가 가리키는 인덱스!

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            K = Integer.parseInt(br.readLine());
            magnets = new int[5][8];

            for (int i = 1; i <= 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    magnets[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            points = new int[5]; //각 자석의 빨간색 화살표가 가리키는 인덱스!

            for (int inst = 0; inst < K; inst++) {
                st = new StringTokenizer(br.readLine());
                int mgNo = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                visited = new boolean[5];
                //빨간부분은 idx = 0
                //맞닿는 부분 왼쪽 = 6
                //맟닿는 부분 오른쪽 = 2
                rotate(mgNo, dir);
            }

            answer = 0;
            for (int i = 1; i <= 4; i++) {
                if (magnets[i][points[i]] == 1) { //S극
                    answer += (int) Math.pow(2, i - 1);
                }
            }

            System.out.println("#" + test_case + " " + answer);

        }
    }

    private static void rotate(int cur, int dir) {
        visited[cur] = true;

        //mgNo의 왼쪽
        int left = cur - 1;
        if (left >= 1 && !visited[left] && magnets[left][(points[left] + 2) % 8] != magnets[cur][(points[cur] + 6) % 8]) {
            rotate(left, dir * -1);
        }
        //mgNo의 오른쪽
        int right = cur + 1;
        if (right <= 4 && !visited[right] && magnets[right][(points[right] + 6) % 8] != magnets[cur][(points[cur] + 2) % 8]) {
            rotate(right, dir * -1);
        }

        if (dir > 0) { //시계방향 회전
            points[cur] = (points[cur] + 7) % 8;
        } else { //반시계방향 회전
            points[cur] = (points[cur] + 1) % 8;
        }


    }
}

