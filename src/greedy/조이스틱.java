package greedy;
import java.util.*;

/**
 * 이 문제 풀이의 핵심은 두 가지 이다.
 * 1. 상 하로 움직이면서 해당 알파벳에서 차이나는 갯수를 찾아준다.
 * 2. 좌 우로 움직이는 방향을 최소한으로 움직여야 한다.
 * 2-1. 이 부분에서는 'A'가 있다면 무시해도 좋기 때문에 각 위치에 대해서 'A'가 연속적으로 나타나는 구간을 찾고
 * 해결하는 것이 관건이다.
 * 2-2. 따라서 방법은 총 3가지이다.
 * 3. 오른쪽으로 쭉 가는 경우, 오른쪽으로 갔다가 왼쪽으로 가는 경우, 왼쪽으로 갔다가 오른쪽으로 가는 경우
 */
public class 조이스틱 {
    public static void main(String[] args) {
        String name = "JEROEN";
        System.out.println(solution(name));
    }

    public static int solution(String name) {
        int answer = name.length()-1;
        int len = name.length();
        int[] arr = new int[len]; // 각 위치별 상 하로 움직인 갯수를 담아준다.

        for(int i = 0; i < len; i++) {
            char c = name.charAt(i);
            if(c != 'A') {
                arr[i] = searchAlpha(c); // 위치의 차이 중 작은 값을 반환하여 담기
            }
            if(i != len-1 && name.charAt(i+1) == 'A') { // 'A'의 연속성 판별
                int end = len;
                for(int j = i+1; j < len; j++) {
                    if(name.charAt(j) != 'A') { // 'A'가 아닌 구간을 찾는다면 연속이 끝난 것이다.
                        end = j;
                        break;
                    }
                }
                answer = Math.min(answer, i*2 + (len - end)); // 오른쪽으로 갔다가 왼쪽으로 가는 경우
                answer = Math.min(answer, (len - end)*2 + i); // 왼쪽으로 갔다가 오른쪽으로 가는 경우
            }
        }

        for(int v : arr) {
            answer += v;
        }
        return answer;
    }

    public static int searchAlpha(char curChar) {
        int up = Math.abs((int)'A' - (int)curChar);
        int down = Math.abs((int)'Z' - (int)curChar) + 1;
        return Math.min(up, down);
    }
}
