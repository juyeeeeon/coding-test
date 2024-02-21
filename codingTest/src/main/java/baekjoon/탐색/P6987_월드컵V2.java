package baekjoon.탐색;

import java.io.*;
import java.util.StringTokenizer;

public class P6987_월드컵V2 {
    static final int TEST_CASE = 4;
    static int sum;
    static boolean possible;
    static int[][] scores; //6개국의 승,무,패
    static int[][] matches;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int test_case = 0; test_case < TEST_CASE; test_case++) {
            scores = new int[6][3]; //6개국의 승,무,패
            sum = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int team = 0; team < 6; team++) {
                sum += scores[team][0] = Integer.parseInt(st.nextToken()); //승
                sum += scores[team][1] = Integer.parseInt(st.nextToken()); //무
                sum += scores[team][2] = Integer.parseInt(st.nextToken()); //패
            }

            //모든 스코어의 합이 30이 아니면 불가능한 결과이다
            if (sum != 30) {
                bw.write(0 + " ");
                continue;
            }

            //총 15개의 라운드(= 6C2)에서 경기를 치르는 팀의 조합
            matches = new int[15][2];
            int round = 0;
            for (int team1 = 0; team1 < 5; team1++) {
                for (int team2 = team1 + 1; team2 < 6; team2++) {
                    matches[round][0] = team1;
                    matches[round][1] = team2;
                    round++;
                }
            }

            possible = false;
            recursion(0);

            if (possible) bw.write(1 + " ");
            else bw.write(0 + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void recursion(int round) {
        if (round == 15) { //15번의 라운드가 성사되면 가능한 경우임
            possible = true;
            return;
        }

        int team1 = matches[round][0];
        int team2 = matches[round][1];

        //team1이 이겼을 때, team2가 졌을 때
        if (scores[team1][0] > 0 && scores[team2][2] > 0) {
            scores[team1][0]--;
            scores[team2][2]--;
            recursion(round + 1);
            scores[team1][0]++;
            scores[team2][2]++;
        }

        //team1과 team2가 비겼을 때
        if (scores[team1][1] > 0 && scores[team2][1] > 0) {
            scores[team1][1]--;
            scores[team2][1]--;
            recursion(round + 1);
            scores[team1][1]++;
            scores[team2][1]++;
        }

        //team1이 지고, team2가 이겼을 때
        if (scores[team1][2] > 0 && scores[team2][0] > 0) {
            scores[team1][2]--;
            scores[team2][0]--;
            recursion(round + 1);
            scores[team1][2]++;
            scores[team2][0]++;
        }
    }
}
