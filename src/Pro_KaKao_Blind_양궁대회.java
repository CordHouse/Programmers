import java.util.*;
public class Pro_KaKao_Blind_양궁대회 {
    static int[] res = {-1}; // 응답
    static int[] lion; // 라이언에 담길 배열
    static int max = -1000;
    public static void main(String[] args) {
        int n = 5;
        int[] info = {2,1,1,1,0,0,0,0,0,0,0};
        solution(n, info);
    }
    public static int[] solution(int n, int[] info) {
        lion = new int[11];
        // info : 어피치가 맞춘 점수 배열
        // cnt : 층의 개수
        // n : 화살의 개수
        dfs(info, 0, n);
        return res;
    }
    // 백트래킹
    // dfs -> 모든 경우에 수에서 하나씩 다 해보기 위해서 선언
    // 항시 첫 문장엔 종료문이 있어야함
    // 동작문에선 넣었던 값을 빼주는 작업을 다시 해야함
    public static void dfs(int[] info, int cnt, int n){
        // 종료문
        if(cnt == n){
            int apeach_point = 0;
            int lion_point = 0;
            for(int i = 0; i <= 10; i++){
                // apeach : 점수가 있든지, lion : 점수가 있던지
                if(info[i] != 0 || lion[i] != 0) {
                    // 점수 부여 (10 - i) -> 점수가 10점부터 내려가기 때문
                    // 점수 누적 lion_point, apeach_point
                    if(info[i] < lion[i]) {
                        lion_point += 10 - i;
                    }
                    else {
                        apeach_point += 10 - i;
                    }
                }
            }
            // lion 점수와 apeach 점수를 비교하여 res에 적립
            // lion은 클론으로 복사
            // max 값은 최종 점수의 합 -> lion 이 apeach보다 항상 클때 고려하면 됨
            if(lion_point > apeach_point) {
                if(lion_point - apeach_point >= max) {
                    res = lion.clone();
                    max = lion_point - apeach_point;
                }
            }
            return ;
        }
        
        // 동작문
        for(int j = 0; j<=10 && lion[j] <= info[j]; j++){
            lion[j]++;
            dfs(info, cnt + 1, n);
            lion[j]--;
        }
    }
}
