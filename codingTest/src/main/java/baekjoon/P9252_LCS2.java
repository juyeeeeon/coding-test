package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9252_LCS2 {

    static char[] input1, input2;
    static int[][] LCS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input1 = br.readLine().toCharArray();
        input2 = br.readLine().toCharArray();

        LCS = new int[input1.length + 1][input2.length + 1];

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

        int len1 = input1.length;
        int len2 = input2.length;
        String reverseAnswer = "";

        // 부분수열 구하기 시작
        while (len1 > 0 && len2 > 0) {

            //두 값이 같다면 왼쪽 대각선 위로
            if (input1[len1 - 1] == input2[len2 - 1]) {
                reverseAnswer += input1[len1 - 1];
                len1 -= 1;
                len2 -= 1;
                continue;
            }

            if (LCS[len1 - 1][len2] == LCS[len1][len2]) { // 위쪽값과 같다면 위로
                len1 -= 1;
            } else if (LCS[len1][len2 - 1] == LCS[len1][len2]) { // 왼쪽값과 같다면 왼쪽으로
                len2 -= 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = reverseAnswer.length()-1; i >=0 ; i--) {
            sb.append(reverseAnswer.charAt(i));
        }
        System.out.println(sb.toString());
    }
}
