package org.example.힙;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 디스크컨트롤러 {
    static int time;
    static int sum;

    static PriorityQueue<int[]> jobQ;
    static PriorityQueue<int[]> queue;

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{0,10},{4,10},{5,11},{15,2}}));
    }

    public static int solution(int[][] jobs) {
        time = 0;
        sum = 0;

        jobQ = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        queue = new PriorityQueue<int[]>((o1, o2) -> o1[1] - o2[1]);


        while (true) {
            time++;

            while (jobQ.peek()[0] <= time) {
                queue.add(jobQ.poll());
            }

        }
    }
}
