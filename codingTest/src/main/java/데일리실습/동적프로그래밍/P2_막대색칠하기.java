package 데일리실습.동적프로그래밍;

/**
 * - 연습문제 2 -
 * 1cm 짜리 파란 막대와 1cm 짜리 노란 막대 그리고 2cm 짜리 빨간 막대가 있다.
 * 이 막대들을 연결하여 길이가 ncm인 막대를 만드는 방법의 수를 f(n)이라 하자.
 * 예를 들면 2cm 막대를 만드는 방법은
 * (파란 막대, 파란 막대),
 * (파란 막대, 노란 막대),
 * (노란 막대, 파란 막대),
 * (노란 막대, 노란 막대),
 * (빨간 막대)
 * 5가지이므로 f(2) = 5이다.
 * f(6)의 값은? 169
 */
public class P2_막대색칠하기 {
    static int[] memo1;
    static int[] memo2;
    public static void main(String[] args) {
        memo1 = new int[7];
        memo2 = new int[7];

        memo1[2] = 4;
        memo2[2] = 1;
        memo1[3] = 10;
        memo2[3] = 2;
        System.out.println(dp1(6) + dp2(6));
    }

    private static int dp1(int n) {
        if (n > 3 && memo1[n] == 0) {
            memo1[n] = (dp1(n - 1) + dp2(n - 1)) * 2;
        }

        return memo1[n];

    }
    private static int dp2(int n) {
        if (n > 3 && memo2[n] == 0) {
            memo2[n] = dp1(n - 1)/2;
        }

        return memo2[n];

    }
}
