package org.example.정렬;

import java.util.Arrays;

public class H_Index {
    public int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);

        int p = 0;

        for (int i = citations.length-1; i >= 0; i--) {
            p++;
            if (citations[i] >= p) answer++;
        }

        return answer;
    }
}
