package swea.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1225_암호생성기 {
    static final int T = 10;
    static final int N = 8;
    static Queue<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        for (int test_case = 1; test_case <= T; test_case++) {
            br.readLine();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                queue.add(Integer.parseInt(st.nextToken()));
            }
            int i = 1;
            while (true) {
                int num = queue.poll() - i;
                if (num <= 0) {
                    queue.add(0);
                    break;
                }
                queue.add(num);

                if (++i >= 6) i = 1;
            }


            System.out.print("#" + test_case + " ");
            while (!queue.isEmpty()) {
                System.out.print(queue.poll() + " ");
            }
            System.out.println();
        }
    }
}
