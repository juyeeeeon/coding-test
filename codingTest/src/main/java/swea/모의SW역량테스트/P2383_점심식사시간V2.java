package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P2383_점심식사시간V2 {
    static int T, N, map[][], minTime;
    static ArrayList<Person> persons;
    static ArrayList<int[]> stairs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            persons = new ArrayList<>();
            stairs = new ArrayList<>();
            map = new int[N][N];

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] == 1) persons.add(new Person(r, c));
                    if (map[r][c] >= 2) stairs.add(new int[]{r, c, map[r][c]}); // [0]:행, [1]:열, [2]:길이
                }
            }

            minTime = Integer.MAX_VALUE;
            perm(0);

            System.out.println("#" + test_case + " " + minTime);
        }
    }

    static void perm(int cnt) {
        if (cnt == persons.size()) {
            int max = 0; //모든 사람들이 계단을 내려가는 데 걸리는 시간 중에서 가장 긴 시간

            for (int stair = 0; stair < 2; stair++) {
                PriorityQueue<Person> pq = new PriorityQueue<>();
                int[] timeStamp = new int[100]; //해당시간에 계단을 이용하는 사람의 수

                for (int i = 0; i < persons.size(); i++) {
                    if (persons.get(i).stair == stair) {
                        pq.add(persons.get(i));
                    }
                }

                int finish = 0;
                while (!pq.isEmpty()) {
                    Person cur = pq.poll();
                    int start = cur.time; //계단까지 도착시간
                    int stairLen = stairs.get(cur.stair)[2]; //계단의 길이
                    finish = start + stairLen; //계단에 도착하고 내려갈 때까지 걸리는 최소 시간

                    for (int j = start; j < finish; j++) {
                        if (timeStamp[j] == 3) { //세명이 계단을 이용하고 있다면
                            finish++; //전체 시간이 1 늘어남
                            continue;
                        }
                        timeStamp[j]++;
                    }
                    if (max < finish) {
                        max = finish;
                    }
                }
            }

            //모든사람이 내려가는 최소시간 업데이트
            if (minTime > max) {
                minTime = max;
            }
            return;
        }

        // 계단 선택하고, 길이 구하기
        //0번째 계단을 선택한 경우
        persons.get(cnt).time = Math.abs(persons.get(cnt).r - stairs.get(0)[0]) + Math.abs(persons.get(cnt).c - stairs.get(0)[1]) + 1; //계단 입구에 도착하면, 1분 후 아래칸으로 내려 갈 수 있다
        persons.get(cnt).stair = 0;
        perm(cnt + 1);

        //1번째 계단을 선택한 경우
        persons.get(cnt).time = Math.abs(persons.get(cnt).r - stairs.get(1)[0]) + Math.abs(persons.get(cnt).c - stairs.get(1)[1]) + 1; //계단 입구에 도착하면, 1분 후 아래칸으로 내려 갈 수 있다
        persons.get(cnt).stair = 1;
        perm(cnt + 1);
    }


    static class Person implements Comparable<Person> {
        int r, c, time, stair;

        public Person(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Person o) {
            return this.time - o.time;
        }
    }
}

