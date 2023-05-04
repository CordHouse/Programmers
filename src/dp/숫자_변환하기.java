package dp;

public class 숫자_변환하기 {
    private static int[] dp;
    public static void main(String[] args) {
        int x = 10;
        int y = 40;
        int n = 5;
        System.out.println(solution(x, y, n));
    }
    public static int solution(int x, int y, int n) {
        int answer = 0;
        // dp[40] 을 만들기 위해 +1 만큼 공간을 잡아준다.
        dp = new int[y+1];

        // 시작은 x 인 순간부터 y 가 될때까지 반복해준다.
        for(int i = x; i <= y; i++) {
            // 처음 값이 dp[x] 이기 때문에 이 값은 0이다. 밑에 조건문을 돌면서 해당 이후 값들을 새로 설정해준다.
            // 따라서 해당 값들이 올땐 뒤에 dp[i] == 0 조건이 만족하지 않기 때문에 밑으로 내려가서 다음 조건을 보게 된다.
            if(i != x && dp[i] == 0) {
                dp[i] = -1;
                continue;
            }
            // 모든 조건들은 계산한 위치가 y보다 작은 경우 동작한다.
            // 그 이외엔 -1이다.
            if(i+n <= y) {
                if(dp[i+n] == 0) {
                    dp[i+n] = dp[i]+1;
                }
                else {
                    dp[i+n] = Math.min(dp[i]+1, dp[i+n]);
                }
            }
            if(i * 2 <= y) {
                if(dp[i*2] == 0) {
                    dp[i*2] = dp[i]+1;
                }
                else {
                    dp[i*2] = Math.min(dp[i]+1, dp[i*2]);
                }
            }
            if(i * 3 <= y) {
                if(dp[i*3] == 0) {
                    dp[i*3] = dp[i]+1;
                }
                else {
                    dp[i*3] = Math.min(dp[i]+1, dp[i*3]);
                }
            }
        }
        answer = dp[y];
        return answer;
    }
}
