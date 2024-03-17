package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P6987_월드컵 {
    static boolean avaliable;
    static int[][] match;
    static int[][] scores;

    public static void main(String[] args) throws IOException {
        scores = new int[6][3];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 0; test_case < 4; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int sum = 0;
            for (int i = 0; i < 6; i++) {
                int win = Integer.parseInt(st.nextToken());
                int draw = Integer.parseInt(st.nextToken());
                int lose = Integer.parseInt(st.nextToken());

                sum += win + draw + lose;
                scores[i] = new int[]{win, draw, lose};
            }

            if (sum != 30) {
                System.out.print(0 + " ");
                continue;
            }


            match = new int[15][2];
            int round = 0;
            for (int team1 = 0; team1 < 5; team1++) {
                for (int team2 = team1 + 1; team2 < 6; team2++) {
                    match[round++] = new int[]{team1, team2};
                }
            }

            avaliable = false;
            dfs(0);

            if (avaliable)System.out.print(1 + " ");
            else System.out.print(0 + " ");
        }

        System.out.println();
    }

    private static void dfs(int round) {
        if (round == 15) {
            avaliable = true;
            return;
        }

        int team1 = match[round][0];
        int team2 = match[round][1];

        //t1이 t2을 이겼을 때
        if (scores[team1][0] > 0 && scores[team2][2] > 0) {
            scores[team1][0]--;
            scores[team2][2]--;
            dfs(round + 1);
            scores[team1][0]++;
            scores[team2][2]++;
        }

        //t1과 t2가 비겼을 때
        if (scores[team1][1] > 0 && scores[team2][1] > 0) {
            scores[team1][1]--;
            scores[team2][1]--;
            dfs(round + 1);
            scores[team1][1]++;
            scores[team2][1]++;
        }

        //t1이 t2에게 졌을 때
        if (scores[team1][2] > 0 && scores[team2][0] > 0) {
            scores[team1][2]--;
            scores[team2][0]--;
            dfs(round + 1);
            scores[team1][2]++;
            scores[team2][0]++;
        }
    }
}
