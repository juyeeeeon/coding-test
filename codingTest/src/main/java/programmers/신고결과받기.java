package programmers;

import java.util.HashMap;
import java.util.HashSet;

public class 신고결과받기 {
        public static int[] solution(String[] id_list, String[] report, int k) {
            //1. 중복 제거
            HashMap<String, HashSet<String>> map = new HashMap<>(); //key:신고당한사람 value:신고한사람
            for (String id : id_list) {
                map.put(id, new HashSet<>());
            }

            //2.몇 번 신고 당했는지 카운팅
            //  -> k번 이상 신고 당한 사람은 정지
            for (String pair : report) {
                String[] split = pair.split(" ");
                String sender = split[0];
                String receiver = split[1];

                HashSet<String> hs = map.get(receiver);
                hs.add(sender);
                map.put(receiver, hs);
            }

            HashMap<String, Integer> receiveMail = new HashMap<>();
            for (String id : id_list) {
                receiveMail.put(id, 0);
            }

            for (String key : map.keySet()) {
                HashSet<String> sender = map.get(key);
                if (sender.size() >= k) {
                    for (String send : sender) {
                        receiveMail.put(send, receiveMail.get(send) + 1);
                    }
                }
            }

            int[] answer = new int[id_list.length];
            for (int i = 0; i < id_list.length; i++) {
                answer[i] = receiveMail.get(id_list[i]);
            }

            return answer;

        }
}
