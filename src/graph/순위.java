package graph;
import java.util.*;

public class 순위 {
    public static void main(String[] args) {
        int n = 5;
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        System.out.println(solution(n, results));
    }
    public static int solution(int n, int[][] results) {
        int answer = 0;
        int[][] map = new int[n+1][n+1];

        for(int i = 0; i < results.length; i++) { // 배열 초기화 1 -> 이긴 경우 / -1 -> 진 경우
            map[results[i][0]][results[i][1]] = 1;
            map[results[i][1]][results[i][0]] = -1;
        }

        for(int i = 1; i <= n; i++) { // 시작
            for(int j = 1; j <= n; j++) { // 종료
                for(int k = 1; k <= n; k++) { // 중간점
                    if(map[i][k] == 1 && map[k][j] == 1) {
                        map[i][j] = 1;
                        map[j][i] = -1;
                    }
                    if(map[i][k] == -1 && map[k][j] == -1) {
                        map[j][i] = 1;
                        map[i][j] = -1;
                    }
                }
            }
        }

        // 최종적으로 n-1번 승부가 난 사람은 최종적인 순위를 알 수 있다.
        for(int i = 1; i <= n; i++) {
            int count = 0;
            for(int j = 1; j <= n; j++) {
                if(map[i][j] != 0) {
                    count++;
                }
            }
            if(count == n-1) {
                answer++;
            }
        }

        return answer;
    }

}
