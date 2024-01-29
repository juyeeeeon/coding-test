package swea.D2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class P1974_스도쿠검증 {
    private static final int sudokuSize = 9;
    public static void main(String args[]) throws Exception
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());


        for(int test_case = 1; test_case <= T; test_case++)
        {

            int[][] sudoku = new int[9][9];

            for (int i = 0; i < sudokuSize; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < sudokuSize; j++) {
                    sudoku[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //행 체크
            int lowCheck = lowCheck(sudoku);

            //열 체크
            int colCheck = colCheck(sudoku);
            
            //박스 체크
            int boxCheck = boxCheck(sudoku);

            if (lowCheck * colCheck * boxCheck == 0) {
                bw.write("#" + test_case + " " + 0 + "\n");
            } else {
                bw.write("#" + test_case + " " + 1 + "\n");
            }


        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int boxCheck(int[][] sudoku) {

        for (int i = 0; i <= 6; i += 3) {
            for (int j = 0; j <= 6; j += 3) {

                int[] check = new int[10];

                int row = i;
                int col = j;

                for (int k = 0; k < 3; k++) {


                    for (int l = 0; l < 3; l++) {
                        if (check[sudoku[row + k][col + l]] != 0) {
                            return 0;
                        }
                        check[sudoku[row + k][col + l]]++;
                    }
                }
            }
        }
        return 1;
    }

    private static int colCheck(int[][] sudoku) {

        for (int i = 0; i < sudokuSize; i++) {
            int[] check = new int[10];
            for (int j = 0; j < sudokuSize; j++) {
                if (check[sudoku[j][i]] != 0) {
                    return 0;
                }
                check[sudoku[j][i]]++;
            }
        }

        return 1;
    }

    private static int lowCheck(int[][] sudoku) {
        for (int i = 0; i < sudokuSize; i++) {
            int[] check = new int[10];
            for (int j = 0; j < sudokuSize; j++) {
                if (check[sudoku[i][j]] != 0) {
                    return 0;
                }
                check[sudoku[i][j]]++;
            }
        }

        return 1;
    }
}
