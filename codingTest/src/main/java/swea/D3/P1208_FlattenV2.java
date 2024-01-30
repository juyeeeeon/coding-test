package swea.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1208_FlattenV2 {
    static final int T = 10;
    static int dump; //주어진 dump 횟수
    static List<Integer> boxesH; //박스의 높이
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int test_case = 1; test_case <= T; test_case++) {
            boxesH = new ArrayList<>();
            dump = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 100; i++) {
                boxesH.add(Integer.parseInt(st.nextToken()));
            }

            for (int i = 0; i < dump; i++) {
                Integer max = Collections.max(boxesH);
                Integer min = Collections.min(boxesH);

                boxesH.remove(max);
                boxesH.remove(min);

                boxesH.add(max - 1);
                boxesH.add(min + 1);
            }

            Integer max = Collections.max(boxesH);
            Integer min = Collections.min(boxesH);

            System.out.println("#" + test_case + " " + (max - min));

        }
    }

}

