package org.example.완전탐색;

public class 최소직사각형 {
    public int solution(int[][] sizes) {
        int answer = 0;

        int length = 0;
        int height = 0;

        for (int i = 0; i < sizes.length; i++) {
            length = Math.max(Math.max(sizes[i][0], sizes[i][1]), length);
            height = Math.max(Math.min(sizes[i][0], sizes[i][1]), height);
        }
        answer = length * height;

        /* //나의 코드
        int max = 0;
        for (int i = 0; i < sizes.length; i++) {
            for (int j = 0; j < sizes[0].length; j++) {
                if (sizes[i][j] > max) {
                    max = sizes[i][j];
                }
            }
        }

        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 0; i < sizes.length; i++) {
            arr.add(Math.min(sizes[i][0], sizes[i][1]));
        }

        Collections.sort(arr);

        int tmp = arr.get(arr.size() - 1);

        answer = max * tmp;*/


        return answer;
    }
}
