package baekjoon.배열과리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1244_스위치켜고끄기 {
    static int switchNum;
    static int[] switches;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        switchNum = Integer.parseInt(br.readLine());
        switches = new int[switchNum + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < switchNum + 1; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int studentNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < studentNum; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            if (gender == 1) male(number);
            else female(number);
        }

        for (int i = 1; i <= switchNum; i++) {
            System.out.print(switches[i] + " ");
            if (i % 20 == 0) { //20개씩 줄바꿔서 출력
                System.out.println();
            }
        }
    }

    /**
     *
     * @param number 의 배수만큼 스위치 바꿈
     */
    private static void male(int number) {
        int i = 1;
        while (number * i <= switchNum) {
            turnSwitch(number * i);
            i++;
        }
    }

    private static void female(int number) {
        turnSwitch(number);

        int left = number - 1;
        int right = number + 1;

        while (left >= 1 && right <= switchNum && switches[left] == switches[right]) {
            turnSwitch(left);
            turnSwitch(right);
            left--;
            right++;
        }
    }

    /**
     *
     * @param idx: 바꿀 스위치
     */
    private static void turnSwitch(int idx) {
        if (switches[idx] == 1) switches[idx] = 0;
        else switches[idx] = 1;
    }
}
