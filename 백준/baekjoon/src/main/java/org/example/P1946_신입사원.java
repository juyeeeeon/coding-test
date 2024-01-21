package org.example;

import java.io.*;
import java.util.StringTokenizer;

public class P1946_신입사원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int cnt;
        int[] orders;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            cnt = 0;
            int N = Integer.parseInt(br.readLine());

            orders = new int[N+1];
            for (int j = 1; j < N+1; j++) {
                st = new StringTokenizer(br.readLine());
                int s1 = Integer.parseInt(st.nextToken());
                int s2 = Integer.parseInt(st.nextToken());

                orders[s1] = s2;
            }

            int min = orders[1];
            
            for (int j = 1; j < N + 1; j++) {
                if (orders[j] <= min) {
                    min = orders[j];
                    cnt++;
                }
            }

            bw.write(Integer.toString(cnt) + '\n');

        }

        bw.flush();
        bw.close();
        br.close();
    }
}
