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

        sushi = new int[d+1]; //종류가 1~30(idx)인 스시의 개수 저장 배열

        int sushiNum = 0; //선택한 초밥의 종류의 수
        for (int i = 0; i < k; i++) { //벨트의 0부터 k개의 스시로 초기화
            if (sushi[belt[i]] == 0) { //기존에 없던 종류이면
                sushi[belt[i]]++;
                sushiNum++; //종류 수 증가
            }else{
                sushi[belt[i]]++;
            }
        }

        if (sushi[c] == 0) maxCnt = Math.max(maxCnt, sushiNum + 1); //쿠폰초밥을 고르지 않았다면 종류 수 증가
        else maxCnt = Math.max(maxCnt, sushiNum); //쿠폰초밥을 이미 골랐다면 종류 수는 증가하지 않음


        for (int i = 1; i <= N; i++) { //i: 벨트 위 시작 초밥 위치
            int Lptr = i;
            int Rptr = i + k - 1;
            if (Rptr >= N) Rptr %= N;

            if (sushi[belt[Lptr - 1]] > 1) { //이전에 골랐던 초밥의 개수가 2 이상이면
                sushi[belt[Lptr - 1]]--; //개수만 하나 감소
            } else {//이전에 골랐던 초밥의 개수가 1이면
                sushi[belt[Lptr - 1]]--; //개수 감소
                sushiNum--; //종류 감소
            }

            if (sushi[belt[Rptr]] == 0) {//새로 고른 초밥의 개수가 0이면
                sushi[belt[Rptr]]++; //개수 증가
                sushiNum++; //종류 증가
            }else{ //새로 고른 초밥의 개수가 0이 아니면
                sushi[belt[Rptr]]++; //개수만 하나 증가
            }

            if (sushi[c] == 0) maxCnt = Math.max(maxCnt, sushiNum + 1);//쿠폰초밥을 고르지 않았다면 종류 수 증가
            else maxCnt = Math.max(maxCnt, sushiNum);//쿠폰초밥을 이미 골랐다면 종류 수는 증가하지 않음
        }

        System.out.println(maxCnt);
    }
}
