package baekjoon.탐색;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P6987_월드컵 {
    static int[] win, lose, draw, team1, team2;
    static boolean isValid;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        team1 = new int[15];        //경기하는 2팀
        team2 = new int[15];

        int round = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 6; j++) {
                team1[round] = i;
                team2[round] = j;
                round++;
            }
        }

        for (int test_case = 0; test_case < 4; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            win = new int[6];
            lose = new int[6];
            draw = new int[6];

            isValid = false;

            for (int team = 0; team < 6; team++) {
                win[team] = Integer.parseInt(st.nextToken());
                draw[team] = Integer.parseInt(st.nextToken());
                lose[team] = Integer.parseInt(st.nextToken());
            }

            //모든 스코어의 합은 30이어야 한다.
            if (Arrays.stream(win).sum() + Arrays.stream(draw).sum() + Arrays.stream(lose).sum() != 30) isValid = false;
            else dfs(0);

            if (isValid) bw.write(Integer.toString(1) + " ");
            else bw.write(Integer.toString(0) + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    /**
     *
     * @param round : 총 15번의 round가 있음
     */
    static void dfs(int round) {
        if (isValid) return;

        if (round == 15) {
            isValid = true;
            return;
        }

        int t1 = team1[round];
        int t2 = team2[round];

        //t1이 이기는 경우
        if (win[t1] > 0 && lose[t2] > 0) {
            win[t1]--;
            lose[t2]--;
            dfs(round + 1);
            win[t1]++;
            lose[t2]++;
        }
        //t1과 t2가 비기는 경우
        if (draw[t1] > 0 && draw[t2] > 0) {
            draw[t1]--;
            draw[t2]--;
            dfs(round + 1);
            draw[t1]++;
            draw[t2]++;
        }
        //t2가 지는 경우
        if (lose[t1] > 0 && win[t2] > 0) {
            lose[t1]--;
            win[t2]--;
            dfs(round + 1);
            lose[t1]++;
            win[t2]++;
        }
    }
}
