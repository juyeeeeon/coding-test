package baekjoon.힙;

import java.io.*;
import java.util.PriorityQueue;

public class P1927_최소힙 {
    static int N;
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0) {
                if (pq.isEmpty()) bw.write(Integer.toString(0) + '\n');
                else bw.write(Integer.toString(pq.poll()) + '\n');
            } else {
                pq.add(input);
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
