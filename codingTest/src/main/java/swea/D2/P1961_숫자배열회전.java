package swea.D2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class P1961_숫자배열회전 {
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = Integer.parseInt(br.readLine());

            int[][] arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] rotated90 = rotate90(arr);
            int[][] rotated180 = rotate90(rotated90);
            int[][] rotated270 = rotate90(rotated180);

            System.out.println("#" + test_case + " ");
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    System.out.print(rotated90[i][j]);
                }
                System.out.print(" ");
                for(int j = 0; j < N; j++) {
                    System.out.print(rotated180[i][j]);
                }
                System.out.print(" ");
                for(int j = 0; j < N; j++) {
                    System.out.print(rotated270[i][j]);
                }
                System.out.println();
            }

        }
    }

    private static int[][] rotate90(int[][] arr) {
        int N = arr.length;
        int[][] result = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[j][N - 1 - i] = arr[i][j];
            }
        }
        return result;
    }
}
