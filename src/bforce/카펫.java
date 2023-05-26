package bforce;
import java.util.*;

public class 카펫 {
    public static void main(String[] args) {
        int brown = 18;
        int yellow = 6;
        System.out.println(Arrays.toString(solution(brown, yellow)));
    }
    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int max = Math.max(brown, yellow); // 주어진 수 중 가장 큰 숫자를 선택한다.
        int sum = brown + yellow;
        boolean[] check = new boolean[max+1];
        for(int i = max; i > 1; i--) { // 가장 큰 수를 기준으로 하나씩 내려가면서 약수를 찾는다.
            if(sum % i == 0 && !check[i] && (sum / i) >= i) {
                check[i] = true; // 약수를 찾았다면 표시해준다.
            }
        }
        for(int i = 3; i < check.length; i++) { // 3부터 시작하는 이유는 가로 혹은 세로가 2개 이상 되어야지 안에 값을 채울 수 있기 때문
            if(check[i] && sum % i == 0) {
                if((sum/i) >= i && ((sum/i)-2)*(i-2) == yellow) { // 뒤에 계산식은 위 아래, 좌 우를 하나씩 줄여서 그 공간만큼 갯수를 채워보기 위함이다.
                    answer[0] = sum / i;
                    answer[1] = i;
                }
            }
        }
        return answer;
    }
}
