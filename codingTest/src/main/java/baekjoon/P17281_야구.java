package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17281_야구 {
    static int N;
    static int[] playerResults;
    static int[] hitters; //타순저장 배열
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        playerResults = new int[10];
        visited = new boolean[10];
        hitters = new int[10];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 9; i++) {
            playerResults[i] = Integer.parseInt(st.nextToken());
        }

        //1번 선수를 4번타자로
        hitters[4] = 1;
        visited[1] = true;
        perm(1);

//        dfs(4);
    }

    private static void perm(int cnt) {
        if (cnt == 4) return;

        if (cnt == 9) { //모든 타자의 타순이 정해짐
            playGame();

            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (visited[i]) continue;
            hitters[cnt] = i;
            visited[i] = true;

            perm(cnt + 1);

            visited[i] = false;

        }

        perm(cnt + 1);
    }

    private static void playGame() {
        Ground ground = new Ground();
        int order = 1;

        ground.taja = hitters[order%10]; //첫번째 선수가 타석에 자리잡음
        while(true) {
            int result = playerResults[hitters[order%10]]; //첫번째 선수의 결과

            if (result == 1) { //안타
                ground.anta(hitters[++order%10]);
            } else if (result == 2) { //2루타
                for (int i = 0; i < 2; i++) {
                    ground.anta(hitters[++order%10]);
                }
            } else if (result == 3) { //3루타
                for (int i = 0; i < 3; i++) {
                    ground.anta(hitters[++order%10]);
                }

            } else if (result == 4) { //홈런
                for (int i = 0; i < 4; i++) {
                    ground.anta(hitters[++order%10]);
                }

            } else if (result == 0) { //아웃
                ground.out();
            }
        }
    }


    static class Ground{
        int no1;
        int no2;
        int no3;
        int taja;

        int score;
        int out;

        public Ground() {
            no1 = 0;
            no2 = 0;
            no3 = 0;
            taja = 0;
            score = 0;
            out = 0;
        }

        void anta(int next_taja) {
            if (no3 != 0) score++;
            no3 = no2;
            no2 = no1;
            no1 = taja;
            taja = next_taja;
        }

        void ruta_2(int next_taja) {
            if (no3 != 0) score++;
            no3 = no2;
            no2 = no1;
            no1 = taja;
            taja = next_taja;
        }

        void out() {
            out++;
        }


    }

}
