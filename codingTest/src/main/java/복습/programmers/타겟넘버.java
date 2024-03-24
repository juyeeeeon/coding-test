package 복습.programmers;

public class 타겟넘버 {
    static int[] g_numbers;
    static int g_target, answer;

    public int solution(int[] numbers, int target) {
        answer = 0;

        g_numbers = numbers;
        g_target = target;

        dfs(0, 0);

        return answer;
    }

    private void dfs(int cnt, int result) {
        if (cnt == g_numbers.length) {
            if (result == g_target) answer++;

            return;
        }

        dfs(cnt + 1, result + g_numbers[cnt]);
        dfs(cnt + 1, result - g_numbers[cnt]);
    }
}
