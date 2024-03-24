package 복습.programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class 단어변환 {
    static int answer;
    static boolean[] visited;
    static ArrayList<String> arr;

    public int solution(String begin, String target, String[] words) {

        arr = new ArrayList<>();

        arr.add(begin);
        for (int i = 0; i < words.length; i++) {
            arr.add(words[i]);
        }

        answer = Integer.MAX_VALUE;
        visited = new boolean[arr.size()];
        bfs(target);

        if (answer == Integer.MAX_VALUE) return 0;
        return answer;
    }

    private void bfs(String target) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0}); //[arr의 idx][cnt]
        visited[0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            String curWord = arr.get(cur[0]);
            int cnt = cur[1];

            if (curWord.equals(target)) {
                answer = Math.min(answer, cnt);
                return;
//                continue;
            }

            for (int i = 0; i < arr.size(); i++) {
                String nextWord = arr.get(i);

                if (diff(curWord, nextWord) == 1 && !visited[i]) {
                    queue.add(new int[]{i, cnt + 1});
                    visited[i] = true;
                }
            }
        }

    }

    private int diff(String curWord, String nextWord) {
        int cnt = 0;

        int ptr = 0;
        while (ptr < curWord.length()) {
            if (curWord.substring(ptr, ptr + 1).equals(nextWord.substring(ptr, ptr + 1))) {
                ptr++;
                continue;
            }
            cnt++;
            ptr++;
        }

        return cnt;

    }
}
