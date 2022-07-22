import java.util.ArrayList;
import java.util.Scanner;

public class kakaoTest {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String new_id = in.next();
        String answer = "";
        // 여기가 소문자로 바꾸는 구간
        answer = new_id.toLowerCase();
        ArrayList<Character> bf_answer = new ArrayList<Character>();
        for(int s = 0; s< answer.length(); s++){
            bf_answer.add(answer.charAt(s));
        }
        // 여기가 해당하지 않는거 제거하는 구간
        for(int i = 0; i<bf_answer.size(); i++){
            if(bf_answer.get(i) != '-' && bf_answer.get(i) != '.' && bf_answer.get(i) != '_' && ((int) bf_answer.get(i) < 48 || (int) bf_answer.get(i) > 122 || ((int) bf_answer.get(i) > 57 && (int) bf_answer.get(i) < 97))) {
                bf_answer.remove(bf_answer.get(i));
                i -= 1;
            }
        }
        // 여기가 점 하나로 만드는 구간
        for(int i = 0; i<bf_answer.size()-1; i++){
            if(bf_answer.get(i) == '.' && bf_answer.get(i+1) == '.'){
                bf_answer.remove(i);
                i -= 1;
            }
        }
        if(bf_answer.size() != 0 && bf_answer.get(0) == '.')
            bf_answer.remove(0);

        // 빈 문자열이라면 a를 대입
        if(bf_answer.size() == 0)
            bf_answer.add('a');
        // 15자리 까지만 표현
        if(bf_answer.size() > 15){
            for(int i = 15; i<bf_answer.size(); i++){
                bf_answer.remove(i);
                i-=1;
            }
        }
        if(bf_answer.size() != 0 && bf_answer.get(bf_answer.size()-1) == '.')
            bf_answer.remove(bf_answer.size() - 1);
        // 글자수가 2보다 작으면 반복해서 3까지 표현
        if(bf_answer.size() <= 2) {
            for (int i = 0; i<=3; i++){
                if(bf_answer.size() == 3)
                    break;
                bf_answer.add(bf_answer.get(i));
            }
        }
        for(char i : bf_answer)
            System.out.print(i);
    }
}
