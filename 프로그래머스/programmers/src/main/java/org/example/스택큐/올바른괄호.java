package org.example.스택큐;

import java.util.Stack;

public class 올바른괄호 {
    boolean solution(String s) {
        //Character가 아닌 String으로 하면 시간초과
        Stack<Character> stk = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            Character element = s.charAt(i);

            if (element == ')') {
                if (stk.isEmpty()) return false;
                else if (stk.peek() == '(') stk.pop();
                else stk.add(element);
            } else {
                stk.add(element);
            }
        }

        return stk.isEmpty();
    }
}
