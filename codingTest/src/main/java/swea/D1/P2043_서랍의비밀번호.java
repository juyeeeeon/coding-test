package swea.D1;

import java.util.Scanner;

public class P2043_서랍의비밀번호 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);

        int P = sc.nextInt();
        int K = sc.nextInt();

        int count = 0;

        while(K <= P){
            count++;
            K++;
        }

        System.out.println(count);
    }
}
