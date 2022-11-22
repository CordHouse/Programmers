import java.util.*;

public class 옹알이_2 {
    public static void main(String[] args) {
        String[] babbling = {"aya", "yee", "u", "maa"};
        System.out.println(solution(babbling));
    }

    public static int solution(String[] babbling) {
        int answer = 0;
        List<String> words = new LinkedList<>(List.of("aya", "ye", "woo", "ma"));

        answer = blinkCount(changeWord(babbling, words));

        return answer;
    }

    public static String[] changeWord(String[] babbling, List<String> words){
        for(int i = 0; i < babbling.length; i++){
            for(int j = 0; j < words.size(); j++){
                babbling[i] = babbling[i].replaceAll(words.get(j), String.valueOf(j));
            }
        }
        return babbling;
    }

    public static int blinkCount(String[] changeWord){
        int count = 0;
        String tmp = "^[0-9]+$";
        List<String> check = new LinkedList<>(List.of("0", "1", "2", "3"));
        for(String value : changeWord){
            if(value.matches(tmp)){
                char[] wordChar = value.toCharArray();
                if(wordChar.length == 1){
                    count++;
                }else{
                    for(int i = 0; i < wordChar.length-1; i++){
                        if(wordChar[i] == wordChar[i+1]){
                            break;
                        }
                        if(i+1 == wordChar.length-1){
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
