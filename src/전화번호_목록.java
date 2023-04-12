import java.util.*;
public class 전화번호_목록 {
    public static void main(String[] args) {
        String[] phone_book = {"119", "97674223", "1195524421"};
        System.out.println(solution(phone_book));
    }
    public static boolean solution(String[] phone_book) {
        boolean answer = true;

        Arrays.sort(phone_book);

        for(int i = 0; i < phone_book.length-1; i++) {
            int minSize = phone_book[i].length();
            if(minSize > phone_book[i+1].length()) {
                continue;
            }
            String phone = phone_book[i+1].substring(0, minSize);
            if(phone.equals(phone_book[i])) {
                answer = false;
                break;
            }
        }
        return answer;
    }
}
