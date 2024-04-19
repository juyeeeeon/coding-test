package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P8458_원점으로집합 {
    static int T, N, max, sum, move;
    static boolean possible;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i] = Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(Integer.parseInt(st.nextToken()));
            }

            max = 0;
            possible = true;
            for (int i = 0; i < N - 1; i++) {
                if (arr[i] % 2 != arr[i + 1] % 2) {
                    possible = false;
                    break;
                }

                max = Math.max(max, arr[i]);
            }

            if (!possible) {
                System.out.println("#" + test_case + " " + -1);
                continue;
            }

            max = Math.max(max, arr[N - 1]);

            sum = 0;
            move = 0;
            while (true) {
                sum += move;

                //원점에 도착하여 남은 움직임으로 주위를 왕복운동가능한지(i번째 움직임에서 각 점은 상하좌우로 i만큼의 거리를 반드시 이동해야 한다.)
                if (sum >= max && (sum - max) % 2 == 0) {
                    System.out.println("#" + test_case + " " + move); //!!!!!!!!몇번째 움직임인지. 문제 잘 읽기
                    break;
                }
                move++;
            }
        }
    }
}
