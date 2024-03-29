import java.util.Arrays;

public class Tmp {

    public int solution(int n, int[] lost, int[] reserve) {
        int[] students = new int[n + 1];
        Arrays.fill(students, 1);

        for (int l : lost) {
            students[l]--;
        }

        for (int r : reserve) {
            students[r]++;
        }

        for (int i = 1; i < students.length; i++) {
            if (students[i] == 2) {
                if (i - 1 >= 1 && students[i - 1] == 0) {
                    students[i]--;
                    students[i-1]++;
                } else if (i + 1 <= n && students[i + 1] == 0) {
                    students[i]--;
                    students[i + 1]++;
                }
            }
        }

        int answer = 0;

        for (int i = 1; i < students.length; i++) {
            if (students[i] != 0) answer++;
        }
        return answer;
    }
}
