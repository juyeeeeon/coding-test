package org.example.해시;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class 의상 {
    public int solution(String[][] clothes) {
        int answer = 1;

        HashMap<String, Integer> hm = new HashMap<>();

        for (String[] clothe : clothes) {
            hm.put(clothe[1], hm.getOrDefault(clothe[1], 0) + 1);
        }


        Collection<Integer> values = hm.values();

        for (Integer value : values) {
            answer *= value+1; // 종류 중에서 아무것도 안입는 경우도 포함
        }

        answer -= 1; // 모든 종류를 안입는 경우 제외

        return answer;
    }
}
