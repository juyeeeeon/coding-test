package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P17471_게리맨더링2 {
    static int N, answer, sumA, sumB;
    static int[] population;
    static boolean[] visited;
    static ArrayList<Integer> A, B;
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        population = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
           population[i] = Integer.parseInt(st.nextToken());
        }


        arr = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = new ArrayList<>();
        }


        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                arr[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        A = new ArrayList<>();
        B = new ArrayList<>();
        sumA = sumB = 0;
        answer = Integer.MAX_VALUE;
        divide(1);

        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    private static void divide(int city) {
        if (city == N + 1) { //모든 도시가 진영이 정해졌을 때
            if (A.size() == N || B.size() == N) return; //하나의 진영으로만 이루어졌으면

            if (isConnected()) { //그게 아니고 두 진영이 연결되어 있다면
                answer = Math.min(answer, Math.abs(sumA - sumB));
            }

            return;
        }

        A.add(city);
        sumA += population[city];
        divide(city + 1);
        A.remove(A.size() - 1);
        sumA -= population[city];

        B.add(city);
        sumB += population[city];
        divide(city + 1);
        B.remove(B.size() - 1);
        sumB -= population[city];
    }

    private static boolean isConnected() {
        visited = new boolean[N + 1];
        bfs(A);
        bfs(B);

        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) return false;
        }
        return true;


    }

    private static void bfs(ArrayList<Integer> region) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(region.get(0));
        visited[region.get(0)] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Integer next : arr[cur]) {
                if (visited[next] || !region.contains(next)) continue;
                queue.add(next);
                visited[next] = true;
            }
        }
    }

}
