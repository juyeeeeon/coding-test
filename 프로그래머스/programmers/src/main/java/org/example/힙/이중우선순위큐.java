package org.example.힙;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class 이중우선순위큐 {
    static ArrayList<Integer> arr;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"})));

    }
    public static int[] solution(String[] operations) {
        arr = new ArrayList<>();

        for (String operation : operations) {
            String[] split = operation.split(" ");
            String operator = split[0];
            int operand = Integer.parseInt(split[1]);

            if (operator.equals("I")) {
                arr.add(operand);
            }

            if (!arr.isEmpty()) {
                if (operator.equals("D") && operand == 1) {
                    arr.remove(Collections.max(arr));
                }
                if (operator.equals("D") && operand == -1) {
                    arr.remove(Collections.min(arr));
                }
            }
        }

        if (arr.isEmpty()) {
            return new int[]{0, 0};
        }

        return new int[]{Collections.max(arr), Collections.min(arr)};
    }
}
