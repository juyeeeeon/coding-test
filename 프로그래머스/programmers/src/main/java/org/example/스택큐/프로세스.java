package org.example.스택큐;

import java.util.*;

public class 프로세스 {
    public int solution(int[] priorities, int location) {

        Queue<P> queue = new LinkedList<>();
        ArrayList<P> processed = new ArrayList<>();

        for (int i = 0; i < priorities.length; i++) {
            queue.add(new P(i, priorities[i]));
        }

        while (!queue.isEmpty()) {
            P now = queue.poll();

            int maxPrior = findMaxPrior(queue);

            if (now.priority < maxPrior) queue.add(now);
            else processed.add(now);
        }

        int answer = 0;

        for (int i = 0; i < processed.size(); i++) {
            if (processed.get(i).location == location) {
                answer = i+1;
            }
        }

        return answer;
    }

    private int findMaxPrior(Queue<P> queue) {
        int max = 0;
        for (P p : queue) {
            if (max < p.priority) {
                max = p.priority;
            }
        }

        return max;

    }


    private class P {
        int location;
        int priority;

        public P(int location, int priority) {
            this.location = location;
            this.priority = priority;
        }

    }
}
