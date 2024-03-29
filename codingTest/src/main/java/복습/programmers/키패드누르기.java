package 복습.programmers;

public class 키패드누르기 {
    static int[][] keypad = {{3, 1}, {0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}, {3, 0}, {3, 2}};
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();

        int[] left = keypad[10];
        int[] right = keypad[11];

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
                sb.append("L");
                left = keypad[numbers[i]];
            }
            else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
                sb.append("R");
                right = keypad[numbers[i]];
            }else{
                int leftDistance = getDistance(left, keypad[numbers[i]]);
                int rightDistance = getDistance(right, keypad[numbers[i]]);

                if (leftDistance < rightDistance) {
                    sb.append("L");
                    left = keypad[numbers[i]];
                } else if (leftDistance > rightDistance) {
                    sb.append("R");
                    right = keypad[numbers[i]];
                } else{
                    if (hand.equals("left")) {
                        sb.append("L");
                        left = keypad[numbers[i]];
                    } else {
                        sb.append("R");
                        right = keypad[numbers[i]];
                    }
                }

            }
        }

        return sb.toString();

    }

    private int getDistance(int[] hand, int[] ints) {
        return Math.abs(hand[0] - ints[0]) + Math.abs(hand[1] - ints[1]);
    }
}
