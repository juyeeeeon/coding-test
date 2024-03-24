package programmers;

public class 숫자문자열과영단어 {
    public int solution(String s) {
        StringBuffer answer = new StringBuffer();
        StringBuffer sb = new StringBuffer();

        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] >= '0' && charArray[i] <= '9') {
                answer.append(charArray[i]);
            } else {
                sb.append(charArray[i]);
                if (sb.toString().equals("zero")) {
                    answer.append("0");
                    sb = new StringBuffer();
                } else if (sb.toString().equals("one")) {
                    answer.append("1");
                    sb = new StringBuffer();
                }else if (sb.toString().equals("two")) {
                    answer.append("2");
                    sb = new StringBuffer();
                }else if (sb.toString().equals("three")) {
                    answer.append("3");
                    sb = new StringBuffer();
                } else if (sb.toString().equals("four")) {
                    answer.append("4");
                    sb = new StringBuffer();
                }else if (sb.toString().equals("five")) {
                    answer.append("5");
                    sb = new StringBuffer();
                }else if (sb.toString().equals("six")) {
                    answer.append("6");
                    sb = new StringBuffer();
                }else if (sb.toString().equals("seven")) {
                    answer.append("7");
                    sb = new StringBuffer();
                }else if (sb.toString().equals("eight")) {
                    answer.append("8");
                    sb = new StringBuffer();
                }else if (sb.toString().equals("nine")) {
                    answer.append("9");
                    sb = new StringBuffer();
                }
            }
        }

        return Integer.parseInt(answer.toString());

    }
}
