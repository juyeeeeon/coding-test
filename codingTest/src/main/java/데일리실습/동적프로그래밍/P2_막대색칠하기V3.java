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
public class P2_막대색칠하기V3 {
    static int[] memo;
    public static void main(String[] args) {
        memo = new int[7];
        memo[1] = 2;
        memo[2] = 5;

        for (int i = 3; i <= 6; i++) {
            memo[i] = memo[i - 1] * 2 + memo[i - 2];
        }

        System.out.println(memo[6]);
    }

    /**
     *  4cm는 3cm에서 1cm을 더할 가지수는 2가지, 2cm에서 2cm을 더할 가지수는 1가지
     *  =>  memo[n] = memo[n-1]*2 + memo[n-2]
     */

}
