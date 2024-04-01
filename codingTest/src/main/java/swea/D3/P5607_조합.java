package swea.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5607_조합 {
    static final long MOD = 1234567891;
    static int T, N, R;
    static long[] factorial;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        factorial = new long[1000001];
        factorial[0] = 1;
        for (int i = 1; i < 1000001; i++) {
            factorial[i] = factorial[i - 1] * i % MOD;
        }

        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            long up = factorial[N];
            long down = (factorial[N - R] * factorial[R]) % MOD;
            long refactoredDown = pow(down, MOD - 2);

            System.out.println("#" + test_case + " " + (up * refactoredDown) % MOD);
        }
    }

    private static long pow(long a, long N) {// a의 n승 구하기 최적화방법
        if(N==0) return 1;
        if(N==1) return a;

        if(N%2==0) {
            long temp = pow(a, N / 2);
            return (temp * temp) % MOD;
        }

        long temp = pow(a, N - 1) % MOD;

        return (temp * a) % MOD;
    }

}
