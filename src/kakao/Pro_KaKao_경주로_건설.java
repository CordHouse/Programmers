package kakao;

import java.util.Arrays;

public class Pro_KaKao_경주로_건설 {
    static int[] dx = {0, 1, 0, -1, 0}, dy = {0, 0, -1, 0, 1};
    static int[][] dp;
    static boolean[][] visit;
    static int wall;
    static final int DIRECT = 100, CORNER = 500;

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println(solution(board));
    }

    public static int solution(int[][] board) {
        int answer = 0;
        wall = board.length;
        visit = new boolean[wall][wall];
        dp = new int[wall][wall];
        for (int i = 0; i < wall; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        visit[0][0] = true;
        dfs(board, 0, 0, 0);
        answer = dp[wall - 1][wall - 1];
        return answer;
    }

    public static void dfs(int[][] board, int x, int y, int corner) {
        if (x == wall - 1 && y == wall - 1) {
            return;
        }

        for (int i = 1; i <= 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < wall && ny < wall) {
                if (!visit[nx][ny] && board[nx][ny] != 1) {
                    if (corner == 0 || corner % 2 == i % 2) {
                        if (dp[x][y] + DIRECT < dp[wall - 1][wall - 1]) {
                            dp[nx][ny] = dp[x][y] + DIRECT;
                            visit[nx][ny] = true;
                            dfs(board, nx, ny, i);
                            visit[nx][ny] = false;
                        }
                    } else {
                        if (dp[x][y] + DIRECT + CORNER < dp[wall - 1][wall - 1] && dp[x][y] <= dp[nx][ny]) {
                            dp[nx][ny] = dp[x][y] + DIRECT + CORNER;
                            visit[nx][ny] = true;
                            dfs(board, nx, ny, i);
                            visit[nx][ny] = false;
                        }
                    }
                }
            }
        }
    }

    public static class Move {
        int x, y, cost, corner;

        public Move(int x, int y, int cost, int corner) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.corner = corner;
        }
    }
}
