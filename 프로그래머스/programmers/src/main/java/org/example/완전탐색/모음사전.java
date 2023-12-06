package org.example.완전탐색;

import java.util.ArrayList;

public class 모음사전 {
    static String[] word = {"A", "E", "I", "O", "U"};
    static ArrayList<String> arr;

    public static void main(String[] args) {
        System.out.println(solution("AAAAE"));
    }

    public static int solution(String word) {
        int answer = 0;
        arr = new ArrayList<>();

        DFS("", 0);

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equals(word)) {
                answer = i;
                break;
            }
        }

        return answer+1;
    }

    private static void DFS(String s, int len) {
        if (len == 5) return;

        for (String w : word) {
            String str = s.concat(w);
            arr.add(str);
            DFS(str, len + 1);
        }
    }
}
