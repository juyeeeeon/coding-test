package baekjoon.슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P12891_DNA비밀번호 {
    static int S; //DNA 문자열 길이
    static int P; //비밀번호 길이
    static String DNAs; //주어진 DNA 문자열
    static Map<String, Integer> dnaCnt; //포함되어야할 DNA의 최소 개수
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        DNAs = br.readLine();
        dnaCnt = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        dnaCnt.put("A", Integer.parseInt(st.nextToken()));
        dnaCnt.put("C", Integer.parseInt(st.nextToken()));
        dnaCnt.put("G", Integer.parseInt(st.nextToken()));
        dnaCnt.put("T", Integer.parseInt(st.nextToken()));


        //슬라이딩 윈도우 초기화
        for (int i = 0; i < P; i++) {
            String key = DNAs.substring(i, i + 1);
            dnaCnt.put(key, dnaCnt.get(key)-1);
        }
        if (validPassword(dnaCnt)) result++;

        for (int i = P; i < S; i++) {
            int prevIdx = i - P;

            //슬라이딩 윈도우를 벗어난 값의 최소개수를 다시 하나 증가
            String prev = DNAs.substring(prevIdx, prevIdx + 1);
            dnaCnt.put(prev, dnaCnt.get(prev) + 1);

            //슬라이딩 윈도우로 새로 들어온 값의 최소개수를 하나 감소
            String newStr = DNAs.substring(i, i + 1);
            dnaCnt.put(newStr, dnaCnt.get(newStr) - 1);

            if (validPassword(dnaCnt)) result++;
        }

        System.out.println(result);

    }

    /**
     * 포함되어야 할 DNA의 최소 개수 이상일 때
     */
    public static boolean validPassword(Map<String, Integer> hm) {
        for (Integer value : hm.values()) {
            if (value > 0) return false;
        }
        return true;
    }
}
