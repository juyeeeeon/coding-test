package org.example.완전탐색;

public class 카펫 {
    public static void main(String[] args) {
        for (int i : solution(24, 25)) {
            System.out.println(i);
        }
    }

    public static int[] solution(int brown, int yellow) {

        int[] answer = new int[2];

        for (int h = 1; h <= Math.ceil((double)yellow/2); h++) {
            if (yellow%h != 0) continue;
            int w = yellow/h;

            if (brown == (2 * w) + (2 * h) + 4) {
                answer[0] = w + 2;
                answer[1] = h + 2;
                return answer;
            }
        }

        return answer;
    }

}
