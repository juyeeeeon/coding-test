package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P5653_줄기세포배양 {
    static int T, N, M, K;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상, 하, 좌, 우
    static HashMap<int[], Integer> hm;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //세로
            M = Integer.parseInt(st.nextToken()); //가로
            K = Integer.parseInt(st.nextToken()); //배양시간

            hm = new HashMap<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    hm.put(new int[]{i, j}, Integer.parseInt(st.nextToken()));
                }
            }

            while (K-- > 0) {
                for (Map.Entry<int[], Integer> entry : hm.entrySet()) {
                    int[] key = entry.getKey();
                    Integer value = entry.getValue();
                    if (--value == -1) {
                        for (int d = 0; d < deltas.length; d++) {
                            int nr = key[0] + deltas[d][0];
                            int nc = key[1] + deltas[d][1];

                            if (hm.containsKey(new int[]{nr, nc})) continue;
                            hm.put(new int[]{nr, nc}, value);
                        }
                    }

                    hm.put(key, value--);


                }
            }




        }
    }
}
