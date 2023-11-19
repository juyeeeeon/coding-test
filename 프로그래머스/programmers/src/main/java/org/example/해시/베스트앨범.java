package org.example.해시;

import java.util.ArrayList;
import java.util.HashMap;

public class 베스트앨범 {
    public static void main(String[] args) {
        int[] solution = solution(new String[]{"classic", "pop", "classic", "classic", "pop"},
                new int[]{500, 600, 150, 800, 2500});

        for (int i : solution) {
            System.out.println(i);
        }
    }

    public static int[] solution(String[] genres, int[] plays) {

        HashMap<String, Integer> hm = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            hm.put(genres[i], hm.getOrDefault(genres[i], 0) + plays[i]);
        }

        //많이 재생된 장르
        String maxGenre = "";
        ArrayList<String> genres_reverseOrder = new ArrayList<>();

        while (!hm.isEmpty()) {
            int maxPlay = 0;
            for (String genre : hm.keySet()) {
                if (hm.get(genre) > maxPlay) {
                    maxPlay = hm.get(genre);
                    maxGenre = genre;
                }
            }
            genres_reverseOrder.add(maxGenre);
            hm.remove(maxGenre);
        }

        //장르 내 많이 재생된 노래의 인덱스를 저장
        ArrayList<Integer> result = new ArrayList<>();

        for (String genre : genres_reverseOrder) {
            ArrayList<Integer> num = new ArrayList<>();

            for (int i = 0; i < genres.length; i++) {
                if (genres[i].equals(genre)) {
                    num.add(i);
                }
            }

            HashMap<Integer, Integer> numPlay = new HashMap<>();
            for (Integer idx : num) {
                numPlay.put(idx, plays[idx]);
            }


            int cnt = 2;
            while (!numPlay.isEmpty() && cnt-- != 0) {
                int maxi = -1;
                int maxp = -1;
                for (Integer idx : numPlay.keySet()) {
                    if (numPlay.get(idx) > maxp) {
                        maxi = idx;
                        maxp = numPlay.get(idx);
                    }
                }

                result.add(maxi);
                numPlay.remove(maxi);
            }

        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}
