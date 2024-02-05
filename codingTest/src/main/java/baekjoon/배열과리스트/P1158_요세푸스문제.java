package baekjoon.배열과리스트;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class P1158_요세푸스문제 {
    static int N, K, idx;
    static List<Integer> myList = new LinkedList<>();
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        //초기화
        for (int i = 1; i <= N; i++) {
            myList.add(i);
        }

        idx = K-1; //myList의 idx는 0부터 시작하므로 -1 해줘야 함
        for (int i = 0; i < N; i++) {
            result.add(myList.remove(idx));
            if (!myList.isEmpty()) {
                idx = (idx + K - 1) % myList.size();
            }
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
