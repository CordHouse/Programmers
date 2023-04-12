package highKit.sort;
import java.util.*;

public class H_Index {
    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
        System.out.println(solution(citations));
    }
    public static int solution(int[] citations) {
        int answer = 0;
        int count = 0;
        Arrays.sort(citations);

        for(int i = citations.length-1; i >= 0; i--) {
            if(citations[i] >= ++count) {
                answer = count;
                continue;
            }
            break;
        }
        return answer;
    }
}
