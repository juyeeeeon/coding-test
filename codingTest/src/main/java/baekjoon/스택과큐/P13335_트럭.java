package baekjoon.스택과큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P13335_트럭 {
    static final int EMPTY = 0; //빈자리
    static int n, w, L; //다리를 건너는 트럭의 수, 다리의 길이, 다리의 최대하중
    static int cntOnBridge; //다리 위에 있는 트럭의 수
    static int time = 0;
    static Queue<Integer> trucks; //트럭
    static Queue<Integer> bridge; //다리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        trucks = new ArrayDeque<>();
        bridge = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks.offer(Integer.parseInt(st.nextToken()));
        }

        cntOnBridge = 0; //다리 위에 있는 트럭의 개수
        for (int i = 0; i < w; i++) { //초기의 다리는 비어있음
            bridge.offer(EMPTY);
        }

        while (!trucks.isEmpty()) { //마지막 트럭이 다리에 올라갈 때까지 반복
            Integer truck = trucks.peek(); //다리에 올라갈 예정인 트럭

            if (cntOnBridge < w) { //다리 위에 트럭이 올라갈 자리가 있는 경우

                passBridge();

                if (getBridgeSum() + truck <= L) {//트럭이 올라갔을 때 최대 하중 이하인 경우
                    bridge.offer(trucks.poll());
                    cntOnBridge++;
                } else { //트럭이 올라갔을 때 최대 하중 초과인 경우
                    bridge.offer(EMPTY);
                }

                time++;

            } else if(cntOnBridge == w) { //다리 위에 트럭이 올라갈 자리가 없는 경우
                passBridge();  //다리 위에 있는 트럭이 하나가 빠져나갔을 때

                if (getBridgeSum() + truck <= L) { // 다리 위에 올라갈 새로운 트럭이 최대 하중 이하인 경우
                    bridge.offer(trucks.poll()); //다리 위로 올리기
                    cntOnBridge++;
                } else { //다리 위에 올라갈 새로운 트럭이 최대 하중 초과인 경우
                    bridge.offer(EMPTY);
                }

                time++;
            }
        }
        //마지막 트럭이 다리에 올라갔을 때 위의 while문이 끝나므로
        //마지막 트럭이 다리를 통과할 시간만큼(=다리의 길이w) 더해줌
        System.out.println(time + w);


    }

    /**
     * EMPTY나 truck이 다리를 통과
     */
    private static void passBridge() {
        if (bridge.poll() != EMPTY) cntOnBridge--;
    }

    /**
     * 다리 위에 있는 트럭의 무게합
     */
    private static int getBridgeSum() {
        int sum= 0;
        for (int value : bridge) {
            if (value != EMPTY) sum += value;
        }
        return sum;
    }
}
