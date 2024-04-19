package 복습.programmers;


public class 단어변환V2 {
    static int answer;
    static boolean[] visited;

    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];

        answer = Integer.MAX_VALUE;
        dfs(begin, target, words, 0);

        if (answer == Integer.MAX_VALUE) return 0;
        return answer;
    }

    private static void dfs(String cur, String target, String[] words, int cnt) {
        if (cur.equals(target)) {
            answer = Math.min(answer, cnt);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && diffOne(cur, words[i])) {
                visited[i] = true;
                dfs(words[i], target, words, cnt + 1);
                visited[i] = false;
            }
        }
    }

    private static boolean diffOne(String cur, String word) {
        int cnt = 0;
        for (int i = 0; i < cur.length(); i++) {
            if (cur.charAt(i) != word.charAt(i)) cnt++;
        }

        if (cnt == 1) return true;
        return false;
    }
}
