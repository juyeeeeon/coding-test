package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// i-1, i, i+1 -> i, i+1, i+2 로 바꿔서 생각
public class P2138_전구와스위치 {
    static int N;
    static char[] inputBulbs1, inputBulbs2, outputBulbs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        inputBulbs1 = br.readLine().toCharArray();
        inputBulbs2 = inputBulbs1.clone();  //inputBulbs2는 앞 두자리를 switch

        outputBulbs = br.readLine().toCharArray();

        turnSwitch(0, inputBulbs2);
        turnSwitch(1, inputBulbs2);

        int answer1 = solve(N, inputBulbs1);
        int answer2 = solve(N, inputBulbs2);

        if (answer2 != -1) answer2++; //앞 두자리를 미리 switch 해놨으므로

        if (answer1 == -1)
            System.out.println(answer2);
        else if (answer2 == -1)
            System.out.println(answer1);
        else
            System.out.println(Math.min(answer2, answer1));
    }

    private static int solve(int n, char[] input) {
        int cnt = 0;

        for (int i = 0; i <= n-2; i++) { //i-1, i, i+1 -> i, i+1, i+2 로 바꿔서 생각하므로
            if (input[i] != outputBulbs[i]) {
                cnt++;
                turnSwitch(i, input);
                turnSwitch(i + 1, input);
                if (i != n - 2) turnSwitch(i + 2, input);
            }
        }

        return input[n - 1] != outputBulbs[n - 1] ? -1 : cnt;

    }

    private static void turnSwitch(int i, char[] bulbs) {
        if (bulbs[i] == '0') bulbs[i] = '1';
        else bulbs[i] = '0';
    }
}
