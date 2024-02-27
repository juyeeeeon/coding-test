package baekjoon.구현;

import java.io.*;
import java.util.*;

public class P16236_아기상어 {
    public static int time, N, eat, size = 2;
    static int[] sharkLocation;
    public static int[][] map;
    public static boolean[][] visited;
    public static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    //상어로부터 물고기를 담는 우선순위큐(상어로부터의 거리가 가까운 순)
    public static PriorityQueue<Location> pq = new PriorityQueue<>((o1, o2) -> {
        if(o1.move == o2.move){ //물고기의 거리가 같다면
            if(o1.x == o2.x){
                return o1.y - o2.y; //가장 위에 있는 물고기
            }else{
                return o1.x - o2.x; //가장 왼쪽에 있는 물고기
            }
        }else{
            return o1.move - o2.move; //거리가 가까운 물고기
        }
    });

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        sharkLocation = new int[2]; //[0]:상어의 x좌표, [1]:상어의 y좌표

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){ //초기 상어의 위치
                    sharkLocation[0] = i;
                    sharkLocation[1] = j;
                    map[i][j] = 0;
                }
            }
        }

        while(true){
            bfs(); //상어로부터 물고기들의 거리

            if(pq.isEmpty()) break;

            Location fish = pq.poll(); //상어로부터 가장 가까운 물고기 꺼내기
            time += fish.move;
            map[fish.x][fish.y] = 0; //상어가 물고기를 먹음

            //상어의 위치 업데이트
            sharkLocation[0] = fish.x;
            sharkLocation[1] = fish.y;

            //상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가
            eat++;
            if(size == eat){
                size++;
                eat = 0;
            }

            pq.clear();
        }

        System.out.println(time);
    }

    /**
     * 상어로부터 물고기들의 거리 구하기
     * 물고기들을 우선순위큐에 넣어 상어로부터 가장 가까운 물고기순으로
     */
    public static void bfs(){
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(sharkLocation[0], sharkLocation[1], 0));

        visited = new boolean[N][N];

        while(!queue.isEmpty()){
            Location cur = queue.poll();

            for(int i = 0; i < 4; i++){
                int nx = cur.x + deltas[i][0];
                int ny = cur.y + deltas[i][1];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                if(!visited[nx][ny]){
                    if(map[nx][ny] == size || map[nx][ny] == 0){ //상어의 크기와 같거나 0이면 지나갈 수 있음
                        visited[nx][ny] = true;
                        queue.add(new Location(nx, ny, cur.move + 1)); //큐에 넣기
                    }else if(map[nx][ny] < size){ //먹을 수 있는 물고기이면
                        visited[nx][ny] = true;
                        pq.add(new Location(nx, ny, cur.move + 1)); //우선순위큐에 넣기
                    }
                }
            }
        }
    }

    public static class Location {
        int x;
        int y;
        int move;
        public Location(int x, int y, int move){
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
}
