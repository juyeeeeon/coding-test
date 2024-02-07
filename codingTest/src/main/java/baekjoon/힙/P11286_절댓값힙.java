package baekjoon.힙;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P11286_절댓값힙 {
    static int N;
    static PriorityQueue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        queue = new PriorityQueue<>((o1, o2) -> {
            if (Math.abs(o1) == Math.abs(o2)) { //두 수의 절댓값이 같을 때
                return o1 - o2;
            } else {
                return Math.abs(o1) - Math.abs(o2);
            }
        } );

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if (input != 0) {
                queue.add(input);
            } else {
                Integer poll = queue.poll();
                if (poll == null) System.out.println(0);
                else System.out.println(poll);
            }
        }
    }
}
