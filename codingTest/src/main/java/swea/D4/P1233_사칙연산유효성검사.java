package swea.D4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1233_사칙연산유효성검사 {
    static int N;
    static String[] tree;
    static int T = 10; //테스트 케이스 개수
    static String[] operator = {"+", "-", "*", "/"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            tree = new String[N + 1];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                tree[Integer.parseInt(st.nextToken())] = st.nextToken();
            }

            boolean validFlag = true;
            for (int i = 1; i <= N; i++) { //모든 노드들을 돌면서
                validFlag = isValid(i); //해당 노드가 유효한지 검사
                if (!validFlag) {//유효하지 않다면
                    bw.write("#" + test_case + " " + 0 + '\n'); //0 출력
                    break;
                }
            }

            if (validFlag) { //유효하다면
                bw.write("#" + test_case + " " + 1 + '\n'); //1 출력
            }
        }

        bw.flush();
        bw.close();
        br.close();

    }

    private static boolean isValid(int i) {
        if (hasChildren(i)) { //자식이 있다면 tree[i]는 무조건 operator여야 함
            if (Arrays.stream(operator).noneMatch(s -> s.equals(tree[i]))) {
                return false; //operator가 아니면
            } else {
                return true; //operator면
            }
        } else { //leaf 노드라면 무조건 operand여야 함
            if (Arrays.stream(operator).anyMatch(s -> s.equals(tree[i]))) {
                return false; //operator면
            } else {
                return true; //operand면
            }
        }

    }

    private static boolean hasChildren(int i) {
        int leftChild = 2*i;
        int rightChild = 2 * i + 1;

        if (leftChild <= N && rightChild <= N) {
            return true;
        }
        return false;
    }
}
