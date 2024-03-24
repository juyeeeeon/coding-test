package programmers;

public class 키패드누르기V2 {
    static int[] left, right;
    static int[][] buttons = {{3, 1},
            {0, 0}, {0, 1}, {0, 2},
            {1, 0}, {1, 1}, {1, 2},
            {2, 0}, {2, 1}, {2, 2}};

    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();

        left = new int[]{3, 0};
        right = new int[]{3, 2};

        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];

            if (num == 1 || num == 4 || num == 7) {
                sb.append("L");
                left = buttons[num];
            } else if (num == 3 || num == 6 || num == 9) {
                sb.append("R");
                right = buttons[num];
            } else {
                int distanceL = getDistance(left, buttons[num]);
                int distanceR = getDistance(right, buttons[num]);

                if (distanceL < distanceR) {
                    sb.append("L");
                    left = buttons[num];
                } else if (distanceL > distanceR) {
                    sb.append("R");
                    right = buttons[num];
                } else {
                    if (hand.equals("left")) {
                        sb.append("L");
                        left = buttons[num];
                    } else {
                        sb.append("R");
                        right = buttons[num];
                    }
                }
            }
        }
        return sb.toString();
    }

    private int getDistance(int[] hand, int[] button) {
        return Math.abs(hand[0] - button[0]) + Math.abs(hand[1] - button[1]);
    }

}
