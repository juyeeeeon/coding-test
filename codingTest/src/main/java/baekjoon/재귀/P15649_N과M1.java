package baekjoon.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15649_N과M1 {
    static int N; //1 ~ N의 자연수
    static int M; //길이가 M인 수열
    static boolean[] visited; //인덱스에 해당하는 숫자가 사용되었는지
    static int[] numbers; //순열 저장 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1]; //0번째 인덱스는 사용 안함
        numbers = new int[M + 1]; //0번째 인덱스는 사용 안함

        Perm(0); //0개의 순열부터 시작


    }

    /**
     *
     * @param cnt: 현재까지 뽑은 순열의 개수
     */
    static void Perm(int cnt) {
        if (cnt == M) { //순열의 개수가 M개면 순열 생성 완료
            for (int i = 1; i < numbers.length; i++) {
                System.out.print(numbers[i] + " ");
            }
            System.out.println();

            return;
        }

        for (int i = 1; i <= N; i++) { //1부터 N까지의 수
            if (visited[i]) continue; //숫자 i가 사용되었으면 continue
            numbers[cnt+1] = i; //숫자 i을 numbers의 cnt+1번째 자리에 저장
            visited[i] = true; //숫자 i가 사용됨

            Perm(cnt+1); //뽑은 순열의 개수는 cnt+1

            visited[i] = false; //순열을 찾지 못했다면 원복
        }
    }
}
