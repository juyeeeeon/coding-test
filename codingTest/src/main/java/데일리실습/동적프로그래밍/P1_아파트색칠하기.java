package 데일리실습.동적프로그래밍;

/**
 * - 연습문제 1 -
 * 아파트를 각 층별로 파란색 또는 노란색 페인트로 칠하되 다음과 같은 규칙으로 칠하려고
 * 한다.
 * 노란색은 인접한 두 층에 연속하여 사용할 수 있다.
 * 파란색은 인접한 두 층에 연속하여 사용할 수 없다.
 * 이와 같은 규칙으로 층의 아파트를 칠할 수 있는 방법의 수를 f(n)이라 하면 다음 그림과
 * 같이 f(1) = 2, f(2) = 3 이다. f(8)을 얼마인가? 55
 */
public class P1_아파트색칠하기 {
    static int[][] memoization;

    public static void main(String[] args) {

        memoization = new int[9][2]; //[][0]:꼭대기층의 노란색의 개수, [][1]:꼭대기층의 파란색의 개수

        memoization[1][0] = 1;
        memoization[1][1] = 1;
        memoization[2][0] = 2;
        memoization[2][1] = 1;

        System.out.println(dp(8));
    }

    private static int dp(int floor) {
        if (floor > 2 && (memoization[floor][0] == 0)) {
            memoization[floor][0] = dp(floor - 1);
            memoization[floor][1] = dp(floor - 2);
        }

        return memoization[floor][0] + memoization[floor][1];
    }
}
