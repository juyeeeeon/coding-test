package org.example.정렬;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class K번째수 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int p = 0; p < commands.length; p++) {
            int i = commands[p][0];
            int j = commands[p][1];
            int k = commands[p][2];

            int[] tmp = Arrays.copyOfRange(array, i - 1, j);
            Arrays.sort(tmp);
            answer[p] = tmp[k-1];
        }

        /* //내가 푼 풀이
        int p = 0;
        for (int[] command : commands) {
            int i = command[0];
            int j = command[1];
            int k = command[2];

            ArrayList<Integer> arr = new ArrayList<>();
            for (int l = i - 1; l < j; l++) {
                arr.add(array[l]);
            }

            Collections.sort(arr);
            answer[p++] = arr.get(k - 1);
        }*/

        return answer;
    }
}
