package 복습.programmers;

import java.util.Arrays;

public class 체육복 {

    public int solution(int n, int[] lost, int[] reserve) {

        int[] students = new int[n + 1];
        Arrays.fill(students, 1);

        for (int i = 0; i < lost.length; i++) {
            students[lost[i]]--;
        }
        for (int i = 0; i < reserve.length; i++) {
            students[reserve[i]]++;
        }

        for (int i = 1; i <= n; i++) {
            if (students[i] == 0) {
                if (i - 1 >= 0 && students[i - 1] >= 2) {
                    students[i-1]--;
                    students[i]++;
                } else if (i + 1 <= n && students[i + 1] >= 2) {
                    students[i+1]--;
                    students[i]++;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i < students.length; i++) {
            if (students[i]>0) answer++;
        }

        return answer;
    }

}
