package highKit.dfs_bfs;

import java.util.LinkedList;
import java.util.Queue;

public class 게임_맵_최단거리 {
    static int[][] map;
    static boolean[][] check;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;
    static int n, m;

    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        System.out.println(solution(maps));
    }

    public static int solution(int[][] maps) {
        int answer = -1;
        n = maps.length;
        m = maps[0].length;
        map = new int[n+1][m+1];
        check = new boolean[n+1][m+1];

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                map[i][j] = maps[i-1][j-1];
            }
        }

        bfs(1,1,n,m);
        answer = min;
        return answer;
    }

    public static void bfs(int startX, int startY, int endX, int endY){
        int distance = 1;
        Queue<Position> queue = new LinkedList<>();
        check[startX][startY] = true;
        queue.add(new Position(startX, startY, distance));

        while(!queue.isEmpty()){
            Position position = queue.poll();

            for(int i = 0; i<4; i++){
                int nx = dx[i] + position.x;
                int ny = dy[i] + position.y;
                distance = position.distance;

                if(nx > 0 && ny > 0 && nx <= n && ny <= m){
                    distance += 1;
                    if(map[nx][ny] == 1 && nx == n && ny == m){
                        min = Math.min(distance, min);
                    }
                    else if(!check[nx][ny] && map[nx][ny] == 1){
                        check[nx][ny] = true;
                        queue.add(new Position(nx, ny, distance));
                    }
                }
            }
        }
        if(min == Integer.MAX_VALUE){
            min = -1;
        }
    }
    static class Position{
        int x;
        int y;
        int distance;
        public Position(int x, int y, int distance){
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

}
