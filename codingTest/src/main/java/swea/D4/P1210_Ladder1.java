package swea.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1210_Ladder1 {
    static boolean finish;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            br.readLine();

            map = new int[100][100];
            for (int row = 0; row < 100; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < 100; col++) {
                    map[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            for (int col = 0; col < 100; col++) {
                if (map[0][col] == 1) { //시작
                    finish = false;
                    move(0, col);
                }
                if (finish) {
                    System.out.println("#" + test_case + " " + col);
                    break;
                }
            }
        }


    }

    private static void move(int row, int col) {
        if (row == 99) {
            if (map[row][col] == 2) { //목적지까지 잘 도착했다면
                finish = true;
            }
            return;
        }

        //왼쪽
        if (col - 1 >= 0 && map[row][col - 1] == 1) { //왼쪽에 길이 있으면
            while (col - 1 >= 0 && map[row][col - 1] == 1) { //왼쪽 끝까지 이동
                col--;
            }
            move(row + 1, col); //아래로 이동
        }

        else if (col + 1 < 100 && map[row][col+1] == 1) { //오른쪽에 길이 있다면
            while (col + 1 < 100 && map[row][col + 1] == 1) { //오른쪽 끝까지 이동
                col++;
            }
            move(row + 1, col); //아래로 이동

        }

        else move(row + 1, col); //왼쪽 오른쪽 모두 길이 없다면 밑으로 이동
    }
}
