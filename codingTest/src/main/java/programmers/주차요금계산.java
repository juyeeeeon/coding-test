package programmers;

import java.util.*;

public class 주차요금계산 {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};

        Map<String, String> inMap = new HashMap<>(); //차량 번호, 입차시간
        Map<String, Integer> parkingTimeMap = new HashMap<>(); //차량번호, 누적주차시간

        for(int i = 0; i < records.length; i++){
            String carNumber = records[i].split(" ")[1];
            parkingTimeMap.put(carNumber, 0); //차량번호, 누적주차시간
        }

        for(int i = 0; i < records.length; i++){
            String[] info = records[i].split(" ");

            String carNumber = info[1];
            String time = info[0];
            if(inMap.containsKey(carNumber)){ //carNumber가 입차한 기록이 있다면
                String[] inTime = inMap.remove(carNumber).split(":"); //입차시간
                String[] outTime = time.split(":"); //출차시간

                int hour = Integer.parseInt(outTime[0]) - Integer.parseInt(inTime[0]);
                int minute = Integer.parseInt(outTime[1]) - Integer.parseInt(inTime[1]);

                parkingTimeMap.replace(carNumber, parkingTimeMap.get(carNumber) + 60 * hour + minute); //차량번호, 누적주차시간 업데이트

            }else{
                inMap.put(carNumber, time); // 차량 번호, 입차시간
            }
        }

        //입차된 후에는 출차된 내역이 없으므로, 23:59에 출차되었다고 간주
        for(String key : inMap.keySet()){
            String[] inTime = inMap.get(key).split(":");

            int hour = 23 - Integer.parseInt(inTime[0]);
            int minute = 59 -Integer.parseInt(inTime[1]);

            parkingTimeMap.replace(key, parkingTimeMap.get(key) + 60 * hour + minute); //차량번호, 누적주차시간 업데이트
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(parkingTimeMap.entrySet());
        list.sort((o1, o2) -> Integer.parseInt(o1.getKey()) - Integer.parseInt(o2.getKey())); //차량 번호가 작은 자동차부터 정렬


        answer = new int[list.size()];

        for(int i = 0; i < answer.length; i++){
            if(list.get(i).getValue() > fees[0]){ //누적주차시간이 기본시간보다 크면
                //주차요금 = 기본요금 + (누적주차시간 - 기본시간) / 단위시간 * 단위요금
                answer[i] = fees[1] + (int) Math.ceil((list.get(i).getValue() - fees[0]) / (double)fees[2]) * fees[3];
            }else{ //누적주차시간이 기본시간보다 작으면
                //주차요금 = 기본요금
                answer[i] = fees[1];
            }
        }

        return answer;
    }
}