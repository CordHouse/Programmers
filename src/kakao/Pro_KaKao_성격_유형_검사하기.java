package kakao;

import java.util.*;
public class Pro_KaKao_성격_유형_검사하기 {
    public static void main(String[] args) {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};

        System.out.println(solution(survey, choices));
    }

    public static String solution(String[] survey, int[] choices) {
        String answer = "";

        HashMap<String, Integer> map = new HashMap<>();

        map.put("R", 0);
        map.put("T", 0);
        map.put("C", 0);
        map.put("F", 0);
        map.put("J", 0);
        map.put("M", 0);
        map.put("A", 0);
        map.put("N", 0);

        for(int i = 0; i < survey.length; i++){
            String first = String.valueOf(survey[i].charAt(0));
            String second = String.valueOf(survey[i].charAt(1));
            if(choices[i] < 4){
                map.put(first, map.get(first)+(4-choices[i]));
            }
            else if(choices[i] == 4){
                continue;
            }
            else{
                map.put(second, map.get(second)+(choices[i]-4));
            }
        }

        answer = mbti(map);

        return answer;
    }

    // mbti 검사하기
    public static String mbti(HashMap<String, Integer> map){
        String[] rcja = {"R", "C", "J", "A"};
        String[] tfmn = {"T", "F", "M", "N"};
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < rcja.length; i++){
            if(map.get(rcja[i]) > map.get(tfmn[i])){
                ans.append(rcja[i]);
            }
            else if(map.get(rcja[i]) < map.get(tfmn[i])){
                ans.append(tfmn[i]);
            }
            else{
                ans.append(rcja[i]);
            }
        }
        return ans.toString();
    }

}
