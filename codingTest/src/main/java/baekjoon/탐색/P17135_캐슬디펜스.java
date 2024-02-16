package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P17135_캐슬디펜스 {

    static int N;       // 맵의 전체 행
    static int M;       // 맵의 전체 열
    static int D;       // 궁수 공격가능거리
    static int enemy; // 적의 수
    static int[][] map, testMap; // 격자판

    // 배치 할 궁수 3명 저장 리스트
    static List<Archer> archers = new ArrayList<>();

    // 경우의 수 별로 저장할 결과 값
    static int count = 0;

    // 최종 결과 값
    static int result = 0;


    public static void main(String[] args) throws IOException {
        // 입력을 받기위한 객체
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행, 열, 궁수 공격거리 값 받아서 변수 초기화
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        // 격자판 생성 및 초기화
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 제거할 수 있는 적의 최대수 찾기
        comb(0);

        // 결과 값 출력
        System.out.println(result);
    }

    /**
     * 궁수를 배치하는 조합을 구하기 위한 재귀메서드
     * @param start : 궁수 배치할 위치 인덱스
     */
    static void comb(int start) {

        // 궁수 3명의 배치가 끝난 경우
        if (archers.size() == 3) {
            attack();
            return;
        }

        // 완전탐색을 위한 반복문
        for (int i = start; i < M; i++) {
            // 해당 위치에 궁수 배치
            archers.add(new Archer(N, i));
            // 다음 위치로 이동
            comb(i + 1);
            // 해당 위치의 궁수 제거
            archers.remove(archers.size()-1);
        }
    }

    private static void attack() {
        // 경우의 수마다 시뮬레이션을 해야하므로 맵을 복사
        copyMapToTestMap();

        // 남은 적의 수 카운트를 위한 변수
        // 초기값은 그냥 99로 줌
        enemy = 99;

        // 적이 1명 이상이면 게임 계속 진행
        while (true) {
            //적이 없으면 끝
            if (enemy == 0) break;

            // 궁수 각각의 가까운 적 찾기
            findNearestEnemy();

            // 궁수 하나씩 꺼내기
            for (Archer archer : archers) {
                // 궁수가 공격할 타켓이 정해졌다면
                if (archer.targetRow != -1) {
                    // 적을 공격해서 제거 (저장된 타켓의 좌표 값을 0으로 갱신)
                    if (testMap[archer.targetRow][archer.targetColumn] == 1) {
                        testMap[archer.targetRow][archer.targetColumn] = 0;

                        // 적을 제거한 수 1 증가
                        count++;
                    }
                }
            }
            searchEnemy();
        }

        // 테스트가 종료되고 적을 처치한 수가 여태까지 저장된 값보다 크면 값 갱신
        result = Math.max(result, count);

        // 새로운 테스트를 위해 카운트 변수 0으로 초기화
        count = 0;
    }

    private static void copyMapToTestMap() {
        testMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                testMap[i][j] = map[i][j];
            }
        }
    }

    private static void searchEnemy() {
        // 남아있는 적의 수를 카운트 하기 위해 0으로 초기화
        enemy = 0;

        // 테스트 맵 전체 탐색
        for (int j = 0; j < M; j++) {
            for (int i = N - 1; i >= 0; i--) {
                // 해당 좌표에 적이 있으면
                if (testMap[i][j] == 1) {
                    // 아래로 한칸 이동하기 위해 좌표 값 계산
                    int moveR = i + 1;

                    // 아래로 한칸 이동 했을 때 맵을 벗어나면
                    // 맵을 벗어나서 적은 사라짐 (해당 좌표 빈칸으로 갱신)
                    if (moveR >= N) testMap[i][j] = 0;
                    else {
                        // 맵을 벗어나지 않으면 적은 아래로 한칸 이동
                        // (해당 좌표 빈칸으로 갱신, 한칸 아래 좌표 1로 갱신)
                        testMap[i][j] = 0;
                        testMap[moveR][j] = 1;

                        // 적이 남아있으므로 카운트변수 1 증가
                        enemy++;
                    }
                }
            }
        }
    }

    private static void findNearestEnemy() {
        for (Archer archer : archers) {

            // 궁수가 공격할 대상 위치 초기화
            archer.targetRow = -1;
            archer.targetColumn = -1;

            // 궁수와 가장 가까운 적을 찾기 위해 사용할 변수
            int minDistance = Integer.MAX_VALUE;

            // 궁수와 가장 가까운 적 찾기
            // 가장 왼쪽 열부터 오른쪽 열로
            for (int c = 0; c < M; c++) {
                // 성과 가까운 행부터 가장 먼 행으로
                for (int r = N-1; r >= 0; r--) {
                    // 해당 위치에 적이 있으면
                    if (testMap[r][c] == 1) {
                        // 적과 궁수의 거리 계산
                        int distance = getDistance(archer.row, archer.column, r, c);

                        // 계산한 값이 궁수공격가능거리보다 멀면 다음 위치탐색
                        if (distance > D) break;

                        // 현재까지 저장된 가장 가까운 적과의 거리보다 위에서 계산한 거리 값이 작으면
                        if (minDistance > distance) {
                            // 위에서 계산한 거리 값으로 변수 값 새로 저장
                            minDistance = distance;
                            // 궁수가 공격할 타켓의 위치를 현재 적의 좌표로 갱신
                            archer.targetRow = r;
                            archer.targetColumn = c;
                        }
                    }
                }
            }
        }
    }

    private static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    // 궁수 클래스
    static class Archer {
        int row;            // 궁수가 있는 행
        int column;         // 궁수가 있는 열
        int targetRow;      // 궁수가 공격할 타켓의 행
        int targetColumn;   // 궁수가 공격할 타켓의 열

        // 궁수 생성자 (초기좌표 값 필요)
        public Archer(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
