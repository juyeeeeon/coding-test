package org.example.스택큐;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 기능개발 {

    public static void main(String[] args) {
        int[] solution = solution(new int[]{93, 30, 55}, new int[]{1, 30, 5});
        for (int i : solution) {
            System.out.println(i);
        }
    }
    public static int[] solution(int[] progresses, int[] speeds) {

        Queue<Integer> days = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();


        for (int i = 0; i < progresses.length; i++) {

            int remain = 100 - progresses[i];
            int day = (int) Math.ceil(remain / (double) speeds[i]);


            if (!days.isEmpty() && days.peek() < day) {
                result.add(days.size());
                days.clear();
            }

            days.add(day);
        }

        result.add(days.size());


        int[] answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}
