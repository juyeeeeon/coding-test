package 복습.programmers;

public class 조이스틱 {

    public int solution(String name) {
        int answer = 0;
        int move = name.length() - 1; // 오른쪽으로 쭉 간 횟수

        for(int i = 0; i < name.length(); i++) {
            //상,하 알파벳 맞추기
            int up = name.charAt(i) - 'A';
            int down = 26 - up;
            answer += Math.min(up, down);

            //좌,우 자리 맞추기
            if (i < name.length() - 1 && name.charAt(i + 1) == 'A') { //오른쪽이 A이면
                int endA = i + 1;
                while(endA < name.length() && name.charAt(endA) == 'A')
                    endA++;
                move = Math.min(move, i * 2 + (name.length() - endA));// 오른쪽으로 갔다 다시 왼쪽으로 꺾기
                move = Math.min(move, (name.length() - endA) * 2 + i);// 왼쪽으로 갔다 다시 오른쪽으로 꺾기
            }
        }
        return answer + move;
    }
}
