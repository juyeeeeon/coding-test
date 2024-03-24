package programmers;

public class 키패드누르기 {
    static int left, right;
    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();

        left = 10;
        right = 12;

        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];

            if (num == 1 || num == 4 || num == 7) {
                answer.append("L");
                left = num;
            }
            else if (num == 3 || num == 6 || num == 9) {
                answer.append("R");
                right = num;
            } else {
                if (num == 0) num = 11;

                int distanceL = Math.abs(num - left) / 3 + Math.abs(num - left) % 3;
                int distanceR = Math.abs(num - right) / 3 + Math.abs(num - right) % 3;

                if (distanceL < distanceR) {
                    answer.append("L");
                    left = num;
                }
                if (distanceL > distanceR) {
                    answer.append("R");
                    right = num;
                }
                if (distanceL == distanceR) {
                    if (hand.equals("left")) {
                        answer.append("L");
                        left = num;
                    }
                    if (hand.equals("right")) {
                        answer.append("R");
                        right = num;
                    }
                }
            }
        }
        return answer.toString();
    }

}
