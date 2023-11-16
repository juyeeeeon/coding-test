package org.example.깊이너비우선탐색;

public class 타겟넘버 {
    int count = 0;
    public int solution(int[] numbers, int target) {
        int answer = 0;

        DFS(numbers, target, 0, 0);

        answer = count;
        return answer;
    }

    private void DFS(int[] numbers, int target, int depth, int result) {
        if (depth == numbers.length) { // 마지막 노드까지 진행했을 때
            if (target == result) count++; //target값과 합계가 같다면
            return;
        }

        int plus = result + numbers[depth];
        int minus = result - numbers[depth];

        DFS(numbers, target, depth + 1, plus);
        DFS(numbers, target, depth + 1, minus);
    }

}
