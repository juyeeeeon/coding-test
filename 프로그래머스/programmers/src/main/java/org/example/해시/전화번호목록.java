package org.example.해시;

import java.util.*;

public class 전화번호목록 {
    public boolean solution(String[] phone_book) {
        boolean answer = true;

        Arrays.sort(phone_book); //숫자로된 문자열은 사전식으로 정렬됨

        //HashSet<String> hs = new HashSet<>();

        for (int i = 0; i < phone_book.length-1; i++) {
            if (phone_book[i+1].startsWith(phone_book[i])) {
                answer = false;
                return answer;
            }
            /* //내가 짠 코드 -> 타임아웃
            if (phone_book[i].equals(phone_book[i + 1].substring(0, phone_book[i].length()))) {
                answer = false;
                return answer;
            }
             */
        }



        return answer;
    }
}
