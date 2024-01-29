package programmers;

import java.util.PriorityQueue;

public class 더맵게 {
    static int cnt;
    static PriorityQueue<Integer> heap;
    public int solution(int[] scoville, int K) {
        cnt = 0;
        heap = new PriorityQueue<>();
        for (int i : scoville) {
            heap.add(i);
        }

        while (true) {
            if (heap.peek() >= K) break;
            if (heap.size() < 2) return -1;
            mix(heap);
        }

        return cnt;
    }

    private void mix(PriorityQueue<Integer> heap) {
        cnt++;
        int sc1 = heap.poll();
        int sc2 = heap.poll();
        heap.add(sc1 + sc2 * 2);
    }
}
