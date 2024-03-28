package 복습.programmers;

import java.util.HashMap;
import java.util.HashSet;

public class 신고결과받기 {

    public static int[] solution(String[] id_list, String[] report, int k) {
        HashMap<String, HashSet<String>> reported = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            reported.put(id_list[i], new HashSet<>());
        }

        //받은 신고 카운팅
        for(String str : report) {
            String[] splited = str.split(" ");
            String sender = splited[0];
            String receiver = splited[1];

            HashSet<String> tmp = reported.get(receiver);
            tmp.add(sender);
            reported.put(receiver, tmp);
        }

        HashMap<String, Integer> results = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            results.put(id_list[i], 0);
        }

        for (HashSet<String> sender : reported.values()) {
            if (sender.size() >= k) {
                for(String str : sender){
                    results.put(str, results.get(str) + 1);
                }
            }
        }

        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            answer[i] = results.get(id_list[i]);
        }

        return answer;
    }
}
