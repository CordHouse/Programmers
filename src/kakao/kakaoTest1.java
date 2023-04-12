package kakao;

import java.util.Arrays;
import java.util.HashMap;

public class kakaoTest1 {
    public static void main(String[] args) {
//        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        String[] record = {"Enter uid234 M", "Enter uid333 P", "Enter uid123 D", "Leave uid234", "Change uid123 A", "Leave uid123"};
        String[] answer = {};
        int count = 0, answer_count = 0;
        for(int i = 0; i < record.length; i++){
            if(!record[i].contains("Change"))
                count++;
        }
        String[] tmp = new String[count];
        HashMap<String, String> hashMap = new HashMap<String, String>();

        for(int i = 0; i< record.length; i++){
            if(record[i].contains("Enter") || record[i].contains("Change")) {
                String[] record_search = record[i].split(" ");
                hashMap.put(record_search[1], record_search[2]);
            }
        }

        for(int i = 0; i< record.length; i++){
            String[] record_output = record[i].split(" ");
            if(record[i].contains("Enter")){
                if(hashMap.containsKey(record_output[1])){
                    tmp[answer_count] = hashMap.get(record_output[1])+"님이 들어왔습니다.";
                    answer_count++;
                }
            }
            else if(record[i].contains("Leave")){
                if(hashMap.containsKey(record_output[1])){
                    tmp[answer_count] = hashMap.get(record_output[1])+"님이 나갔습니다.";
                    answer_count++;
                }
            }
        }
        answer = tmp;
        System.out.println(Arrays.toString(answer));
        for(String i : answer)
            System.out.println(i);

    }
}
