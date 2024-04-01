package baekjoon.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * KMP
 */
public class P1786_찾기 {
    static String text;
    static String pattern;
    static int[] table;
    static List<Integer> answers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        text = br.readLine();
        pattern = br.readLine();

        table = new int[pattern.length()];

        solve();

        System.out.println(answers.size());

        for(Integer answer: answers){
            System.out.println(answer);
        }
    }

    private static void solve(){
        makeTable();

        int idx = 0;

        for(int i = 0; i< text.length(); i++){
            while(idx>0 && text.charAt(i)!= pattern.charAt(idx)){
                idx = table[idx-1];
            }

            if (text.charAt(i) == pattern.charAt(idx)) {
                if (idx == pattern.length() - 1) {
                    answers.add(i - idx + 1);
                    idx = table[idx];
                } else {
                    idx += 1;
                }
            }
        }
    }

    private static void makeTable(){

        int idx =0;

        for(int i = 1; i< pattern.length(); i++){
            while(idx>0 && pattern.charAt(i)!= pattern.charAt(idx)){
                idx = table[idx-1];
            }

            if(pattern.charAt(i)== pattern.charAt(idx)){
                idx++;
                table[i] = idx;
            }
        }
    }
}
