import java.util.*;

public class Pro_KaKao_Blind_소수_구하기 {
    public static void main(String[] args) {
        int n = 437674;
        int k = 3;
        System.out.println(solution(n, k));
    }
    public static int solution(int n, int k) {
        int answer = 0;
        // Step 1. 변수 선언
        String nStr = Integer.toString(n, k); // k진수로 변환해 문자열로 저장
        StringTokenizer st = new StringTokenizer(nStr, "0");
        int count = 0;

        // Step 2. 반복 -> 토큰이 있는 만큼 list에 값 넣음
        while(st.hasMoreTokens()){
            if(prime(Long.valueOf(st.nextToken()))){
                continue;
            }
            else{
                count++;
            }
        }

        if(count!=0)
            answer = count;
        return answer;
    }
    // Step 3. 소수 찾기
    public static boolean prime(Long number){
        if(number < 2)
            return true;

        for(int i = 2; i <= Math.sqrt(number); i++){
            if(number % i == 0)
                return true;
        }
        return false;
    }

}

