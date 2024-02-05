package baekjoon.스택과큐;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class P17298_오큰수 {
    static int N;
    static int[] arr;
    static Stack<Integer> stk = new Stack<>();
    static ArrayList<Integer> result = new ArrayList<>(); //결과를 담을 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N - 1; i >= 0; i--) { //arr의 요소값을 뒤에서부터 접근
            boolean findO = false; //오큰수를 찾았는지
            while (!stk.isEmpty()) {
                if (arr[i] < stk.peek()) { //오큰수이면
                    result.add(stk.peek()); //결과 배열에 저장
                    findO = true;
                    break;
                } else {//오큰수가 아니면
                    stk.pop(); // stack에서 제거. 왜냐하면 앞으로 볼 arr의 요소는 지금 arr의 요소값보다 큰 수 중에서 오큰수를 구하기 때문
                }
            }

            if (!findO) result.add(-1); //오큰수가 없으면

            stk.push(arr[i]);
        }

        for (int i = result.size() - 1; i >= 0; i--) { //결과를 담은 배열을 거꾸로 출력
            bw.write(result.get(i) + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
