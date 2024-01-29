package programmers;

public class 등굣길 {

    public static void main(String[] args) {
        int[][] puddles = {{2, 2}};
        System.out.println(solution(4, 3, puddles));
    }

    static int[][] map;
    static final int cons = 1000000007;

    public static int solution(int m, int n, int[][] puddles) {

        map = new int[m + 1][n + 1];
        map[0][1] = 1;

        for (int[] puddle : puddles) {
            map[puddle[0]][puddle[1]] = -1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == -1) continue;
                if (map[i - 1][j] == -1) {
                    map[i][j] = map[i][j - 1] % cons;
                    continue;
                }
                if (map[i][j - 1] == -1) {
                    map[i][j] = map[i - 1][j] % cons;
                    continue;
                }
                map[i][j] = (map[i - 1][j] + map[i][j - 1]) % cons;
            }
        }


        return map[m][n] % cons;
    }
}
