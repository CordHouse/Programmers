import java.util.*;
public class 무인도_여행 {
    // 좌표를 담을 테이블과 테이블 체크 배열 변수를 선언한다.
    private static String[][] table;
    private static boolean[][] tableCheck;
    private static int xLength, yLength; // x의 크기, y의 크기를 담는다.
    private static int max; // 식량의 최대 값을 담는 변수
    private static int[] dx = {-1,0,1,0}, dy = {0,1,0,-1}; // 상하좌우
    private static PriorityQueue<Integer> queue = new PriorityQueue<>(); // 우선순위 큐를 사용하여 오름차순 정리

    public static void main(String[] args) {
        String[] maps = {"X591X","X1X5X","X231X", "1XXX1"};
        System.out.println(Arrays.toString(solution(maps)));
    }

    public static int[] solution(String[] maps) {
        xLength = maps.length;
        yLength = maps[0].length();
        table = new String[xLength][yLength];
        tableCheck = new boolean[xLength][yLength];

        // 배열 초기화 진행
        for(int i = 0; i < xLength; i++) {
            String[] map = maps[i].split("");
            for(int j = 0; j < yLength; j++) {
                table[i][j] = map[j];
                if(map[j].equals("X")) {
                    tableCheck[i][j] = true;
                }
            }
        }

        // dfs함수를 통해 무인도 하나씩 최대 식량을 모두 구한다.
        for(int i = 0; i < xLength; i++) {
            for(int j = 0; j < yLength; j++) {
                if(!table[i][j].equals("X") && !tableCheck[i][j]) {
                    max = Integer.parseInt(table[i][j]);
                    dfs(i, j);
                    queue.add(max);
                }
            }
        }
        // 큐가 비었다면, X만 있는 것이기 때문에 리턴은 -1
        if(queue.isEmpty()) {
            return new int[]{-1};
        }

        // 이후 answer에 큐에 있는 값만큼 오름차순으로 넣어준다.
        int[] answer = new int[queue.size()];
        int count = 0;
        while(!queue.isEmpty()) {
            answer[count++] = queue.poll();
        }

        return answer;
    }

    public static void dfs(int x, int y) {
        tableCheck[x][y] = true;

        for(int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if(nx >= 0 && ny >= 0 && nx < xLength && ny < yLength) {
                if(!tableCheck[nx][ny]) {
                    tableCheck[nx][ny] = true;
                    max += Integer.parseInt(table[nx][ny]);
                    dfs(nx, ny);
                }
            }
        }
    }
}
