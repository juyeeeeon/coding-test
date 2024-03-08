package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P5653_줄기세포배양 {
    static int N, M, K;
    static PriorityQueue<Cell> activeCells;
    static Queue<Cell> cells;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상, 하, 좌, 우

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            activeCells = new PriorityQueue<>((o1, o2) -> o2.lifetime - o1.lifetime);
            cells = new ArrayDeque<>(); //모든 cell을 담는 큐
            visited = new boolean[N + K + 1][M + K + 1];

            for (int r = K / 2; r < K / 2 + N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = K / 2; c < K / 2 + M; c++) {
                    int value = Integer.parseInt(st.nextToken());
                    if (value > 0) {
                        visited[r][c] = true;
                        cells.add(new Cell(r, c, value));
                    }
                }
            }

            //------------INPUT END---------------
            solve();
            System.out.println("#"+test_case+" "+ cells.size());

        }
    }

    private static void solve() {
        while(K-->0) {
            int size = cells.size();

            for (int s = 0; s < size; s++) { //size 인라인하면 안됌!
                Cell cell = cells.poll();

                //활성상태면 pq에 넣기
                if(cell.status==1) activeCells.add(cell);

                cell.step();

                if(cell.status==2) continue;

                cells.add(cell);
            }
            //세포 번식
            while(!activeCells.isEmpty()) {
                Cell cell = activeCells.poll();

                for (int d = 0; d < deltas.length; d++) {
                    int nr = cell.r + deltas[d][0];
                    int nc = cell.c + deltas[d][1];

                    //이미 방문 처리 된 경우는, 이전에 배양 된 자리거나.
                    //더 생명력이 높은 세포가 배양 되어 있는 경우
                    if(visited[nr][nc]) continue;
                    visited[nr][nc]=true;

                    cells.add(new Cell(nr, nc, cell.lifetime));
                }
            }
        }
    }

    static class Cell{
        int r, c; // 세포 위치
        int status; // 상태 정보 (0:비활성, 1:활성세포, 2:죽은세포)
        int lifetime; // 생명력 수치
        int time; // 상태 변경 용

        Cell(int r, int c, int lifetime) {
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



