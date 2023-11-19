package org.example.완전탐색;

import java.util.ArrayList;

public class 모의고사 {
    public int[] solution(int[] answers) {

        int[] a = new int[3];

        int[] p1 = new int[]{1, 2, 3, 4, 5};
        int[] p2 = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
        int[] p3 = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int pp1 = 0;
        int pp2 = 0;
        int pp3 = 0;

        int cnt = answers.length;
        for (int ans : answers) {
            if (pp1 >= p1.length) pp1 = 0;
            if (pp2 >= p2.length) pp2 = 0;
            if (pp3 >= p3.length) pp3 = 0;

            if (ans == p1[pp1++]) a[0]++;
            if (ans == p2[pp2++]) a[1]++;
            if (ans == p3[pp3++]) a[2]++;
        }

        int max = Math.max(Math.max(a[0], a[1]), a[2]);

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] == max) result.add(i+1);
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}
