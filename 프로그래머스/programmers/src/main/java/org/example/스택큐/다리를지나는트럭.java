package org.example.스택큐;

import java.util.LinkedList;
import java.util.Queue;

public class 다리를지나는트럭 {
    public static int time;

    public static void main(String[] args) {
        System.out.println(solution(10, 12, new int[]{4, 4, 4, 2, 2, 1, 1, 1, 1}));
    }
    public static int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<Integer> onBridge = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) {
            onBridge.add(0);
        }

        int idx = 0;
        int currentWeight = 0;

        while (idx < truck_weights.length) {
            currentWeight -= onBridge.poll();
            time++;

            if (currentWeight + truck_weights[idx] <= weight) {
                onBridge.add(truck_weights[idx]);
                currentWeight += truck_weights[idx++];
            } else onBridge.add(0);
        }

        return time + bridge_length;
    }
}
