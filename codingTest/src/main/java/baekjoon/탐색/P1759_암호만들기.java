package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1759_암호만들기 {
    static int L, C;//암호의 갯수, 사용할 문자종류의 갯수
    static char[] candidates, password; //사용할 문자후보들, 사전식으로 가능성 있는 암호

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());// 암호의 갯수
        C = Integer.parseInt(st.nextToken());// 사용할 문자종류의 갯수

        candidates = new char[C];//사용할 문자후보들
        password = new char[L];//사전식으로 가능성 있는 암호
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            candidates[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(candidates); //사전식으로 정렬

        dfs(0, 0, 0, 0);

    }

    /**
     *
     * @param cnt 현재까지 만든 암호문자의 개수
     * @param start
     * @param moeum 모음의 개수
     * @param jaeum 자음의 개수
     */
    private static void dfs(int cnt, int start, int moeum, int jaeum) {
        if (cnt == L) { //현재까지 만든 암호 개수가 L개이면
            if (moeum >= 1 && jaeum >= 2) { //최소 한개의 모음 && 최소 두개의 자음이면
                System.out.println(password); // 암호완성
                return;
            } else {
                return;
            }
        }

        for (int i = start; i < C; i++) {
            password[cnt] = candidates[i];
            if (isMoeum(i)) { //candidates[i]가 모음이면
                dfs(cnt + 1, i + 1, moeum + 1, jaeum);
            } else { //candidates[i]가 자음이면
                dfs(cnt + 1, i + 1, moeum, jaeum + 1);
            }

        }
    }

    private static boolean isMoeum(int i) {
        return candidates[i] == 'a' || candidates[i] == 'e' || candidates[i] == 'i' ||
                candidates[i] == 'o' || candidates[i] == 'u';
    }
}
