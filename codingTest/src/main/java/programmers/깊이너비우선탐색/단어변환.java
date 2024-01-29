package programmers.깊이너비우선탐색;

import java.util.LinkedList;
import java.util.Queue;

public class 단어변환 {
    static int N, count;
    static boolean[] visited;
    public int solution(String begin, String target, String[] words) {
        int answer = 0;

        N = words.length;
        visited = new boolean[N];

        BFS(begin, target, words, 0);

        answer = count;

        return answer;
    }

    private void BFS(String begin, String target, String[] words, int cnt) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(begin, cnt));

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (now.word.equals(target)) {
                count = now.cnt;
                return;
            }

            for (int j = 0; j < N; j++) {
                if (!visited[j] && diffOne(now.word, words[j])) {
                    visited[j] = true;
                    queue.add(new Node(words[j], now.cnt + 1));
                }
            }
        }
    }

    private boolean diffOne(String word, String word1) {
        int cnt = 0;
        for (int i = 0; i < word.length(); i++) {
            if (!word.substring(i, i + 1).equals(word1.substring(i, i + 1))) {
                cnt++;
            }
        }

        if (cnt == 1) return true;
        else return false;
    }

    private class Node {
        String word;
        int cnt;

        public Node(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }
}
