package baekjoon;

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

    private static void male(int number) {
        for (int i = 1; i <= switchNum; i++) { //idx(=i)가 number의 배수이면 스위치 바꿈
            if (i % number == 0) turnSwitch(i);
        }
    }

    private static void female(int number) {

        int left = number;
        int right = left;

        if (left >= 1 && right <= switchNum) {
            while (left >= 1 && right <= switchNum && switches[left] == switches[right]) {
                left--;
                right++;
            }

            //left 인덱스나 right 인덱스가 범위 안에 존재하지 않거나 스위치가 다르면 while 조건 통과
            turnSwitchSpan(left + 1, right - 1); //따라서 left+1 인덱스 값부터 right-1 인덱스 값까지의 스위치를 바꿈
        } else turnSwitch(number);


    }

    /**
     *
     * @param idx: 바꿀 스위치
     */
    private static void turnSwitch(int idx) {
        if (switches[idx] == 1) switches[idx] = 0;
        else switches[idx] = 1;
    }

    /**
     * left 인덱스부터 right 인덱스까지 스위치 바꿈
     * @param left
     * @param right
     */
    private static void turnSwitchSpan(int left, int right) {
        for (int i = left; i <= right ; i++) {
            turnSwitch(i);
        }
    }
}
