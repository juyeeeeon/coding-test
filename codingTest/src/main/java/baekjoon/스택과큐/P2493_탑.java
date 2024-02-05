package baekjoon.스택과큐;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class P2493_탑 {
    static int N;
    static int idx = 1;
    static Stack<int[]> tops; //[탑의 높이, 인덱스]

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        tops = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());

            boolean findHigher = false;
            while (!tops.isEmpty()) {
                if (tops.peek()[0] > height) { //탑의 높이 비교
                    findHigher = true;
                    bw.write(tops.peek()[1] + " "); //해당 탑의 인덱스 write
                    break;
                } else {
                    tops.pop();
                }
            }

            if (!findHigher) {
                bw.write(0 + " "); //레이저 신호를 수신하는 탑이 존재하지 않으면 0 출력
            }

            tops.push(new int[]{height, idx++});
        }


        bw.flush();
        bw.close();
        br.close();
    }
}
