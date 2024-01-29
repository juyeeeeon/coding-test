package swea.D1;

import java.util.Scanner;

public class P2068_최대수구하기 {
    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int max = sc.nextInt();
            for(int i=1; i<10; i++){
                int tmp = sc.nextInt();
                if(tmp>max){
                    max = tmp;
                }
            }
            System.out.println("#"+test_case+" "+max);
        }
    }
}
