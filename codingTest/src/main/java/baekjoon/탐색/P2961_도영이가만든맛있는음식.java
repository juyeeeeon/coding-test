package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2961_도영이가만든맛있는음식 {
    static int N;
    static int min = Integer.MAX_VALUE;
    static int[][] ingre;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        ingre = new int[N][2]; // ingre[n][0]:신맛, ingre[n][1]:쓴맛
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ingre[i][0] = Integer.parseInt(st.nextToken());
            ingre[i][1] = Integer.parseInt(st.nextToken());
        }

        //바이너리카운팅을 이용하여 모든 부분집합을 구함
        generateSubset(N);

        System.out.println(min);


    }

    //바이너리카운팅
    private static void generateSubset(int n) {
        for (int bits = 1; bits < (1 << n); bits++) { //부분집합의 개수는 2^n(= 1<<n)가지, 재료를 최소 1개 이상 사용해야하므로 1부터(ex> n = 3일 때 000을 제외한 001부터)
            int sin = 1; //신맛
            int ssyn = 0; //쓴맛
            for (int jaritsu = 0; jaritsu < n; jaritsu++) { //부분집합은 n개의 bits로 구분될 수 있음
                if ((bits & (1 << jaritsu)) != 0) {
                    sin *= ingre[jaritsu][0];
                    ssyn += ingre[jaritsu][1];
                }
            }
            min = Math.min(Math.abs(sin - ssyn), min);
        }
    }
}
