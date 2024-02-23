package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15961_회전초밥 {

    static int N, d, k, c;
    static int maxCnt = Integer.MIN_VALUE;
    static int[] belt;
    static int[] sushi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //벨트 위에 놓인 접시의 수
        d = Integer.parseInt(st.nextToken()); //초밥의 가짓수
        k = Integer.parseInt(st.nextToken()); //연속해서 먹는 접시의 수
        c = Integer.parseInt(st.nextToken()); //쿠폰 번호

        belt = new int[N];
        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        sushi = new int[d+1];

        int sushiNum = 0;
        for (int i = 0; i < k; i++) { //벨트의 0부터 k개의 스시로 초기화
            if (sushi[belt[i]] == 0) {
                sushi[belt[i]]++;
                sushiNum++;
            }else{
                sushi[belt[i]]++;
            }
        }

        if ((belt[N - 1] == c || belt[k] == c) && sushi[c] == 0) maxCnt = Math.max(maxCnt, sushiNum + 1);
        else maxCnt = Math.max(maxCnt, sushiNum);


        for (int i = 1; i <= N - 1; i++) {
            int Lptr = i;
            int Rptr = i + k - 1;
            if (Rptr >= N) Rptr %= N;

            if (sushi[belt[Lptr - 1]] > 1) {
                sushi[belt[Lptr - 1]]--;
            } else {
                sushi[belt[Lptr - 1]]--;
                sushiNum--;
            }

            if (sushi[belt[Rptr]] == 0) {
                sushi[belt[Rptr]]++;
                sushiNum++;
            }else{
                sushi[belt[Rptr]]++;
            }

            if ((belt[Lptr-1] == c || belt[(Rptr+1)%N] == c) && sushi[c] == 0) maxCnt = Math.max(maxCnt, sushiNum + 1);
            else maxCnt = Math.max(maxCnt, sushiNum);
        }

        boolean flag = false;
        for (int i : belt) {
            if (i == c) flag = true;
        }
        if (!flag) maxCnt += 1;

        System.out.println(maxCnt);
    }
}
