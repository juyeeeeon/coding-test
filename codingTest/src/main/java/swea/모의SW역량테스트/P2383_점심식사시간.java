/*
package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P2383_점심식사시간 {
    static int T, N, map[][], personNum, stairNum, time = 0;
    static ArrayList<Person> personsLocation;
    static ArrayList<Stair>stairs;
    static int[] personGoToWhichStair;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            personsLocation = new ArrayList<>();
            stairs = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] == 1) personsLocation.add(new Person(r, c));
                    if (map[r][c] > 1) stairs.add(new Stair(r, c));
                }
            }

            personNum = personsLocation.size();
            stairNum = stairs.size();
            personGoToWhichStair = new int[personNum];
            perm(0);



        }
    }

    //모든 사람들이 계단을 가능 모든 경우
    private static void perm(int personIdx) {
        if (personIdx == personNum) {
            System.out.println("test: " + Arrays.toString(personGoToWhichStair));

            for (int i = 0; i < personGoToWhichStair.length; i++) {
                int stairIdx = personGoToWhichStair[i];
                int stair_r = stairs.get(stairIdx).r;
                int stair_c = stairs.get(stairIdx).c;
                int person_r = personsLocation.get(i).r;
                int person_c = personsLocation.get(i).c;

                int time = getTime(person_r, person_c, stair_r, stair_c);
                personsLocation.get(i).stair = stairIdx;
                personsLocation.get(i).time = time;
            }
            dfs();

            return;
        }

        for (int stairIdx = 0; stairIdx < stairNum; stairIdx++) {
            personGoToWhichStair[personIdx] = stairIdx;
            perm(personIdx + 1);
        }
    }

    private static void dfs() {

        PriorityQueue<Person> pq = new PriorityQueue<>();

        while (!pq.isEmpty()) {
            Person cur = pq.poll();
            int stairIdx = cur.stair;
            int time = cur.time;

            stairs.get(stairIdx).
        }


    }

    private static int getTime(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    private static class Stair{
        int r;
        int c;
        int using = 0;
        int waiting = 0;
        Queue<Person> usingQueue = new ArrayDeque<>();
        Queue<Person> waitingQueue = new ArrayDeque<>();

        public Stair(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Stair(Person person) {
            if (usingQueue.size() < 3) {
                Person poll = usingQueue.poll();
                this.usingQueue.add(person);
                using++;
            } else {
                waitingQueue.add(person);
                waiting++;
            }
        }
    }

    private static class Person implements Comparable<Person>{
        int r;
        int c;
        int stair;
        int time;

        public Person(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Person(int r, int c, int stair, int time) {
            this.r = r;
            this.c = c;
            this.stair = stair;
            this.time = time;
        }

        @Override
        public int compareTo(Person o) {
            return this.time - o.time;
        }
    }
}
*/
