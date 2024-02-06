package swea.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P1218_괄호짝짓기 {
    static final int T = 10;
    static Stack<String> stk;
    static String[] openB = {"(", "[", "{", "<"};
    static String[] closeB = {")", "]", "}", ">"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= T; test_case++) {
            br.readLine();

            String input = br.readLine();
            stk = new Stack<>();

            for (int i = 0; i < input.length(); i++) {
                String s = input.substring(i, i + 1);
                if (isOpenBracket(s)) { //여는 괄호면 푸시
                    stk.push(s);
                    continue;
                }

                //닫는 괄호
                if (s.equals(")") && stk.peek().equals("(")) {
                    stk.pop();
                } else if (s.equals("]") && stk.peek().equals("[")) {
                    stk.pop();
                }else if (s.equals("}") && stk.peek().equals("{")){
                    stk.pop();
                } else if (s.equals(">") && stk.peek().equals("<")) {
                    stk.pop();
                } else { //짝이 아님
                    break;
                }
            }

            if (stk.isEmpty()) {
                System.out.println("#" + test_case + " " + 1);
            } else {
                System.out.println("#" + test_case + " " + 0);
            }

        }
    }

    private static boolean isOpenBracket(String s) {
        for (String str : openB) {
            if (s.equals(str)) return true;
        }
        
        return false;
    }
}
