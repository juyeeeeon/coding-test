package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 위상정렬
 * 진입차수 배열을 만들어서 진입차수가 0인 것을 출력
 */
public class P2252_줄세우기 {
    static int N, M;
    static int[] inDegrees; //진입차수저장 배열
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inDegrees = new int[N + 1]; //idx 0 제외
        arr = new ArrayList[N + 1]; //idx 0 제외
        for (int i = 1; i < N + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr[s].add(e);
            inDegrees[e]++; //진입차수 증가
        }

        Queue<Integer> queue = new ArrayDeque<>();

        //진입차수가 0인 정점찾아 큐에 넣기
        for (int i = 1; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            System.out.print(poll + " ");

            for (Integer num : arr[poll]) {
                inDegrees[num]--; //진입차수 감소
                if (inDegrees[num] == 0) queue.add(num); //만약 진입차수가 0이면 큐에 넣기
            }
        }
    }

}
