package baekjoon.구현;

import java.io.*;
import java.util.*;

public class P16236_아기상어 {
    public static int time, N, eat, sharkSize = 2;
    static int[] sharkLocation;
    public static int[][] map;
    public static boolean[][] visited;
    public static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    //상어로부터 물고기를 담는 우선순위큐(상어로부터의 거리가 가까운 순)
    public static PriorityQueue<Location> fishesFromShark;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        sharkLocation = new int[2]; //[0]:상어의 x좌표, [1]:상어의 y좌표

        for(int r = 0; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 9){ //초기 상어의 위치
                    sharkLocation[0] = r;
                    sharkLocation[1] = c;
                    map[r][c] = 0;
                }
            }
        }

        while(true){
            //상어로부터 물고기를 담는 우선순위큐(상어로부터의 거리가 가까운 순)
            fishesFromShark = new PriorityQueue<>((o1, o2) -> {
                if(o1.time == o2.time){ //물고기의 거리가 같다면
                    if(o1.r == o2.r){
                        return o1.c - o2.c; //가장 왼쪽에 있는 물고기
                    }else{
                        return o1.r - o2.r; //가장 위에 있는 물고기
                    }
                }else{
                    return o1.time - o2.time; //거리가 가까운 물고기
                }
            });

            bfs(); //상어로부터 물고기들의 거리

            if(fishesFromShark.isEmpty()) break; //물고기가 없다

            Location fish = fishesFromShark.poll(); //상어로부터 가장 가까운 물고기 꺼내기
            time += fish.time;
            map[fish.r][fish.c] = 0; //상어가 물고기를 먹음

            //상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가
            eat++;
            if(sharkSize == eat){
                sharkSize++;
                eat = 0;
            }

            //상어의 위치 업데이트
            sharkLocation[0] = fish.r;
            sharkLocation[1] = fish.c;

        }

        System.out.println(time);
    }

    /**
     * map을 완전탐색하면서 상어로부터 물고기들의 거리 구하기
     * 물고기들을 우선순위큐에 넣어 상어로부터 가장 가까운 물고기순으로
     */
    public static void bfs(){
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(sharkLocation[0], sharkLocation[1], 0));

        visited = new boolean[N][N];

        while(!queue.isEmpty()){
            Location cur = queue.poll();

            for(int i = 0; i < 4; i++){
                int nr = cur.r + deltas[i][0];
                int nc = cur.c + deltas[i][1];

                if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                if(!visited[nr][nc]){
                    if(map[nr][nc] == sharkSize || map[nr][nc] == 0){ //상어의 크기와 같거나 0이면 지나갈 수 있음
                        visited[nr][nc] = true;
                        queue.add(new Location(nr, nc, cur.time + 1)); //큐에 넣기
                    }else if(map[nr][nc] < sharkSize){ //먹을 수 있는 물고기이면
                        visited[nr][nc] = true;
                        fishesFromShark.add(new Location(nr, nc, cur.time + 1)); //우선순위큐에 넣기
                    }
                }
            }
        }
    }

    public static class Location {
        int r;
        int c;
        int time;
        public Location(int r, int c, int time){
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
}
