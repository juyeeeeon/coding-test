package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P16435_스네이크버드 {
    static int N, L;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>((o1, o2) -> o1 - o2);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(st.nextToken()));
        }

        while (!pq.isEmpty()) {
            if (L < pq.peek()) break;
            pq.poll();
            L++;
        }

        System.out.println(L);
    }
}

