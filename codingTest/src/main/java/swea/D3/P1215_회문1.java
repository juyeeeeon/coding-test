package swea.D3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P1215_회문1 {
    public static void main(String args[]) throws Exception
    {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        //System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = 10;
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int num = Integer.parseInt(br.readLine());

            char[][] arr = new char[8][8];

            for(int i=0; i<8; i++){
                String row = br.readLine();
                for(int j=0; j<8; j++){
                    arr[i][j] = row.charAt(j);
                }
            }


            int result = 0;

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j <= arr.length - num; j++) {
                    boolean flag = true;
                    for (int k = 0; k < num / 2; k++) {
                        if (arr[i][j + k] != arr[i][j + num - k - 1]) {
                            flag = false;
                        }
                    }
                    if (flag) result++;

                    flag = true;
                    for (int k = 0; k < num / 2; k++) {
                        if (arr[j + k][i] != arr[j + num - k - 1][i]) {
                            flag = false;
                        }
                    }
                    if (flag) result++;
                }
            }

/*            for(int i=0; i<8; i++){
                for(int j=0; j<=8-num; j++){
                    int start = j;
                    int end = j+num-1;

                    boolean flag = true;

                    while(start <= end && flag){
                        if(arr[i][start] == arr[i][end]){
                            start++;
                            end--;
                        }
                        else{
                            flag = false;
                        }
                    }

                    if(flag) result++;
                    else	flag = true;
                }
            }


            for(int i=0; i<=8-num; i++){
                for(int j=0; j<8; j++){
                    int start = i;
                    int end = i+num-1;

                    boolean flag = true;

                    while(start <= end && flag){
                        if(arr[start][j] == arr[end][j]){
                            start++;
                            end--;
                        }
                        else{
                            flag = false;
                        }
                    }

                    if(flag) result++;
                    else	flag = true;
                }
            }*/

            bw.write(result);

            /////////////////////////////////////////////////////////////////////////////////////////////
			/*
				 이 부분에 여러분의 알고리즘 구현이 들어갑니다.
			 */
            /////////////////////////////////////////////////////////////////////////////////////////////

        }

        bw.flush();
        bw.close();
        br.close();
    }
}
