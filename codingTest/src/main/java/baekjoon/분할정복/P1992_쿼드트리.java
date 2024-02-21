package baekjoon.분할정복;

import java.io.*;

public class P1992_쿼드트리 {
    static int N;
    static char[][] map;
    static BufferedReader br;
    static BufferedWriter bw;
    
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        DFS(0, 0, N);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void DFS(int r, int c, int n) throws IOException {
        //nXn 영역이 모두 같은 수인지 확인
        int sum = getSumNxN(r, c, n);
        if (sum == n * n) { //nXn 영역이 모두 1이라면
            bw.write(Integer.toString(1));
            return;
        } else if (sum == 0) { //nXn 영역이 모두 0이라면
            bw.write(Integer.toString(0));
            return;
        } 
        
        //nXn 영역이 모두 1, 모두 0이 아니라면
        bw.write("(");
        DFS(r, c, n / 2); //왼쪽 위
        DFS(r, c + n / 2, n / 2); //오른쪽 위
        DFS(r + n / 2, c, n / 2); //왼쪽 아래
        DFS(r + n / 2, c + n / 2, n / 2); //오른쪽 아래
        bw.write(")");
    }

    private static int getSumNxN(int r, int c, int n) {
        int sum = 0;
        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                sum += map[i][j] - '0';
            }
        }
        return sum;
    }
}
