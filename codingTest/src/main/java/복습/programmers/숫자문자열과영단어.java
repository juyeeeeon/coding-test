package 복습.programmers;

public class 숫자문자열과영단어 {
    static String[] map = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public int solution(String s) {
        for (int i = 0; i < 10; i++) {
            s = s.replaceAll(map[i], Integer.toString(i));
        }

        return Integer.parseInt(s);
    }
}
