package baekjoon.탐색;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P11724_연결요소의개수 {
    static boolean[] visit;
    static ArrayList<Integer>[] arr;
    static int count;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visit = new boolean[N+1];

        arr = new ArrayList[N + 1];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            arr[s].add(e);
            arr[e].add(s);
        }

        for (int i = 1; i < arr.length; i++) {
            if (!visit[i]) {
                count++;
                DFS(i);
            }
        }
        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
        br.close();


    }

    private static void DFS(int i) {
        if (visit[i]) return;
        visit[i] = true;

        for (Integer node : arr[i]) {
            if (!visit[node]) {
                DFS(node);
            }
        }
    }
}
