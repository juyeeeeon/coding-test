package swea.D1;

import java.util.Scanner;

public class P1933_간단한N의약수 {
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for(int i=1; i<=N; i++){
            if(N%i == 0){
                System.out.print(i+" ");
            }
        }
        System.out.println();
    }
}
