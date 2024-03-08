package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
모든 세포를 담는 큐와 이 중에서 활성화된 세포를 담는 우선순위큐가 필요
시간이 흐를 때마다 생명력 수치 변경 -> 활성상태도 변경
K시간 이후 세포를 담은 큐의 사이즈가 비활성+활성 상태 수
 */
public class P5653_줄기세포배양 {
    static int T, N, M, K;
    static PriorityQueue<Cell> activeCells; //활성상태인 cell을 담는 큐
    static Queue<Cell> cells; //모든 cell을 담는 큐
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상, 하, 좌, 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            activeCells = new PriorityQueue<>((o1, o2) -> o2.lifetime - o1.lifetime);
            cells = new ArrayDeque<>();
            visited = new boolean[N + K + 1][M + K + 1];

            for (int r = K/2; r < K/2 + N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = K/2; c < K/2 + M; c++) {
                    int value = Integer.parseInt(st.nextToken());
                    if (value > 0) {
                        visited[r][c] = true;
                        cells.add(new Cell(r, c, value));
                    }
                }
            }

            while (K-- > 0) {
                int size = cells.size();
                for (int s = 0; s < size; s++) { //foreach쓰면 안됌! size 인라인하면 안됌!
                    Cell cell = cells.poll();

                    // cell이 활성상태면 pq에 넣기
                    if (cell.status == 1) activeCells.add(cell);
                    cell.step();
                    if (cell.status == 2) continue; //죽은 세포는 담지 큐에 다시 담지 않음

                    cells.add(cell);
                }

                while(!activeCells.isEmpty()) {
                    Cell activeCell = activeCells.poll();

                    for (int d = 0; d < deltas.length; d++) {
                        int nr = activeCell.r + deltas[d][0];
                        int nc = activeCell.c + deltas[d][1];

                        if (visited[nr][nc]) continue;
                        visited[nr][nc] = true;

                        cells.add(new Cell(nr, nc, activeCell.lifetime));
                    }
                }
            }

            System.out.println("#" + test_case + " " + cells.size());
        }
    }

    private static class Cell {
        int r;
        int c;
        int status; //상태 정보 (0:비활성, 1:활성세포, 2:죽은세포)
        int lifetime; //생명력 수치
        int time;

        public Cell(int r, int c, int lifetime) {
            this.r = r;
            this.c = c;
            this.status = 0;
            this.lifetime = lifetime;
            this.time = lifetime;
        }

        public void step() {
            if (this.status == 0) { //비활성 상태
                this.time--;
                if (this.time == 0) this.status = 1; //활성 상태로 변경
            }else if (this.status == 1) { //활성 상태
                this.time++;
                if (this.time == this.lifetime) this.status = 2; //죽은 세포로 변경
            }
        }
    }
}
