package baekjoon.스택과큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P30892_상어키우기 {
    static long N; //앞바다에 존재하는 상어 마릿수
    static long K; //샼이 먹을 수 있는 상어의 최대 마릿수
    static long T; //샼의 최초 크기
    static ArrayList<Long> buffer; //상어들을 담아두는 buffer
    static Queue<Long> sharks; //앞바다에 존재하는 상어들의 크기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());
        T = Long.parseLong(st.nextToken());


        sharks = new PriorityQueue<>((o1, o2) -> Math.toIntExact(o2 - o1)); //내림차순 큐

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sharks.offer(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < K; i++) { //최대 K마리 만큼 먹을 수 있음
            buffer = new ArrayList<>();

            while (!sharks.isEmpty() && sharks.peek() >= T) { //샼의 크기 이상이면 못먹음
                buffer.add(sharks.poll()); //buffer에 담아두기
            }

            if (sharks.isEmpty()) { //샼이 먹을 상어가 없음(모두 샼보다 크기가 큼)
                System.out.println(T);
                return;
            }

            T += sharks.poll(); //샼이 먹어서 크기가 커짐

            for (long tmp : buffer) { //buffer에 담아둔 값을 다시 queue에 넣음
                sharks.offer(tmp);
            }
        }

        System.out.println(T);

    }
}
