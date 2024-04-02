package programmers;

import java.util.ArrayList;

public class 하노이탑 {
    static ArrayList<int[]> arr;
    public int[][] solution(int n) {
        arr = new ArrayList<>();

        hanoi(n, 1, 3, 2);

        int[][] answer = new int[arr.size()][2];
        for (int i = 0; i < arr.size(); i++) {
            answer[i] = arr.get(i);
        }

        return answer;
    }

    private void hanoi(int cnt, int s, int e, int m) {
        if (cnt == 0) return;

        hanoi(cnt - 1, s, m, e);
        arr.add(new int[]{s, e});
        hanoi(cnt - 1, m, e, s);
    }
}

