package baekjoon.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class P1181_단어정렬_stream {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        //시작
        long start = System.currentTimeMillis();


        //(String s1) -> s1.length() == String::length
        Arrays.sort(arr, Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()));
        String[] array = Arrays.stream(arr).distinct().toArray(String[]::new);
                                                            //value -> new String[value] == String[]::new


        //끝
        long end = System.currentTimeMillis();

        for (String s : array) {
            System.out.println(s);
        }

        System.out.println("걸린 시간 : " + (end - start));

    }
}
