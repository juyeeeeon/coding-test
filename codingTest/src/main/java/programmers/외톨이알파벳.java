package programmers;

import java.util.Arrays;
import java.util.HashSet;

public class 외톨이알파벳 {
    public String solution(String input_string) {
        String answer = "";

        //알파벳의 갯수 담기
        int[] countAlphabet = new int[26]; //[0]:A [1]:B

        char[] charArray = input_string.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            countAlphabet[charArray[i]-'a']++;
        }

        HashSet<Character> lonelyAlpha = new HashSet<>();

        //알파벳이 연속인지 -> 아니면 외톨이 알파벳
        int idx = 0;
        while (idx < charArray.length) {
            int cnt = countAlphabet[charArray[idx] - 'a'];

            if (cnt == 1) {
                idx++;
                continue;
            }

            int i;
            for (i = idx+1; i < idx + cnt; i++) {
                if (i >= charArray.length) { //charArray의 범위를 넘어섰다면 -> 이미 앞에서 외톨이 알파벳 담았을 것임
                    break;
                }

                if (charArray[i] != charArray[idx]) {
                    lonelyAlpha.add(charArray[idx]);
                    break;
                }
            }

            idx = i;
        }

        //loneyAlpha을 알파벳순으로 이어 붙인 문자열 리턴하기
        char[] arr = new char[lonelyAlpha.size()];

        int c = 0;
        for (Character h : lonelyAlpha) {
            arr[c++] = h;
        }

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (char c1 : arr) {
            sb.append(c1);
        }

        if (sb.toString().isEmpty()) return "N";
        return sb.toString();
    }
}
