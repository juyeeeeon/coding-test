package baekjoon.스택과큐;

import java.io.*;
import java.util.Stack;

public class P1874_스택수열 {
    /*
    BufferedWriter는 일정량 이상의 데이터가 버퍼에 쌓이면 자동으로 flush를 함
    8192개 이상의 입력이 들어가서 flush 된다면 출력 초과

    따라서 BufferedWriter -> StringBuffer로 변경
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stk = new Stack<>();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int num = 1;
        for (int i = 0; i < N; i++) {
            int tmp = arr[i];

            if (stk.isEmpty() || stk.peek() < tmp) {
                while (num <= tmp) {
                    stk.push(num++);
                    sb.append("+" + "\n");
                }
                stk.pop();
                sb.append("-" + "\n");
            } else if (!stk.isEmpty() && stk.peek() == tmp) {
                stk.pop();
                sb.append("-" + "\n");
            } else {
                System.out.println("NO");
                return;
            }

        }

        System.out.println(sb);
        br.close();
    }
}
