package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9251_LCS {
    static char[] input1, input2;
    static int[][] LCS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input1 = br.readLine().toCharArray();
        input2 = br.readLine().toCharArray();

        LCS = new int[input1.length+1][input2.length+1];

        for (int i = 1; i <= input1.length; i++) {
            for (int j = 1; j <= input2.length; j++) {
                if (input1[i - 1] == input2[j - 1]) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                } else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                }
            }
        }

        System.out.println(LCS[input1.length][input2.length]);

    }
}
