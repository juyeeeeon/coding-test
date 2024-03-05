package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15961_회전초밥 {
    static int N, d, k, c, maxNum;
    static int[] kindsOfSushi; //초밥 종류
    static int[] sushiOnBelt; //벨트 위에 있는 초밥

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //벨트 위의 접시 수
        d = Integer.parseInt(st.nextToken()); //초밥의 가짓수
        k = Integer.parseInt(st.nextToken()); //연속해서 먹는 접시의 수
        c = Integer.parseInt(st.nextToken()); //쿠폰 번호

        maxNum = Integer.MIN_VALUE;
        sushiOnBelt = new int[N];

        for (int i = 0; i < N; i++) {
            sushiOnBelt[i] = Integer.parseInt(br.readLine());
        }

        kindsOfSushi = new int[d + 1];

        int count = 0; //초밥의 가짓수

        //초기화
        for (int i = 0; i < k; i++) {
            int sushi = sushiOnBelt[i];
            if (kindsOfSushi[sushi] == 0) count++;  //초밥의 가짓수 증가
            kindsOfSushi[sushi]++; //해당 초밥의 갯수 증가
        }
        
        //최대 가짓수 업데이트
        if (kindsOfSushi[c] == 0) maxNum = Math.max(maxNum, count + 1); //쿠폰초밥이 포함되어 있지 않다면 가짓수 증가(count++ 쓰면 안됌!)
        else maxNum = Math.max(maxNum, count); 

        //슬라이딩 윈도우
        int start = 1; //시작 인덱스
        while (true) {
            if (start == 0) break; //원점으로 돌아오면 끝내기

            int removeSushi = sushiOnBelt[start - 1]; //빼려는 초밥
            kindsOfSushi[removeSushi]--; //빼려는 초밥의 수를 하나 감소
            if (kindsOfSushi[removeSushi] == 0) count--; //빼려는 초밥이 0이면 가짓수 1 감소

            int end = (start + k - 1) % N; //인덱스가 0부터 시작이므로 -1 해야됨
            int addSushi = sushiOnBelt[end]; //추가하려는 초밥
            if (kindsOfSushi[addSushi] == 0) count++; //추가하려는 초밥의 갯수가 0이면 가짓수 1 증가
            kindsOfSushi[addSushi]++; //추가하려는 초밥의 갯수 증가

            //최대 가짓수 업데이트
            if (kindsOfSushi[c] == 0) maxNum = Math.max(maxNum, count + 1); //쿠폰초밥이 포함되어 있지 않으면 가짓수 증가(count++ 쓰면 안됌!)
            else maxNum = Math.max(maxNum, count);

            start++;
            start %= N;
        }

        System.out.println(maxNum);
    }
}
