package baekjoon.탐색;

import java.io.*;
import java.util.StringTokenizer;

public class P6987_월드컵V2 {
    static final int TEST_CASE = 4;
    static int[][] scores; //6개국의 승,무,패
    static int[][] matches;

    public static void main(String[] args) throws IOException {
        for (int test_case = 0; test_case < TEST_CASE; test_case++) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            scores = new int[6][3]; //6개국의 승,무,패

            for (int team = 0; team < 6; team++) {
                scores[team][0] = Integer.parseInt(st.nextToken());
                scores[team][1] = Integer.parseInt(st.nextToken());
                scores[team][2] = Integer.parseInt(st.nextToken());
            }

            //총 15개의 라운드에서 경기를 치르는 팀의 조합
            matches = new int[15][2];
            int round = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = i + 1; j < 6; j++) {
                    matches[round][0] = i;
                    matches[round][1] = j;
                    round++;
                }
            }



        }
    }
}
