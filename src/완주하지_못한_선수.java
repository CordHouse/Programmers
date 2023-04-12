import java.util.*;
public class 완주하지_못한_선수 {
    public static void main(String[] args) {
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};
        System.out.println(solution(participant, completion));
    }

    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> nameTable = new HashMap<>();

        for(String joinName : participant) {
            if(!nameTable.containsKey(joinName)) {
                nameTable.put(joinName, 1);
                continue;
            }
            nameTable.put(joinName, nameTable.get(joinName)+1);
        }

        for(String completionName : completion) {
            if(nameTable.containsKey(completionName)) {
                nameTable.put(completionName, nameTable.get(completionName)-1);
            }
            if(nameTable.get(completionName) == 0) {
                nameTable.remove(completionName);
            }
        }

        List<String> notComplet = new ArrayList<>(nameTable.keySet());
        answer = notComplet.get(0);

        return answer;
    }

}
