```java
//모두 import하기
import java.util.*;
import java.io.*;
import java.lang.*;
```

```java
//Input과 Output
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//StringTokenizer
StringTokenizer st = new StringTokenizer(br.readLine());
st.nextToken();

~
bw.write(~~~); // 아스키코드에 따른 문자가 출력되므로 int형은 Integer.toString()으로 변환
bw.flush();
bw.close();
br.close();
```
이건 위에 것보다 속도가 느림.
```java
Scanner sc = new Scanner(System.in);
a = sc.nextInt();                           // int 변수 1개 입력받는 예제
b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
g = sc.nextByte();                          // char 변수 1개 입력받는 예제
var = sc.next();                            // 문자열 1개 입력받는 예제
AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
```

```java
//StringBuffer 사용
StringBuffer buffer = new StringBuffer();
buffer.append("adfds");
System.out.println(buffer);
```

```java
//int의 절댓값
Math.abs();
//max값
Math.max( , );
//min값
Math.min( , );
```

```java
//int을 String으로
String strN = Integer.toString(N);
```

```java
//오름차순 정렬
int A[] = {5 ,4, 3, 2, 1};
Arrays.sort(A); //{1, 2, 3, 4, 5}

//내림차순 정렬
Integer A[] = {1, 2, 3, 4, 5};
Arrays.sort(A, Collections.reverseOrder()); //{5, 4, 3, 2, 1}

/**
 * Arrays.sort()는 일시적인 sort가 아닌 array 배열 자체를 sort하므로 array가 변함!
 * /
```
```java
//max값 구하기
Integer[] arr = new Integer[N];
Integer max = Collections.max(Arrays.asList(arr));

//min값 구하기
Integer[] arr = new Integer[N];
Integer max = Collections.min(Arrays.asList(arr));

/**
 * array 배열 자체를 sort하지 않고 그냥 max와 min값을 구하기 때문에 array가 변하지 않음!
 * /
```

```java
//Integer array는 default 값이 null이므로
Integer[] arr = new Integer[N];
//array를 0으로 초기화
Arrays.fill(arr, 0);
```

```java
//stack에서 bottom에서 부터 꺼낼려면
while(!stk.isEmpty()) System.out.print(stk.remove(0));
```

```java
//originalArray의 i번째부터 j-1번째까지 원소들을 복사
int[] tmp = Arrays.copyOfRange(originalArray, i, j);
```