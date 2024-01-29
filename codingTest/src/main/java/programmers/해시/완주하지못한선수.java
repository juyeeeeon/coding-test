package programmers.해시;

import java.util.HashMap;

public class 완주하지못한선수 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> hashMap = new HashMap<>();


        for (String player : participant) {
           /* if (hashMap.containsKey(player))
                hashMap.replace(player, hashMap.get(player) + 1);
            else
                hashMap.put(player, 1);*/

            //put() 메서드는 HashMap에 키와 값을 추가하는 메서드로 알려져 있으나 키가 이미 존재하는 경우 매핑되는 값이 변경됩니다.
            hashMap.put(player, hashMap.getOrDefault(player, 0) + 1);
        }

        for (String player : completion) {
            /*if (hashMap.containsKey(player))
                hashMap.replace(player, hashMap.get(player) - 1);*/

            hashMap.put(player, hashMap.get(player) - 1);
        }

        for (String key : hashMap.keySet()) {
            if (hashMap.get(key) != 0) {
                answer = key;
            }
        }


        return answer;
    }
}
