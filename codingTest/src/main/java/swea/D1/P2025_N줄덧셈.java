package swea.D1;

import java.util.Scanner;

public class P2025_N줄덧셈 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int result = 0;

        for(int i=1; i<=N; i++){
            result += i;
        }

        System.out.println(result);
    }
}
