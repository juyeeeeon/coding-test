package 복습.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P2383_점심식사시간 {
    static int T, N;
    static int minTime;
    static ArrayList<Person> persons;
    static ArrayList<int[]> stairs;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            persons = new ArrayList<>();
            stairs = new ArrayList<>();
            map = new int[N][N];

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] == 1) persons.add(new Person(r, c));
                    if (map[r][c] > 1) stairs.add(new int[]{r, c, map[r][c]}); //[0]행 [1]열 [2]길이
                }
            }

            //값 초기화
            minTime = Integer.MAX_VALUE;
            dfs(0);

            System.out.println("#" + test_case + " " + minTime);
        }


    }

    /**
     * 사람마다 두 계단을 선택하는 모든 경우
     *
     * @param idx persons의 인덱스
     */
    private static void dfs(int idx) {
        if (idx == persons.size()) { //모든 사람이 계단을 골랐다면
            int lateTime = 0; //두 계단에서 가장 마지막으로 내려오는 사람의 시간

            //계단마다 처리
            for (int i = 0; i < stairs.size(); i++) {
                int startTime = 0;
                int endTime = 0;
                int[] timeStamp = new int[100];

                PriorityQueue<Person> pq = new PriorityQueue<>(); //계단에 가장 먼저 도착하는 사람순으로
                for (Person person : persons) {
                    if (person.stair == i) pq.add(person);
                }

                while(!pq.isEmpty()) {

                    Person person = pq.poll();
                    startTime = person.arriveTimeAtStair; //계단을 내려가기 시작하는 시간
                    endTime = startTime + stairs.get(i)[2]; //계단을 내려간 시간

                    for (int j = startTime; j < endTime; j++) {
                        if (timeStamp[j] == 3) endTime++; //시간j에 3명이 내려가고 있다면 시간1 추가
                        else timeStamp[j] ++; //시간j에 내려가는 사람 한명 추가
                    }

                    lateTime = Math.max(lateTime, endTime); //계단에서 가장 늦게 내려온 사람 업데이트
                }
            }

            minTime = Math.min(minTime, lateTime); //두 계단에서 가장 마지막으로 내려오는 사람의 최소시간

            return;
        }

        persons.get(idx).stair = 0;
        persons.get(idx).arriveTimeAtStair = getDistance(persons.get(idx).r, persons.get(idx).c, stairs.get(0)[0], stairs.get(0)[1]) + 1;
        dfs(idx + 1); //idx번째 사람이 0번째 계단으로 가는 경우

        persons.get(idx).stair = 1;
        persons.get(idx).arriveTimeAtStair = getDistance(persons.get(idx).r, persons.get(idx).c, stairs.get(1)[0], stairs.get(1)[1]) + 1;
        dfs(idx + 1);//idx번째 사람이 1번째 계단으로 가는 경우
    }

    private static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    private static class Person implements Comparable<Person>{
        int r;
        int c;
        int stair;
        int arriveTimeAtStair;

        public Person(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Person o) {
            return this.arriveTimeAtStair - o.arriveTimeAtStair;
        }
    }
}
