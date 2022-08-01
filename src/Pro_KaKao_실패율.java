import java.util.*;

public class Pro_KaKao_실패율 {
    public static void main(String[] args){
        System.out.println(Arrays.toString(solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3})));
    }
    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N]; // return 값
        int[] playUser = new int[N+1]; // 매 stage마다 있는 사람의 수를 저장할 배열 < 1번 부터 시작 >
        Map<Integer, Double> map = new HashMap<Integer, Double>(); // map을 통해 value를 기준으로 정렬 시도

        int stayUser = stages.length; // stages -> 매 stage마다 지나간 총 인원 수
        for(int stage : stages){ // for - each구문으로 해당 stages를 기준으로 stage를 증가시킴
            if(stage > N) // stage > N일 경우 N보다 크기때문에 무시
                continue;
            playUser[stage]++;
        }

        // 1부터 시작
        for(int i = 1; i < N+1; i++){
            if(playUser[i] != 0 && stayUser != 0){ // 분자 && 분모가 0인 경우 Nan이 나오기 때문에 필히 처리
                map.put(i, (double) playUser[i] / stayUser);
                stayUser -= playUser[i];
            }
            else{
                map.put(i, (double) 0);
            }

        }

        // * 중요!!
        // map의 keySet()을 List로 받아 초기화
        List<Integer> keyList = new LinkedList<>(map.keySet());
        // Collections클래스의 sort함수 사용
        // 매개변수는 List와 Comparator<>()
        // Double이 아니고 Integer인 이유 -> key 값은 int이기 때문
        Collections.sort(keyList, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                // * 중요!!
                // o1, o2 -> 오름차순 정렬 1, 2, 3 ...
                // o2, o1 -> 내림차순 정렬 3, 2, 1 ...
//                return Double.compare(map.get(o2), map.get(o1));
                if(map.get(o1) < map.get(o2))
                    return 1;
                else if(map.get(o1) > map.get(o2))
                    return -1;
                else
                    return 0;
            }
        });

        int count = 0;
        for(Integer key : keyList){
            answer[count] = key;
            count++;
        }

        return answer;
    }
}