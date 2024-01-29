package swea.D2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class P1204_1일차_최빈수구하기 {
    static HashMap<Integer, Integer> hm;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;


        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            br.readLine();

            hm = new HashMap<>();

            st = new StringTokenizer(br.readLine());

            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                if (hm.get(num) == null) {
                    hm.put(num, 1);
                } else {
                    Integer val = hm.get(num);
                    val++;
                    hm.put(num, val);
                }
            }

            ArrayList<Integer> keySet = new ArrayList<>(hm.keySet());
            Collections.reverse(keySet);

            Integer max = findAnswer(keySet);

            bw.write("#" + test_case + " " + max + "\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }


    private static Integer findAnswer(ArrayList<Integer> keySet) {
        Integer max = Collections.max(hm.values());
        for (Integer key : keySet) {
            if (hm.get(key).equals(max)) {
                return key;
            }
        }
        return null;
    }
}
