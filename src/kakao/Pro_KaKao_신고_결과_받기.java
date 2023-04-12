package kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Pro_KaKao_신고_결과_받기 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"}, 2)));
    }

    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};
        // k번 이상 신고된 사람을 찾기 위해 HashMap을 통해 횟수를 카운트 해준다.
        HashMap<String, Integer> reportCount = new HashMap<String, Integer>();
        // < Key, [Value]> 형식으로 만들어 한 사람당 한번의 신고가 가능하도록 설계한다.
        HashMap<String, ArrayList<String>> reportCheck = new HashMap<String, ArrayList<String>>();
        // StringTokenizer를 선언해 문자열을 Token기준으로 나눈다.
        StringTokenizer st;
        // HashMap에 저장될 ArrayList는 및에서 생성해주기 위해 초기값을 설정하지 않으며, search는 아래 문장을 수행하지 않아도 결과는 뽑혀야 하기에 초기 값을 설정한다.
        ArrayList<String> arr, search = new ArrayList<>();
        // 첫번째 반복은 id_list로 받는 변수에 있는 사람들을 집어넣어주기 위해서 이다.
        for(int i = 0; i < id_list.length; i++){
            reportCount.put(id_list[i], 0);
        }

        // 두번째 반복은 신고를 접수한 갯수만큼 반복한다.
        for(int i = 0; i < report.length; i++){
            st = new StringTokenizer(report[i]); // 문자열을 Token기준으로 쪼갠다.
            String userId = st.nextToken(), reportId = st.nextToken(); // 각 토큰 별로 userId(신고자), reportId(신고당한사람)를 생성한다.
            // 신고자가 처음 신고한 경우 수행
            if(!reportCheck.containsKey(userId)){
                // ** HashMap안에 ArrayList 집어넣는 방법
                arr = new ArrayList<>();
                arr.add(reportId);
                reportCheck.put(userId, arr);
                reportCount.put(reportId, reportCount.get(reportId)+1); // 신고 당한사람의 신고당한 횟수 카운트
            }
            else{
                // 중복되는 신고를 제외하기 위해서 처음 신고 되는 사람인지 확인하는 구문
                if(!reportCheck.get(userId).contains(reportId)){
                    reportCheck.get(userId).add(reportId);
                    reportCount.put(reportId, reportCount.get(reportId)+1);
                }
            }
            // 카운트된 횟수가 k번 이상일 경우 수행
            if(reportCount.get(reportId) >= k){
                // 중복을 제외하면서 신고당한 횟수가 일정 이상인 경우 그 신고당한 사람을 저장하는 ArrayList
                if(!search.contains(reportId))
                    search.add(reportId);
            }
        }

        // answer에 대입하기 위해 선언
        int[] tmp = new int[id_list.length];
        // 쓸대 없는 반복을 줄이기 위해 size가 0이라면 실행하지 않음
        if(search.size() != 0){
            // 2차 포문을 통해 reportCheck에 Value가 null인 경우를 고려하며, 해당 Value안에서 신고당한 횟수를 초과하는 사람이 있다면 그거에 맞춰 증가시킨다.
            for(int i = 0; i < id_list.length; i++){
                for(int j = 0; j < search.size(); j++){
                    if(reportCheck.get(id_list[i]) != null && reportCheck.get(id_list[i]).contains(search.get(j)))
                        tmp[i] += 1;
                }
            }
        }
        answer = tmp;
        return answer;
    }
}
