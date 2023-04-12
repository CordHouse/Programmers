package kakao;

import java.util.*;
public class Pro_KaKao_Blind_개인정보_수집_유효기간 {

    public static void main(String[] args) {
        String today = "2022.05.19";
        String[] terms = {"A 6", "B 12", "C 3"};
        String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
        System.out.println(solution(today, terms, privacies));
    }

    public static List<Integer> solution(String today, String[] terms, String[] privacies) {
        HashMap<String, Integer> termsMap = createTerms(terms);
        List<Integer> todays = daySplit(today);

        List<Integer> answer = startCheck(privacies, termsMap, todays);

        return answer;
    }

    public static List<Integer> startCheck(String[] privacies, HashMap<String, Integer> termsMap, List<Integer> todays) {
        // 반복문 넣기
        List<Integer> answer = new LinkedList<>();
        for(int i = 0; i < privacies.length; i++) {
            String[] privaciesSplit = privacies[i].split(" ");
            List<Integer> privaciesDay = daySplit(privaciesSplit[0]);
            int afterSumDay = calTerms(termsMap, privaciesSplit[1]); // 유효 기간
            int day = calDay(privaciesDay); // 수집날짜
            int sum = day + afterSumDay; // 오늘 날짜부터 유효기간 날짜까지

            int priMonth = sum/28; // 월 (12가 넘어가면 1부터 다시 조건 확인해야함)
            int priDay = (sum%28)-1; // 일 (하루 전까진 보관)
            int priYear = privaciesDay.get(0) + createYear(priMonth); // 더해질 년도 체크

            if(todayAndPriTodayCompare(todays, priMonth%12, priDay, priYear)) {
                answer.add(i+1);
            }
        }
        return answer;
    }

    public static boolean todayAndPriTodayCompare(List<Integer> todays, int priMonth, int priDay, int priYear) {
        int compareYear = todays.get(0) - priYear;
        int compareMonth = (todays.get(1) + compareYear * 12) - priMonth;
        int compareDay = (todays.get(2) + compareMonth * 28) - priDay;
        if(compareDay > 0) {
            return true;
        }
        return false;
    }

    // 더해지는 년도 체크
    public static int createYear(int priMonth) {
        return priMonth/12;
    }

    // 오늘 날짜 * 28해서 현재 위치 찾기
    public static int calDay(List<Integer> days) {
        return days.get(1) * 28 + days.get(2);
    }

    // 유효기간 계산 * 28해서
    public static int calTerms(HashMap<String, Integer> termsMap, String key) {
        return termsMap.get(key) * 28;
    }

    // 년, 월, 일을 숫자로 변경하여 리스트로 담기
    public static List<Integer> daySplit(String day) {
        // 년, 월, 일
        String tempYear = day.substring(0,4);
        String tempMonth = day.substring(5,7);
        String tempDay = day.substring(8,10);
        List<Integer> days = new LinkedList<>();
        days.add(Integer.parseInt(tempYear));
        days.add(Integer.parseInt(tempMonth));
        days.add(Integer.parseInt(tempDay));
        return days;
    }

    // 약관 종류별 유효기간
    public static HashMap<String, Integer> createTerms(String[] terms) {
        HashMap<String, Integer> termsMap = new HashMap<>();
        for(String value : terms){
            String[] term = value.split(" ");
            termsMap.put(term[0], Integer.parseInt(term[1]));
        }
        return termsMap;
    }

}
