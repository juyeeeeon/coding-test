package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17281_야구 {
    static int N, ans = -1;
    static boolean[] selected = new boolean[10];
    static int[] playOrder = new int[10];
    static int[][] playerResults;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        playerResults = new int[N][10];

        for(int inning=0;inning<N;inning++) {
            st = new StringTokenizer(br.readLine());
            for(int player=1;player<=9;player++) {
                playerResults[inning][player]= Integer.parseInt(st.nextToken());
            }
        }

        // 1번 선수는 4번 타자로 미리 결정
        playOrder[4] = 1;
        selected[4] = true;

        permutation(2); //1번 선수는 이미 결정했으므로 2번 선수부터
        System.out.println(ans);

    }

    // 타순 정하기
    private static void permutation(int player) {

        if (player == 10) {// 순서가 정해지면 게임 시작
            ans = Math.max(ans, playGame());
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (!selected[i]) {

                playOrder[i] = player;

                selected[i] = true;
                permutation(player + 1);
                selected[i] = false;
            }
        }
    }

    // 게임 진행
    private static int playGame() {
        int start = 1;
        int score = 0;

        for (int i = 0; i < N; i++) {
            // 각 주자들의 위치를 저장하기 위한 배열(아웃은 0번 인덱스에 저장 )
            int[] state = {0, 0, 0, 0, 0}; //out, 1루, 2루, 3루, home

            // 아웃이 3번이 되기 전까지 진행
            while (state[0] < 3) {
                run(state, playerResults[i][playOrder[start]]);
                if (start == 9) {
                    start = 1;
                } else {
                    start++;
                }
            }

            // 한 이닝이 끝나면 얻은 점수를 score에 저장
            score += state[4];
        }
        return score;
    }

    // 모든 주자들 이동
    private static void run(int[] state, int result) {

        for (int i = 0; i < result; i++) {
            // 홈으로 들어온 주자들의 수는 point[4]에 축적됨 = 점수
            state[4] += state[3];
            state[3] = state[2];
            state[2] = state[1];
            state[1] = 0;
        }

        // 이전에 나가있던 주자들을 이동시킨 후에 새로 공을 친 주자의 위치를 저장(타자가 home에서 result만큼 이동하므로)
        state[result]++;
    }
}
