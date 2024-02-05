package baekjoon.스택과큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class P2164_카드2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        while (queue.size() > 1) {
            queue.poll(); //제일 위에 있는 카드를 바닥에 버린다.
            queue.add(queue.poll()); //제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮긴다.
        }

        System.out.println(queue.poll());//제일 마지막에 남게 되는 카드

    }
}
