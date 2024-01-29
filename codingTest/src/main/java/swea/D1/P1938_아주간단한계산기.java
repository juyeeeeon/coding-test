package swea.D1;

import java.util.Scanner;

public class P1938_아주간단한계산기 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);

        int input1 = sc.nextInt();
        int input2 = sc.nextInt();

        System.out.println(input1 + input2);
        System.out.println(input1 - input2);
        System.out.println(input1 * input2);
        System.out.println(input1 / input2);
    }
}
