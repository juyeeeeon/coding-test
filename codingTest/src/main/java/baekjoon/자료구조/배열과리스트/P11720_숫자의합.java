package baekjoon.자료구조.배열과리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P11720_숫자의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int sum = 0;
        String line = br.readLine();

        char[] chars = line.toCharArray();
        for (char c : chars) {
            sum += c-'0';
        }

        System.out.println(sum);
    }
}
