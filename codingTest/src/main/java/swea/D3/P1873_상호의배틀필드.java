package swea.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1873_상호의배틀필드 {
    static int T, H, W, N, trainX, trainY;
    static char[][] map;
    static char[] inst;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];

            //전차의 x, y좌표 초기값
            trainX = -1;
            trainY = -1;

            for (int r = 0; r < H; r++) {
                String line = br.readLine();

                for (int c = 0; c < W; c++) {
                    map[r][c] = line.charAt(c);

                    if (isTrain(r, c)) {
                        trainX = r;
                        trainY = c;
                    }
                }
            }



            N = Integer.parseInt(br.readLine());
            inst = new char[N];
            inst = br.readLine().toCharArray();

            for (char i : inst) {
                //전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
                if (i == 'U') instruction_U();
                //전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
                else if (i == 'D') instruction_D();
                //전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
                else if (i == 'L') instruction_L();
                //전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
                else if (i == 'R') instruction_R();
                //전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
                else if (i == 'S') instruction_S();
            }


            System.out.print("#" + test_case + " ");
            for (int i = 0; i < H; i++) {
                System.out.println(map[i]);
            }
        }
    }

    private static void instruction_U() {
        if (isValid(trainX - 1, trainY) && map[trainX - 1][trainY] == '.') {
            map[trainX - 1][trainY] = '^'; //한칸 위로 이동 후 위 방향으로 바꿈
            map[trainX][trainY] = '.'; //이전의 자리는 평지로

            trainX -= 1; //전차의 x좌표 업데이트
        } else {
            map[trainX][trainY] = '^';
        }
    }

    private static void instruction_D() {
        if (isValid(trainX + 1,trainY) && map[trainX + 1][trainY] == '.') {
            map[trainX + 1][trainY] = 'v'; //한칸 아래로 이동 후 아래 방향으로 바꿈
            map[trainX][trainY] = '.'; //이전의 자리는 평지로
            trainX = trainX + 1; //전차의 x좌표 업데이트
        } else {
            map[trainX][trainY] = 'v';
        }
    }

    private static void instruction_L() {
        if (isValid(trainX, trainY - 1) && map[trainX][trainY - 1] == '.') {
            map[trainX][trainY - 1] = '<'; //한칸 왼쪽으로 이동 후 왼쪽 방향으로 바꿈
            map[trainX][trainY] = '.'; //이전의 자리는 평지로

            trainY = trainY - 1; //전차의 y좌표 업데이트
        } else {
            map[trainX][trainY] = '<';
        }
    }

    private static void instruction_R() {
        if (isValid(trainX, trainY + 1) && map[trainX][trainY + 1] == '.') {
            map[trainX][trainY + 1] = '>'; //한칸 오른쪽으로 이동 후 오른쪽 방향으로 바꿈
            map[trainX][trainY] = '.'; //이전의 자리는 평지로

            trainY = trainY + 1; //전차의 y좌표 업데이트
        } else {
            map[trainX][trainY] = '>';
        }
    }

    private static void instruction_S() {
        if (map[trainX][trainY] == '^') { //위를 바라볼 때
            for (int r = trainX - 1; r >= 0; r--) {
                if (map[r][trainY] == '*') { //벽돌
                    map[r][trainY] = '.';
                    break;
                }
                if (map[r][trainY] == '#') break; //강철
            }
        }
        if (map[trainX][trainY] == 'v') { //아래를 바라볼 때
            for (int r = trainX + 1; r < H; r++) {
                if (map[r][trainY] == '*') { //벽돌
                    map[r][trainY] = '.';
                    break;
                }
                if (map[r][trainY] == '#') break; //강철
            }
        }
        if (map[trainX][trainY] == '<') { //왼쪽을 바라볼 때
            for (int c = trainY - 1; c >= 0; c--) {
                if (map[trainX][c] == '*') { //벽돌
                    map[trainX][c] = '.';
                    break;
                }
                if (map[trainX][c] == '#') break; //강철
            }

        }
        if (map[trainX][trainY] == '>') { //오른쪽을 바라볼 때
            for (int c = trainY + 1; c < W; c++) {
                if (map[trainX][c] == '*') { //벽돌
                    map[trainX][c] = '.';
                    break;
                }
                if (map[trainX][c] == '#') break; //강철
            }
        }
    }

    /**
     * map[r][c]가 전차인지 확인
     */
    private static boolean isTrain(int r, int c) {
        return map[r][c] == '^' || map[r][c] == 'v' || map[r][c] == '<' || map[r][c] == '>';
    }

    private static boolean isValid(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < H && ny < W;
    }

}
