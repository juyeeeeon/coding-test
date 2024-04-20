package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 지수법칙 : a^(n+m) = a^n * a^m
 *
 * 모듈러 성질 : (a*b)%c = (a%c * b%c)%c
 */
public class P1629_곱셈 {

    public static long A, B, C;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        System.out.println(pow(A, B));
    }

    // A^exponent
    public static long pow(long A, long exponent) {
        if (exponent == 1) {
            return A % C;
        }

        long tmp = pow(A, exponent / 2);

        if (exponent % 2 == 0) {
            return tmp * tmp % C;
        } else {
            return (tmp * tmp % C) * A % C;
        }
    }

}
