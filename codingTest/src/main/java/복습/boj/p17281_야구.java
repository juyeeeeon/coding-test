package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p17281_야구 {
    static int N, maxScore; //어닝 수
    static int[] order; //타자 순서
    static boolean[] visited;
    static int[][] result; //어닝별 선수들의 결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        result = new int[N + 1][10]; //[이닝][선수별 결과]
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                result[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order = new int[10];
        order[4] = 1; //4번째 순서는 1번 타자

        maxScore = Integer.MIN_VALUE;
        visited = new boolean[10];
        visited[4] = true;
        dfs(2);


        System.out.println(maxScore);
    }

    private static void dfs(int player) {
        if (player == 10) { //선수들의 순서가 정해졌다면
//            for (int i : order) {
//                System.out.print(i+" ");
//            }
//            System.out.println();

            maxScore = Math.max(maxScore, playGame());
            return;
        }

        for (int o = 1; o <= 9; o++) {
            if (visited[o]) continue;
            order[o] = player;
            visited[o] = true;
            dfs(player + 1);
            visited[o] = false;
        }
    }

    private static int playGame() {
        Ground ground = new Ground();
        int score = 0;

        int  o = 1; //첫번째 순서
        for (int e = 1; e <= N; e++) { //각 이닝
            while (true) {
                int player = order[o];
                ground.home = player;

                int hit = result[e][player];
                if (hit == 1) {
                    ground.anta();
                } else if (hit == 2) {
                    for (int i = 0; i < hit; i++) ground.anta();
                } else if (hit == 3) {
                    for (int i = 0; i < hit; i++) ground.anta();
                } else if (hit == 4) {
                    for (int i = 0; i < hit; i++) ground.anta();
                } else if (hit == 0) {
                    ground.outNum++;
                    if (ground.outNum == 3) { //3아웃
                        break; //이닝 종료
                    }
                }

                //다음 순서
                o++;
                if (o == 10) o = 1;
            }

            //다음 순서
            o++;
            if (o == 10) o = 1;

            score += ground.scoreNum;
            ground.init();
        }

        return score;
    }

    static class Ground{
        int home;
        int one;
        int two;
        int three;
        int outNum;
        int scoreNum;

        public Ground() {
            this.home = 0;
            this.one = 0;
            this.two = 0;
            this.three = 0;
            this.outNum = 0;
            this.scoreNum = 0;
        }

        public void init() {
            this.home = 0;
            this.one = 0;
            this.two = 0;
            this.three = 0;
            this.outNum = 0;
            this.scoreNum = 0;
        }

        public void anta(){
            if (three != 0) scoreNum++;
            three = two;
            two = one;
            one = home;

            home = 0; //!!!!
        }
    }
}
