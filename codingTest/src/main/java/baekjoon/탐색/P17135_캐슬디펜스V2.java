package baekjoon.탐색;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 궁수의 위치 조합(조합마다 초기 적의 수와 초기 맵으로 리셋해줘야함) => 타겟 정하기 => 다음 라운드로 맵 업데이트 => 타겟 업데이트 => ...
 */
public class P17135_캐슬디펜스V2 {
    static int N, M, D;
    static int maxKill = Integer.MIN_VALUE;
    static int initEnemy = 0; //초기 적의 수
    static int enemy = 0;
    static int [][] archers; //[][0]:궁수의 x좌표, [][1]:궁수의 y좌표
    static int [][] targets; //[][0]:타겟의 x좌표, [][1]:타겟의 y좌표
    static int[][] map, testMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        archers = new int[3][2];

        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) initEnemy++;
            }
        }

        combination(0, 0);

        System.out.println(maxKill);
    }

    /**
     * 궁수 위치의 모든 조합 구하기
     * @param start
     * @param cnt : 궁수의 수
     */
    private static void combination(int start, int cnt) {

        if (cnt == 3) {//궁수 3명 위치 정해지면
            testMapInit(); //testMap을 map으로 초기화
            enemy = initEnemy; //적의 수를 초기 적의 수로 초기화
            attack(); //궁수 각각의 target 설정
            return;
        }

        for (int c = start; c < M; c++) {
            archers[cnt][0] = N;
            archers[cnt][1] = c;
            combination(c + 1, cnt + 1);
        }
    }

    private static void attack() {
        int kill = 0;

        while (enemy > 0) { //매 턴마다
            setTarget(); //타겟 설정
            for (int t = 0; t < targets.length; t++) {
                if (targets[t][0] == -1) continue; //타겟의 좌표가 초기좌표면(=타겟이 없으면) continue
                if (testMap[targets[t][0]][targets[t][1]] == 0) continue; //타겟이 존재하지만 다른 궁수와 타겟이 겹치면(=이미 다른 궁수가 죽였다면) continue

                testMap[targets[t][0]][targets[t][1]] = 0; //타겟을 죽이고 testMap의 해당 좌표를 0으로 업데이트
                kill++; //킬 수 증가
            }

            nextTestMap(); //다음 턴의 testMap 업데이트
            enemy = countEnemy(); //적의 수 업데이트
        }

        //모든 턴이 끝나면 최대 kill수 업데이트
        maxKill = Math.max(maxKill, kill);
    }

    /**
     * 궁수 각각의 target 설정
     */
    private static void setTarget() {
        targetInit(); //target들의 x, y좌표를 -1로 초기화

        //각각의 궁수마다 타겟을 설정
        for (int i = 0; i < archers.length; i++) {
            int minDistance = Integer.MAX_VALUE;

            for (int c = 0; c < M; c++) {//왼쪽에서부터 오른쪽으로
                for (int r = N - 1; r >= 0; r--) {//아래서부터 위로
                    if (testMap[r][c] == 0) continue; //적이 위치하지 않으면 continue
                    int distance = getDistance(archers[i][0], archers[i][1], r, c); //궁수와 적의 거리
                    if (distance > D) continue; // 궁수와 적의 거리가 D보다 크면 continue
                    if (distance >= minDistance) continue; //궁수와 적의 거리가 최소거리보다 크면 continue
                    minDistance = distance;
                    targets[i][0] = r; //궁수i의 타겟 좌표 저장
                    targets[i][1] = c;
                }
            }
        }
    }

    /**
     * 다음 턴의 testMap 업데이트
     */
    private static void nextTestMap() {
        for (int r = N-2; r >= 0; r--) {
            for (int c = 0; c < M; c++) {
                testMap[r+1][c] = testMap[r][c];
            }
        }

        for (int c = 0; c < M; c++) {
            testMap[0][c] = 0;
        }
    }

    /**
     *
     * @return : 존재하는 적의 수
     */
    private static int countEnemy() {
        int cnt = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (testMap[r][c] == 1) cnt++;
            }
        }

        return cnt;
    }

    /**
     *
     * @return : 궁수와 타겟 사이의 거리
     */
    private static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    /**
     * testMap을 map으로 초기화
     */
    private static void testMapInit() {
        testMap = new int[N][M];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                testMap[r][c] = map[r][c];
            }
        }
    }

    /**
     * target들의 x, y좌표를 -1로 초기화
     */
    private static void targetInit() {
        targets = new int[3][2];
        for (int i = 0; i < targets.length; i++) {
            Arrays.fill(targets[i], -1);
        }
    }
}