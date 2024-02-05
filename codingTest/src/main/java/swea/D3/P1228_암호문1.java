package swea.D3;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class P1228_암호문1 {
    static int N; //원본 암호문의 길이
    static final int T = 10; //테스트 케이스의 개수
    static List<String> codes; //원본 암호문
    static int instN; //명령어의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());

            codes = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                codes.add(st.nextToken());
            }
            instN = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                String inst = st.nextToken();
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                for (int i = 0; i < y; i++) { //y개의 숫자를 삽입
                    codes.add(x++, st.nextToken()); //앞에서부터 x의 위치 바로 다음에
                }
            }
            bw.write("#" + test_case + " ");
            for (int i = 0; i <10; i++) { //수정된 암호문의 처음 10개 항을 출력
                bw.write(codes.get(i) + " ");
            }
            bw.write('\n');


        }

        bw.flush();
        bw.close();
        br.close();
    }
}
