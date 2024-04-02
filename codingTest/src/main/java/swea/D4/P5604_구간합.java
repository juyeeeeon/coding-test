package swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5604_구간합 {

        static long[] memo;
        static int[] sum; // 1 ~ i 까지의 합

        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.parseInt(br.readLine());

            sum = new int[10];
            sum[1] = 1;
            for(int i=2;i<10;i++) sum[i] = i + sum[i-1];

            memo = new long[16];
            memo[1] = sum[9];
            for (int i = 2; i <= 15; i++) {
                memo[i] = memo[1] * (long) Math.pow(10, i - 1) + memo[i - 1] * 10;
            }

            for(int t=1;t<=T;t++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                long a = Long.parseLong(st.nextToken());
                long b = Long.parseLong(st.nextToken());

                System.out.println("#" + t + " " + (solve(b) - solve(a - 1)));
            }
        }

        public static long solve(long num) {
            if(num<0) return 0;
            if(num<10) return sum[(int)num];

            String str = String.valueOf(num);
            int z = str.length() - 1;
            int x = str.charAt(0) - '0';
            long y = Long.parseLong(str.substring(1));

            return sum[x - 1] * (long) Math.pow(10, z) + x * memo[z] + x * (y + 1) + solve(y);
        }

}
