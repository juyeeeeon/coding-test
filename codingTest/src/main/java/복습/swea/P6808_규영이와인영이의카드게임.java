package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P6808_규영이와인영이의카드게임 {
    static int T, win, lose;
    static boolean[] visited;
    static int[] gyu, inyoung;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            visited = new boolean[19];

            gyu = new int[9];
            inyoung = new int[9];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 9; i++) {
                int num = Integer.parseInt(st.nextToken());
                gyu[i] = num;
                visited[num] = true;
            }

            win = 0;
            lose = 0;
            dfs(0, 0, 0);

            System.out.println("#" + test_case + " " + win + " " + lose);


        }
    }

    private static void dfs(int idx, int gyuScore, int inyoungScore) {
        if (idx == 9) {
            if (gyuScore > inyoungScore) win++;
            else if (gyuScore < inyoungScore) lose++;

            return;
        }

        for (int inyoung_card = 1; inyoung_card <= 18; inyoung_card++) {
            if (visited[inyoung_card]) continue;

            visited[inyoung_card] = true;
            int gyu_card = gyu[idx];
            if (gyu_card > inyoung_card) {
                dfs(idx + 1, gyuScore + gyu_card + inyoung_card, inyoungScore);
            } else if (gyu_card < inyoung_card) {
                dfs(idx + 1, gyuScore, inyoungScore + gyu_card + inyoung_card);
            }
            visited[inyoung_card] = false;

        }


    }

}
