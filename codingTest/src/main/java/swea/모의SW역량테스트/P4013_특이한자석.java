package swea.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P4013_특이한자석 {
    static int T, K;
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static LinkedList<Integer>[] mg, origin;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            K = Integer.parseInt(br.readLine());

            mg = new LinkedList[5];
            origin = new LinkedList[5];
            for (int i = 0; i <= 4; i++) {
                mg[i] = new LinkedList<>();
                origin[i] = new LinkedList<>();
            }

            for (int i = 1; i <=4 ; i++) {
                st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    int NS = Integer.parseInt(st.nextToken());
                    mg[i].add(NS);
                    origin[i].add(NS);
                }
            }


            for (int inst = 0; inst < K; inst++) {
                st = new StringTokenizer(br.readLine());
                int cur = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());


                rotate(cur, dir);
                if (dir == 1) {//시계방향
                    /*for (int i = 1; i <= 4; i++) {
                        for (int j = 0; j < 8; j++) {
                            System.out.print(mg[i].get(j) + " ");
                        }
                        System.out.println();
                    }*/

                    if (cur == 1) {
                        if (!(mg[cur].get(2).equals(mg[2].get(6)))) { //1-2 다른 극일 때

                            rotate(2, -1); //2 반시계방향

                            if (!(mg[2].get(2).equals(mg[3].get(6)))) { //2-3 다른 극일 때

                                rotate(3, 1); //3 시계방향

                                if (!(mg[3].get(2).equals(mg[4].get(6)))) { //3-4 다른 극일 때
                                    rotate(4, -1); //4 반시계방향
                                }
                            }
                        }
                    }
                    if (cur == 2) {
                        if (!(mg[cur].get(6).equals(mg[1].get(2)))) { //1-2 다른 극일 때
                            rotate(1, -1); //1 반시계방향
                        }

                        if (!(mg[cur].get(2).equals(mg[3].get(6)))) { //2-3 다른 극일 때
                            rotate(3, -1); //3 반시계방향

                            if (!(mg[3].get(2).equals(mg[4].get(6)))) { //3-4 다른 극일때
                                rotate(4, 1); //4 시계방향
                            }
                        }
                    }

                    if (cur == 3) {
                        if (!(mg[cur].get(2).equals(mg[4].get(6)))) { //3-4 다른 극일 때
                            rotate(4, -1); //4 반시계방향
                        }

                        if (!(mg[cur].get(6).equals(mg[2].get(2)))) { //2-3 다른 극 일때
                            rotate(2, -1);//2 반시계방향

                            if (!(mg[2].get(6).equals(mg[1].get(2)))) { //1-2 다른 극 일때
                                rotate(1, 1); //1 시계방향
                            }
                        }
                    }
                    if (cur == 4) {
                        if (!(mg[cur].get(6).equals(mg[3].get(2)))) { //3-4 다른 극일때

                            rotate(3, -1); //3 반시계

                            if (!(mg[3].get(6).equals(mg[2].get(2)))) { //2-3 다른 극일때

                                rotate(2, 1); //2 시계

                                if (!(mg[2].get(6).equals(mg[1].get(2)))) { //1-2 다른 극일때

                                    rotate(1, -1); //1 반시계
                                }
                            }
                        }
                    }

                } else { //반시계방향
                    /*System.out.println("========");
                    for (int i = 1; i <= 4; i++) {
                        for (int j = 0; j < 8; j++) {
                            System.out.print(mg[i].get(j) + " ");
                        }
                        System.out.println();
                    }*/

                    if (cur == 1) {
                        if (!(mg[cur].get(2).equals(mg[2].get(6)))) { //1-2 다른 극일 때

                            rotate(2, 1); //2 시계방향

                            if (!(mg[2].get(2).equals(mg[3].get(6)))) { //2-3 다른 극일 때

                                rotate(3, -1); //3 반시계방향

                                if (!(mg[3].get(2).equals(mg[4].get(6)))) { //3-4 다른 극일 때
                                    rotate(4, 1); //4 시계방향
                                }
                            }
                        }
                    }
                    if (cur == 2) {
                        if (!(mg[cur].get(6).equals(mg[1].get(2)))) { //1-2 다른 극일 때
                            rotate(1, 1); //1 시계방향
                        }

                        if (!(mg[cur].get(2).equals(mg[3].get(6)))) { //2-3 다른 극일 때
                            rotate(3, 1); //3 시계방향

                            if (!(mg[3].get(2).equals(mg[4].get(6)))) { //3-4 다른 극일때
                                rotate(4, -1); //4 반시계방향
                            }
                        }
                    }
                    if (cur == 3) {
                        if (!(mg[cur].get(2).equals(mg[4].get(6)))) { //3-4 다른 극일 때
                            rotate(4, 1); //4 시계방향
                        }

                        if (!(mg[cur].get(6).equals(mg[2].get(2)))) { //2-3 다른 극 일때
                            rotate(2, 1);//2 시계방향

                            if (!(mg[2].get(6).equals(mg[1].get(2)))) { //1-2 다른 극 일때
                                rotate(1, -1); //1 반시계방향
                            }
                        }
                    }
                    if (cur == 4) {
                        if (!(mg[cur].get(6).equals(mg[3].get(2)))) { //3-4 다른 극일때
                            rotate(3, 1); //3 시계

                            if (!(mg[3].get(6).equals(mg[2].get(2)))) { //2-3 다른 극일때

                                rotate(2, -1); //2 반시계

                                if (!(mg[2].get(6).equals(mg[1].get(2)))) { //1-2 다른 극일때

                                    rotate(1, 1); //1 시계
                                }
                            }
                        }
                    }
                }

                originToMg();
            }



            int answer = 0;

//            System.out.println("============");
            for (int i = 1; i <= 4; i++) {
                /*for (int j = 0; j < 8; j++) {
                    System.out.print(mg[i].get(j) + " ");
                }
                System.out.println();*/
                if (mg[i].peek() == 1) { //S극이면
                    answer += Math.pow(2, i - 1);
                }
            }



            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void originToMg() {

        for (int i = 1; i <= 4; i++) {
            mg[i] = new LinkedList<>();
            for (int j = 0; j < 8; j++) {
                mg[i].add(origin[i].get(j));
            }
        }
    }

    /**
     * no번째 자석을 dir방향으로 회전
     */
    private static void rotate(int no, int dir) {
        if (dir == 1) { //시계방향
            Integer remove = origin[no].removeLast();
            origin[no].addFirst(remove);

        } else { //반시계방향
            Integer remove = origin[no].removeFirst();
            origin[no].addLast(remove);
        }
    }

}

