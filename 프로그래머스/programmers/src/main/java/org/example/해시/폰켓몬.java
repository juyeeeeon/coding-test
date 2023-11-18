package org.example.해시;

import java.util.HashMap;
import java.util.HashSet;

public class 폰켓몬 {
    public int solution(int[] nums) {
        int answer = 0;

        int N = nums.length;

        HashSet<Integer> hs = new HashSet<>();

        for (int num : nums) {
            hs.add(num);
        }

        answer = Math.min(hs.size(), N / 2);


/*      //나의 코드
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }

        if (hm.keySet().size() >= N / 2) {
            answer = N / 2;
        } else {
            answer = hm.keySet().size();
        }*/

        return answer;
    }
}
