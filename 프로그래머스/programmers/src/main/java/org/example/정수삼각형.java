package org.example;

import java.util.Arrays;

public class 정수삼각형 {
    public static void main(String[] args) {
        int solution = solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}});
        System.out.println(solution);
    }
    public static int solution(int[][] triangle) {

        for (int i = 1; i < triangle.length; i++) {
            triangle[i][0] += triangle[i - 1][0];
            triangle[i][i] += triangle[i-1][i-1];
            for (int j = 1; j < i; j++) {
                triangle[i][j] += Math.max(triangle[i-1][j], triangle[i-1][j - 1]);
            }
        }
        return Arrays.stream(triangle[triangle.length - 1]).max().getAsInt();
    }
}
