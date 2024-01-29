package swea.D1;

import java.util.Scanner;

public class P2046_스탬프찍기 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();

        //자바11이상
//        System.out.println("#".repeat(num));

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            builder.append("#");
        }
        System.out.println(builder);
    }
}
