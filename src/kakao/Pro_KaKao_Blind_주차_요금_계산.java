package kakao;

import java.util.*;
public class Pro_KaKao_Blind_주차_요금_계산 {
    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        System.out.println(Arrays.toString(solution(fees, records)));
    }

    // fees : 주차 요금표
    // records : 데이터 베이스에 담긴 데이터들
    public static int[] solution(int[] fees, String[] records) {
        StringTokenizer st; // records에 담긴 변수 값을 나눠 담기 위해 사용
        Map<String, Integer> carNumber = new TreeMap<>(); // 차량당 계산된 비용 담기
        Map<String, Integer> stackTime = new TreeMap<>(); // 차량당 누적 이용 시간 담기

        String[][] recordsArr = new String[records.length][4];
        // Step 1. 반복 -> records 매개변수의 값을 공백 기준으로 값을 나눈다.
        for(int i = 0; i < records.length; i++){
            String recordsStr = records[i];
            st = new StringTokenizer(recordsStr);
            String time = st.nextToken();
            recordsArr[i][0] = time.substring(0,2); // 시
            recordsArr[i][1] = time.substring(3,5); // 분
            recordsArr[i][2] = st.nextToken(); // 차량 번호
            recordsArr[i][3] = st.nextToken(); // 입차, 출차
        }

        // Step 2. 반복 -> 주차 시간 계산
        for(int i = 0; i < records.length; i++){
            if(recordsArr[i][3].equals("IN")){ // 입차 시 시간 저장
                carNumber.put(recordsArr[i][2],
                        (Integer.parseInt(recordsArr[i][0]) * 60) + Integer.parseInt(recordsArr[i][1]));
            } else { // 출차 시 시간 계산
                carNumber.put(recordsArr[i][2],
                        Math.abs(((Integer.parseInt(recordsArr[i][0]) * 60) + Integer.parseInt(recordsArr[i][1])) - carNumber.get(recordsArr[i][2])));
                if(stackTime.containsKey(recordsArr[i][2])){ // 누적 시간을 담는 변수에 값이 있는 경우
                    stackTime.put(recordsArr[i][2], stackTime.get(recordsArr[i][2]) + carNumber.get(recordsArr[i][2]));
                } else { // 없는 경우
                    stackTime.put(recordsArr[i][2], carNumber.get(recordsArr[i][2]));
                }
                carNumber.remove(recordsArr[i][2]);
            }
        }

        // Step 3. 반복 -> 출차가 되지 않은 차량이 있는 경우 시간 계산
        for(String key : carNumber.keySet()){
            carNumber.put(key, Math.abs((((23 * 60) + 59) - carNumber.get(key))));
            if(stackTime.containsKey(key)){ // 누적 시간을 담는 변수에 값이 있는 경우
                stackTime.put(key, stackTime.get(key) + carNumber.get(key));
            } else { // 없는 경우
                stackTime.put(key, carNumber.get(key));
            }
        }

        List<Integer> pay = new LinkedList();
        
        // Step 4. 반복 -> 누적된 시간으로 주차 요금 계산
        for(String key : stackTime.keySet()){
            if(stackTime.get(key) <= fees[0]){
                pay.add(fees[1]);
            } else {
                pay.add(fees[1] + (int) Math.ceil((stackTime.get(key) - (double) fees[0]) / fees[2]) * fees[3]);
            }
        }
        
        // Step 5. answer 에 맞게 값 넘기기
        int[] answer = new int[pay.size()];
        for(int i = 0; i < pay.size(); i++){
            answer[i] = pay.get(i);
        }
        return answer;
    }
}

