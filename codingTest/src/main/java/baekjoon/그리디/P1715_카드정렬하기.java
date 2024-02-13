package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P1715_카드정렬하기 {
    static int N, result;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        result = 0;

        while (pq.size() != 1) {
            int poll1 = pq.poll();
            int poll2 = pq.poll();

            int sum = poll1 + poll2;
            result += sum;
            pq.add(sum);
        }

        System.out.println(result);

    }
}
