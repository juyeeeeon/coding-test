package baekjoon.스택과큐;

import java.io.*;
import java.util.*;

public class P1158_요세푸스문제V2 {
    static int N, K;
    static Queue<Integer> myQueue = new ArrayDeque<>();
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        //초기화
        for (int i = 1; i <= N; i++) {
            myQueue.add(i);
        }

        while (!myQueue.isEmpty()) {
            for (int i = 0; i < K-1; i++) { //1번 ~ k-1번 앞에서 뽑고 뒤로 넣음
                myQueue.add(myQueue.poll());
            }
            result.add(myQueue.poll()); //k번째 queue에서 뽑아서 result에 넣음
        }

        bw.write("<" + result.get(0));
        for (int i = 1; i < result.size(); i++) {
            bw.write(", " + result.get(i));
        }
        bw.write(">");

        bw.flush();
        bw.close();
        br.close();

    }
}
