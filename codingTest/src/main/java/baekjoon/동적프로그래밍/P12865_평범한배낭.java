package baekjoon.동적프로그래밍;

import java.io.*;
import java.util.StringTokenizer;

public class P12865_평범한배낭 {
    static int N, K;
    static int[][] item;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //물품의 수
        K = Integer.parseInt(st.nextToken()); //최대 무게

        item = new int[N+1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            item[i][0] = Integer.parseInt(st.nextToken()); //무게
            item[i][1] = Integer.parseInt(st.nextToken()); //가치
        }

        dp = new int[N+1][K+1];

        for (int idx = 1; idx <= N; idx++) { //아이템의 idx
            for (int space = 1; space <= K; space++) { //남은 무게

                //담을 수 없을 경우
                if (item[idx][0] > space) {
                    dp[idx][space] = dp[idx-1][space];
                } else { //담을 수 있는 경우
                    dp[idx][space] = Math.max(dp[idx - 1][space], item[idx][1] + dp[idx - 1][space - item[idx][0]]); //담지 않았을 때와 담았을 때의 최대값
                }
            }
        }

        System.out.println(dp[N][K]);


        /**
         * memo[idx - 1][space]와 memo[idx - 1][space - item[idx][0]]를 비교할 때, 이 둘의 차이점을 이해하는 것이 중요합니다.
         * 여기서 핵심은 아이템을 배낭에 담았을 때와 담지 않았을 때의 상황을 구분하는 것입니다.
         *
         * memo[idx - 1][space]는 현재 고려 중인 아이템(idx)을 배낭에 담지 않고,
         * 이전 아이템들만 고려하여 현재 주어진 무게(space)로 달성할 수 있는 최대 가치를 의미합니다.
         *
         * memo[idx - 1][space - item[idx][0]]는 현재 고려 중인 아이템(idx)을 배낭에 담았을 때의 상황을 나타냅니다.
         * 이 표현은 현재 아이템의 무게(item[idx][0])를 고려하여 남은 무게(space - item[idx][0])에 대해, 이전 아이템들로 달성할 수 있는 최대 가치를 나타냅니다.
         * 여기에 현재 아이템의 가치(item[idx][1])를 더하면, 현재 아이템을 포함하여 달성할 수 있는 최대 가치를 계산할 수 있습니다.
         *
         * 즉, memo[idx - 1][space - item[idx][0]]를 사용하는 이유는 현재 아이템을 배낭에 담았을 때의 상황을 모델링하기 위함입니다.
         * 이를 통해, 현재 아이템을 추가로 담는 것이 가능한지와 그렇게 했을 때의 최대 가치가 어떻게 되는지를 계산할 수 있습니다.
         *
         * 현재 아이템을 담으면, 그 아이템의 무게만큼 전체 배낭에서 사용할 수 있는 무게가 줄어들기 때문에,
         * space - item[idx][0]를 새로운 무게 한도로 보고 이전 아이템들을 이용해 그 무게 한도 내에서 달성할 수 있는 최대 가치(memo[idx - 1][space - item[idx][0]])를 찾는 것입니다.
         *
         * 따라서, memo[idx - 1][space - item[idx][0]]는 아이템을 담았을 때 남은 무게에 대한 최대 가치를 나타내며,
         * 이것을 현재 아이템의 가치와 합쳐서 해당 아이템을 포함시켰을 때의 총 가치를 계산하는데 사용됩니다.
         */
    }
}
