package org.example;

public class 체육복 {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] students = new int[n];
        for (int r : reserve) {
            students[r-1]++;
        }
        for (int l : lost) {
            students[l-1]--;
        }

        for (int i = 0; i < n; i++) {
            if (students[i] == -1) {
                if (i - 1 >= 0 && students[i - 1] == 1) {
                    students[i - 1]--;
                    students[i]++;
                } else if (i + 1 < n && students[i + 1] == 1) {
                    students[i + 1]--;
                    students[i]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (students[i] >= 0) answer++;
        }

        return answer;
    }
}
