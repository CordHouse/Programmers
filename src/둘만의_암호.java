import java.util.*;

public class 둘만의_암호 {
    public static void main(String[] args) {
        String s = "aukks";
        String skip = "wbqd";
        int index = 5;
        System.out.println(solution(s, skip, index));
    }

    public static String solution(String s, String skip, int index) {
        String answer = "";

        List<Character> alpha = createAlphabet();
        duplicateRemove(alpha, skip.toCharArray());
        answer = searchChangeAlphabet(s.toCharArray(), index, alpha);

        return answer;
    }

    public static List<Character> createAlphabet(){
        List<Character> createAlpha = new LinkedList<>();
        for(int i = 0; i < 26; i++){
            createAlpha.add((char)('a'+i));
        }
        return createAlpha;
    }

    public static void duplicateRemove(List<Character> alpha, char[] skip) {
        for(char value : skip){
            alpha.remove(alpha.indexOf(value));
        }
    }

    public static int indexReset(int current, int maxLength, int index) {
        int cyclePoint = current + index;
        return cyclePoint - maxLength * (cyclePoint/maxLength);
    }

    public static String searchChangeAlphabet(char[] s, int index, List<Character> alpa) {
        StringBuilder sb = new StringBuilder();
        for(char value : s){
            if(alpa.indexOf(value)+index >= alpa.size()) {
                sb.append(alpa.get(indexReset(alpa.indexOf(value), alpa.size(), index)));
                continue;
            }
            sb.append(alpa.get(alpa.indexOf(value)+index));
        }
        return sb.toString();
    }

}
