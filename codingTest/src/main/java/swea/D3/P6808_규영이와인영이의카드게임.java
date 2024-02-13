package swea.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P6808_규영이와인영이의카드게임 {
    static int T, win, lose;
    static boolean[] visited;
    static int[] cards;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cards = new int[9];
            visited = new boolean[19]; //1~19까지의 수들이 사용되었는지
            for (int i = 0; i < cards.length; i++) {
                int number = Integer.parseInt(st.nextToken());
                cards[i] = number;
                visited[number] = true;
            }

            win = 0;
            lose = 0;
            dfs(0, 0, 0);

            System.out.println("#" + test_case + " " + win + " " + lose);

        }
    }

    private static void dfs(int idx, int gyuScore, int inScore) {
        if (idx == 9) {
            if (gyuScore > inScore) win++;
            if (gyuScore < inScore) lose++;

            return;
        }

        for (int i = 1; i < visited.length; i++) {
            if (visited[i]) continue; //숫자 i가 사용되었으면 continue

            visited[i] = true;
            if (cards[idx] > i) {
                dfs(idx + 1, gyuScore + cards[idx] + i, inScore);
            } else if (cards[idx] < i) {
                dfs(idx + 1, gyuScore, inScore + cards[idx] + i);
            }
            visited[i] = false;
        }
    }
}
