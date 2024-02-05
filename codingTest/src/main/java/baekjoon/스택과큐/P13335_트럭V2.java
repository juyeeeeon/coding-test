package baekjoon.스택과큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P13335_트럭V2 {
    static final int EMPTY = 0; //빈자리
    static int n, w, L; //다리를 건너는 트럭의 수, 다리의 길이, 다리의 최대하중
    static int sum; //다리 위에 있는 트럭의 무게
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

        sum = 0; //다리 위에 있는 트럭의 무게

        for (int i = 0; i < w; i++) { //초기의 다리는 비어있음
            bridge.offer(EMPTY);
        }

        while (!bridge.isEmpty()) { //다리가 빌 때 까지 반복
            sum -= bridge.poll(); //다리를 통과

            if (!trucks.isEmpty()) {
                if (sum + trucks.peek() <= L) { //최대 하중 이하
                    int truck = trucks.poll();
                    bridge.offer(truck);
                    sum += truck;
                } else { //최대 하중 초과
                    bridge.offer(EMPTY);
                }
            }

            time++;
        }

        System.out.println(time);
    }

}
