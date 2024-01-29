package programmers.완전탐색;

import java.util.HashSet;

public class 소수찾기 {

    static HashSet<Integer> answer = new HashSet<>();
    static boolean[] visited;
    static String[] elements;

    public static void main(String[] args) {
        System.out.println(solution("1231"));
    }

    public static int solution(String numbers) {

        elements = new String[numbers.length()];

        for (int i = 0; i < numbers.length(); i++) {
            elements[i] = numbers.substring(i, i + 1);
        }

        for (int i = 0; i < elements.length; i++) {
            visited = new boolean[elements.length];
            DFS(i, elements[i]);
        }


        return answer.size();
    }

    private static void DFS(int idx, String s) {
        if (visited[idx]) return;

        if (isPrime(s)) {
            answer.add(Integer.parseInt(s));
        }

        visited[idx] = true;

        for (int i = 0; i < elements.length; i++) {
            if (!visited[i]) {
                DFS(i, s + elements[i]);
                visited[i] = false;
            }
        }
    }

    private static boolean isPrime(String s) {
        int i = Integer.parseInt(s);
        if (i <= 1) return false;
        for (int j = 2; j <= Math.sqrt(i); j++) {
            if (i%j == 0) return false;
        }
        return true;
    }
}
