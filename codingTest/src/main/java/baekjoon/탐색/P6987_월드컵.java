package baekjoon.탐색;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P6987_월드컵 {
    static int sum = 0;
    static int[] win, lose, draw, team1, team2;
    static boolean isValid;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //6개국 중 2팀을 뽑아 경기를 하는 모든 경우(= 6C2)
        team1 = new int[15];
        team2 = new int[15];

        //경기를 하는 두 팀 저장
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
                sum += win[team] = Integer.parseInt(st.nextToken());
                sum += draw[team] = Integer.parseInt(st.nextToken());
                sum += lose[team] = Integer.parseInt(st.nextToken());
            }

            //모든 스코어의 합은 30이어야 한다.
            if (sum != 30) isValid = false;
            else dfs(0);

            if (isValid) bw.write(Integer.toString(1) + " ");
            else bw.write(Integer.toString(0) + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    /**
     * 15개 경기 진행하기
     * 각 경기를 하나의 정점으로 보고, 15경기를 진행하기
     * 각 경기마다 갈 수 있는 경우의 수는 3가지(승-패, 무-무, 패-승)
     * @param round : 총 15번의 round가 있음
     */
    static void dfs(int round) {
        //가지치기: 다른 dfs에서 조건을 만족했으면, 더이상 다른 탐색 진행할 필요 없으므로 return
        //현재 문제에선 크게 시간적 영향 없음
        if (isValid) return;

        //기저 조건: 경기 횟수가 15번이 됐으면, 경기가 성립된 것
        if (round == 15) {
            isValid = true;
            return;
        }

        //경기 할 두 팀 세팅
        int t1 = team1[round];
        int t2 = team2[round];

        ////해당 승부로 결과로 깎을 점수가 있으면 (0점이면 게임 진행할 수 없음)
        //t1이 이기는 경우
        if (win[t1] > 0 && lose[t2] > 0) {
            win[t1]--;
            lose[t2]--;
            dfs(round + 1);
            //되돌아 왔으면 다른 경기결과 만들러 가봐야함.
            win[t1]++;
            lose[t2]++;
        }
        //t1과 t2가 비기는 경우
        if (draw[t1] > 0 && draw[t2] > 0) {
            draw[t1]--;
            draw[t2]--;
            dfs(round + 1);
            //되돌아 왔으면 다른 경기결과 만들러 가봐야함.
            draw[t1]++;
            draw[t2]++;
        }
        //t1이 지는 경우
        if (lose[t1] > 0 && win[t2] > 0) {
            lose[t1]--;
            win[t2]--;
            dfs(round + 1);
            //되돌아 왔으면 다른 경기결과 만들러 가봐야함.
            lose[t1]++;
            win[t2]++;
        }
    }
}
