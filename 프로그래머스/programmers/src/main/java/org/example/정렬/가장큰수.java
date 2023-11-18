package org.example.정렬;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class 가장큰수 {

    public String solution(int[] numbers) {
        String answer = "";

        String[] arr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = Integer.toString(numbers[i]);
        }

        Arrays.sort(arr, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        //[0, 0, 0, ..] 인 경우
        if (arr[arr.length - 1].equals("0")) {
            return answer = "0";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = arr.length - 1; i >= 0; i--) {
            sb.append(arr[i]);
        }
        answer = sb.toString();


        return answer;
    }

    /* //시간초과
    boolean[] visited;
    static ArrayList<String> arr = new ArrayList<>();
    public String solution(int[] numbers) {
        String answer = "";

        for (int i = 0; i < numbers.length; i++) {
            visited = new boolean[numbers.length];
            visited[i] = true;
            DFS(numbers, 1, Integer.toString(numbers[i]));
        }

        Collections.sort(arr);

        answer = arr.get(arr.size() - 1);

        return answer;
    }

    private void DFS(int[] numbers, int cnt, String nums) {
        if (cnt == numbers.length) {
            arr.add(nums);
            return;
        }

        for (int i = 0; i < numbers.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                DFS(numbers, cnt + 1, nums + Integer.toString(numbers[i]));
                visited[i] = false;
            }
        }


    }*/
}
