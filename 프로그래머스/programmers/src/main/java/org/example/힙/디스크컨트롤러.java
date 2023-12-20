package org.example.힙;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class 디스크컨트롤러 {

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{0,10},{4,10},{5,11},{15,2}}));
    }

    /**
     * 1) jobs를 시작시간을 기준으로 정렬해주고
     * 2) 우선순위큐를 소요시간을 기준으로 만들어줘야 합니다.
     * <p>
     * 그 이후 아래의 작업을 반복해주시면 됩니다.
     * 1. 진행된 시점보다 요청시점이 전인 jobs을 우선순위큐에 넣어줍니다.
     * 2. 만약 큐가 비어있다면 time을 다음 job의 요청시점인 jobs[idx][0]로 맞춰주고, queue에 jobs[idx]를 넣어줍니다.
     * 3. queue에서 제일 앞에 있는 작업(소요시간이 제일 작은 작업)을 꺼내서 time을 계산합니다.
     * 4. result에 (현재 작업이 끝난 시간 - 요청시점)을 더해주시면 됩니다.
     */

    static int result;
    static int time;
    static Queue<int[]> queue;

    public static int solution(int[][] jobs) {

        //jobs을 시작시간 기준으로 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        //우선순위 큐를 소요시간 기준으로 만들기
        queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        time = jobs[0][0];

        int idx = 0;
        while (idx < jobs.length || !queue.isEmpty()) {
            //1. 진행된 시점보다 요청시점이 전인 jobs을 우선순위큐에 넣어줍니다.
            while (idx < jobs.length && jobs[idx][0] <= time) {
                queue.add(jobs[idx++]);
            }

            //2. 만약 큐가 비어있다면 time을 다음 job의 요청시점인 jobs[idx][0]로 맞춰주고, queue에 jobs[idx]를 넣어줍니다.
            if (queue.isEmpty()) {
                time = jobs[idx][0];
                queue.add(jobs[idx++]);
            }

            //3. queue에서 제일 앞에 있는 작업(소요시간이 제일 작은 작업)을 꺼내서 time을 계산합니다.
            int[] job = queue.poll();
            time += job[1];

            //4. result에 (현재 작업이 끝난 시간 - 요청시점)을 더해주시면 됩니다.
            result += time - job[0];
        }

        return result / jobs.length;

    }
}
