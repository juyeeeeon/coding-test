package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1873_상호의배틀필드 {
    static int T, H, W, N;
    static int trainR;
    static int trainC;
    static char[] inst;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];

            for (int r = 0; r < H; r++) {
                map[r] = br.readLine().toCharArray();
                for (int c = 0; c < W; c++) {
                    if (map[r][c] == '<' || map[r][c] == '>'
                            || map[r][c] == '^' || map[r][c] == 'v') {
                        trainR = r;
                        trainC = c;
                    }
                }
            }

            N = Integer.parseInt(br.readLine());
            inst = new char[N];

            inst = br.readLine().toCharArray();

            for (char i : inst) {
                instruction(i);
            }

            printResult(test_case);
        }
    }

    private static void instruction(char i) {
        if (i == 'U') {
            if (isValid(trainR-1, trainC) && map[trainR - 1][trainC] == '.') {
                map[trainR][trainC] = '.';
                trainR -= 1;
            }
            map[trainR][trainC] = '^';
        } else if (i == 'D') {
            if (isValid(trainR+1, trainC) && map[trainR + 1][trainC] == '.') {
                map[trainR][trainC] = '.';
                trainR += 1;
            }
            map[trainR][trainC] = 'v';
        } else if (i == 'L') {
            if (isValid(trainR, trainC-1) && map[trainR][trainC - 1] == '.') {
                map[trainR][trainC] = '.';
                trainC -= 1;
            }
            map[trainR][trainC] = '<';
        } else if (i == 'R') {
            if (isValid(trainR, trainC+1) && map[trainR][trainC + 1] == '.') {
                map[trainR][trainC] = '.';
                trainC += 1;
            }
            map[trainR][trainC] = '>';
        } else {
            bomb();
        }
    }

    private static void bomb() {
        switch (map[trainR][trainC]){
            case '^':
                for (int r = trainR-1; r >=0 ; r--) {
                    if (map[r][trainC] == '*') {
                        map[r][trainC] = '.';
                        break;
                    }
                    if (map[r][trainC] == '#') break;
                }
                break;

            case 'v':
                for (int r = trainR+1; r < H; r++) {
                    if (map[r][trainC] == '*') {
                        map[r][trainC] = '.';
                        break;
                    }
                    if (map[r][trainC] == '#') break;
                }
                break;

            case '<':
                for (int c = trainC-1; c >= 0; c--) {
                    if (map[trainR][c] == '*') {
                        map[trainR][c] = '.';
                        break;
                    }
                    if (map[trainR][c] == '#') break;
                }
                break;

            case '>':
                for (int c = trainC+1; c < W; c++) {
                    if (map[trainR][c] == '*') {
                        map[trainR][c] = '.';
                        break;
                    }
                    if (map[trainR][c] == '#') break;
                }
                break;
        }
    }

    private static void printResult(int test_case) {
        System.out.print("#" + test_case + " ");
        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                System.out.print(map[r][c]);
            }
            System.out.println();
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < H && c < W;
    }
}
