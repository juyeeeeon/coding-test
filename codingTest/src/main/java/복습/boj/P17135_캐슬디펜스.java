package 복습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P17135_캐슬디펜스 {
    static int N, M, D;
    static int kill = 0;
    static int maxKill = Integer.MIN_VALUE;
    static int[][] map;
    static int[][] originalMap;
    static ArrayList<Archer> archers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        archers = new ArrayList<>();
        map = new int[N][M];
        originalMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                originalMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(maxKill);

    }

    private static void dfs(int cnt, int start) {
        if (cnt == 3) { //세명의 궁수 자리가 정해지면
            //궁수의 행, 죽인 적의 수, 맵 초기화
            for (Archer archer : archers) {
                archer.r = N;
            }
            kill = 0;
            map = copyOriginalMap();


            attack(); // 공격시작

            maxKill = Math.max(maxKill, kill); // 최대 죽인 적의 수 업데이트

            return;
        }

        for (int i = start; i < M; i++) {
            archers.add(new Archer(i)); //열이 i인 궁수 담기
            dfs(cnt + 1, i + 1);
            archers.remove(archers.size() - 1); //원복
        }
    }

    private static int[][] copyOriginalMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = originalMap[i][j];
            }
        }

        return map;
    }

    /**
     * 적 공격
     */
    private static void attack() {
        for (int round = N-1; round >=0 ; round--) {
            //라운드마다 각 궁수의 타겟을 담는 우선순위큐 초기화
            for (Archer archer : archers) {
                archer.targets.clear();
            }
            setTarget(round);

            for (Archer archer : archers) {
                if (archer.targets.isEmpty()) continue; //궁수의 타겟이 없다면
                Target1 target = archer.targets.poll(); //궁수의 타겟
                if (map[target.r][target.c] == 1) { //각 궁수의 타겟이 겹칠 수도 있으므로
                    map[target.r][target.c] = 0;
                    kill++;
                }
            }

            //한 라운드가 끝나면 적이 아래로 한칸 이동 = 궁수가 한 칸 위로
            for (Archer archer : archers) {
                archer.r -= 1;
            }
        }
    }

    /**
     * 궁수마다 타겟을 정함
     * @param row 매 라운드마다 고려해야할 로우 0 ~ row
     */
    private static void setTarget(int row) {

        for (int i = row; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue; //적이 없다면
                for (Archer archer : archers) { //적이 있으면 각 궁수마다
                    int distance = getDistance(archer.r, archer.c, i, j); //적과 궁수의 거리 측정
                    if (distance <= D) archer.targets.add(new Target1(i, j, distance)); //사정거리 안이면 타겟우선순위큐에 저장
                }
            }
        }
    }

    private static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    private static class Archer{
        int r;
        int c;
        PriorityQueue<Target1> targets; //궁수의 타겟을 담는 우선순위 큐

        public Archer(int c) {
            this.c = c;
            this.targets = new PriorityQueue<>();
        }

    }
    private static class Target1 implements Comparable<Target1>{
        int r;
        int c;
        int d;

        public Target1(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Target1 o) {
            if (this.d == o.d) return this.c - o.c; //거리가 같다면 가장 왼쪽에 있는 적부터
            else return this.d - o.d; //가장 가까운 적부터
        }
    }
}
