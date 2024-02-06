package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class P16926_배열돌리기1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = Math.min(N, M) / 2; //하나의 배열 안에서 사각형의 갯수 = 돌아가는 횟수
        for (int i = 0; i < R; i++) { //R번 회전
            for (int cnt = 0; cnt < count; cnt++) { //하나의 배열 안에서 회전
                int temp = map[cnt][cnt]; //맨 마지막에 넣기 위해 따로 저장

                for (int col = cnt + 1; col < M - cnt; col++) { //맨 윗줄 왼쪽으로 한칸씩 이동
                    map[cnt][col - 1] = map[cnt][col];
                }

                for (int row = cnt + 1; row < N - cnt; row++) { ///맨 오른쪽줄 위쪽으로 한칸씩 이동
                    map[row - 1][M - 1 - cnt] = map[row][M - 1 - cnt];
                }

                for (int col = M - 2 - cnt; col >= cnt; col--) { //맨 아랫줄 오른쪽으로 한칸씩 이동
                    map[N - 1 - cnt][col + 1] = map[N - 1 - cnt][col];
                }

                for (int row = N - 2 - cnt; row > cnt; row--) {//맨 왼쪽줄 아래쪽으로 한칸씩 이동
                    map[row + 1][cnt] = map[row][cnt];
                }

                map[cnt + 1][cnt] = temp;
            }
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                bw.write(map[row][col] + " ");
            }
            bw.write('\n');
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
