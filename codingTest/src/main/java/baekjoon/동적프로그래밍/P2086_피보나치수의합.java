package baekjoon.동적프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class P2086_피보나치수의합 {
    static final long MOD = 1000000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long sumA = fibonacciSum(a - 1);
        long sumB = fibonacciSum(b);

        long result = (sumB - sumA + MOD) % MOD;
        System.out.println(result);
    }

    private static long fibonacciSum(long n) {
        if (n < 0) return 0;

        long[][] F = {{1, 1}, {1, 0}};
        long[][] result = matrixPower(F, n + 2);

        return (result[0][1] - 1 + MOD) % MOD;
    }

    private static long[][] matrixPower(long[][] A, long n) {
        long[][] result = {{1, 0}, {0, 1}}; // Identity matrix

        while (n > 0) {
            if (n % 2 == 1) result = matrixMultiply(result, A);
            A = matrixMultiply(A, A);
            n /= 2;
        }

        return result;
    }

    private static long[][] matrixMultiply(long[][] A, long[][] B) {
        long[][] C = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }
}
