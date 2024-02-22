package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P17471_게리맨더링 {

    public static int n;
    public static int[] population;

    public static ArrayList<Integer>[] arr;
    public static boolean[] selected, visited;
    public static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        population = new int[n + 1];

        arr = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) arr[i] = new ArrayList<>();

        selected = new boolean[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for(int j = 0; j < num; j++) {
                int t = Integer.parseInt(st.nextToken());
                arr[i].add(t);
            }
        }

        divide(1);

        if(ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }

    //selected가 true인 선거구와 false인 선거구로 나눔
    public static void divide(int idx) {
        if(idx == n + 1) { //모든 지역을 선거구로 나눴을 때
            ArrayList<Integer> listA = new ArrayList<>();
            ArrayList<Integer> listB = new ArrayList<>();

            int asum = 0;
            int bsum = 0;

            for(int i = 1; i <= n; i++) {
                if(selected[i]) {
                    listA.add(i);
                    asum += population[i];
                }
                else {
                    listB.add(i);
                    bsum += population[i];
                }
            }
            if(listA.isEmpty() || listB.isEmpty()) return; //모든 지역이 하나의 선거구일때
            if(!allLinked(listA) || !allLinked(listB)) return; //각각의 선거구가 연결되지 않았을 때

            int dif = Math.abs(asum - bsum);
            ans = Math.min(ans, dif);
            return;
        }

        selected[idx] = true;
        divide(idx + 1);
        selected[idx] = false;
        divide(idx + 1);
    }

    //선거구가 연결되었는지 확인
    public static boolean allLinked(ArrayList<Integer> list) {
        int node = list.get(0);
        visited = new boolean[n + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;

        int cnt = 1;
        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for (int v : arr[cur]) {
                if (list.contains(v) && !visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                    cnt++;
                }
            }
        }

        if(cnt == list.size()) return true; //모두 연결되어 있으면

        return false; //연결되어 있지 않으면
    }
}

