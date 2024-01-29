package swea.D2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P1959_두개의숫자열 {
    public static void main(String args[]) throws Exception
    {
        ArrayList<Integer> sol;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            sol = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] A = new int[N];
            int[] B = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < A.length; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < B.length; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            if (A.length > B.length) {
                extracted(A, B, sol);
            } else {
                extracted(B, A, sol);
            }

            bw.write("#" + test_case + " " + Collections.max(sol) + '\n');
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void extracted(int[] A, int[] B, ArrayList<Integer> sol) {
        for (int i = 0; i <= A.length - B.length; i++) {
            int result = 0;
            int ptrA = i;
            int ptrB = 0;
            while (ptrA < A.length && ptrB < B.length) {
                result += A[ptrA++] * B[ptrB++];
            }
            sol.add(result);
        }
    }
}
