package baekjoon.자료구조.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2018_수들의합5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        int s = 1;
        int e = 1;
        int sum = 1;

        while (s<=N && e<=N) {
            if (sum == N) {
                answer++;
                sum += ++e;
            }
            else if (sum > N) {
                sum -= s;
                s++;
            }
            else { // sum < N
                e++;
                sum += e;
            }
        }


        System.out.println(answer);

    }
}
