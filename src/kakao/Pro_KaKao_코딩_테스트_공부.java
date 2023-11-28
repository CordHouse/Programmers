package kakao;

public class Pro_KaKao_코딩_테스트_공부 {
    static int alpMax, copMax;
    static int[][] dp;

    public static void main(String[] args) {
        int alp = 10;
        int cop = 10;
        int[][] problems = {{10,15,2,1,2},{20,20,3,3,4}};
        System.out.println(solution(alp, cop, problems));
    }
    public static int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        // 최대 알고력과 코딩력 찾기 (종료 지점)
        for (int[] problem : problems) {
            if (alpMax < problem[0]) {
                alpMax = problem[0];
            }
            if (copMax < problem[1]) {
                copMax = problem[1];
            }
        }

        // 알고력과 코딩력이 주어질 때 문제들 보다 아예 큰 경우 바로 0 리턴
        if(alp > alpMax && cop > copMax) {
            return 0;
        }
        // 주의
        // 최대 알고력으로 갱신해줘야 하는 이유는 dp 공간에 배열이 최대 알고력과 코딩력을 기준으로 뽑아오기 때문에 갱신해줘야한다.
        // 최소 한번의 반복을 돌기 위해서
        if(alp > alpMax) {
            alp = alpMax;
        }
        if(cop > copMax) {
            cop = copMax;
        }

        // 이후 포문에 알고력과 코딩력이 20이라면 20번째 배열도 확인해야 되기 때문에 + 2만큼 배열을 더 잡는다.
        dp = new int[alpMax+2][copMax+2];
        for(int i = alp; i < alpMax + 1; i++) {
            for(int j = cop; j < copMax + 1; j++) {
                dp[i][j] = 1_000_000;
            }
        }

        dp[alp][cop] = 0;

        for(int i = alp; i <= alpMax; i++) {
            for(int j = cop; j <= copMax; j++) {
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 1);
                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);

                for(int[] pro : problems) {
                    // 문제의 알고력과 코딩력을 만족한다면 해당 조건문 실행
                    if(i >= pro[0] && j >= pro[1]) {
                        // 문제의 최대 알고력과 코딩력을 넘으면 인덱스 문제가 발생하기 때문에
                        // 아래와 같은 조건을 주어 최대 값을 넘지 않도록 조절해준다.
                        int newAlp = Math.min(i+pro[2], alpMax);
                        int newCop = Math.min(j+pro[3], copMax);

                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[i][j] + pro[4]);
                    }
                }
            }
        }

        answer = dp[alpMax][copMax];

        return answer;
    }
}
