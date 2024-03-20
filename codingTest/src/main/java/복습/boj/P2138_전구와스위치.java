package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2138_전구와스위치 {
    static int N, result1, result2;
    static char[] bulbs1, bulbs2, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bulbs1 = new char[N];
        bulbs2 = new char[N];

        bulbs1 = br.readLine().toCharArray();
        answer = br.readLine().toCharArray();

        result1 = 0;
        for (int i = 0; i < N - 1; i++) {
            if (bulbs1[i] == answer[i]) continue;
            turnThreeSwitch(i, bulbs1);
            result1++;
        }

        boolean findReulst1 = false;
        if (bulbs1[N-1] == answer[N-1]) findReulst1 = true;


        for (int i = 0; i < N; i++) {
            bulbs2[i] = bulbs1[i];
        }
        result2 = 0;
        turnSwitch(0, bulbs2);
        turnSwitch(1, bulbs2);
        result2++;
        for (int i = 0; i < N - 1; i++) {
            if (bulbs2[i] == answer[i]) continue;
            turnThreeSwitch(i, bulbs2);
            result2++;
        }

        boolean findResult2 = false;
        if (bulbs2[N-1] == answer[N-1]) findResult2 = true;

        if (findReulst1 && findResult2) System.out.println(Math.min(result1, result2));
        else if (findReulst1) System.out.println(result1);
        else if (findResult2) System.out.println(result2);
        else System.out.println(-1);

    }

    private static void turnSwitch(int i, char[] bulb) {
        if (bulb[i] == '0') {
            bulb[i] = '1';
        } else {
            bulb[i] = '0';
        }
    }

    private static void turnThreeSwitch(int i, char[] bulb) {
        turnSwitch(i, bulb);
        turnSwitch(i + 1, bulb);
        if (i+2 < N) turnSwitch(i + 2, bulb);
    }
}
