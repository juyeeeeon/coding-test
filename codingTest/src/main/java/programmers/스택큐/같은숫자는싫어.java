package programmers.스택큐;

import java.util.Stack;

public class 같은숫자는싫어 {
    public int[] solution(int []arr) {
        Stack<Integer> stk = new Stack<>();

        for (int num : arr) {
            if (!stk.isEmpty() && stk.peek() == num) continue;
            stk.add(num);
        }

        int[] answer = new int[stk.size()];
        for (int i = answer.length-1; i >= 0; i--) {
            answer[i] = stk.pop();
        }

        return answer;
    }
}
