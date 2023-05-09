import java.util.*;
public class 미로_탈출 {
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static int xSize, ySize;
    private static String[][] map;
    private static boolean[][] check;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
        System.out.println(solution(maps));
    }
    public static int solution(String[] maps) {
        int answer = -1;
        xSize = maps.length;
        ySize = maps[0].length();
        map = new String[xSize][ySize];
        check = new boolean[xSize][ySize];
        int sX = 0, sY = 0, eX = 0, eY = 0, lX = 0, lY = 0;

        // 배열에 정보를 담아주고, 방문하면 안되는 곳 체크한다.
        for(int i = 0; i < xSize; i++) {
            String[] line = maps[i].split("");
            for(int j = 0; j < line.length; j++) {
                if(line[j].equals("S")) {
                    sX = i;
                    sY = j;
                }
                else if(line[j].equals("E")) {
                    eX = i;
                    eY = j;
                }
                else if(line[j].equals("L")) {
                    lX = i;
                    lY = j;
                }
                else if(line[j].equals("X")) {
                    check[i][j] = true;
                }
                map[i][j] = line[j];
            }
        }

        // Start, Lever, Exit 3곳의 위치가 하나라도 어떤 방식으로도 갈 수 없는 곳이라면 -1 반환
        // 주의할 점은 밑에 함수에서 최소 값을 한 번 더 보아야 하는데 그 이유는
        // 해당 if문은 숫자가 엄청 컸을때 미리 확인하기 위한 작업임으로 밑에 함수 동작을 꼭 해줘야한다.
        if(aroundCheck(sX, sY) || aroundCheck(eX, eY) || aroundCheck(lX, lY)) {
            return answer;
        }

        // 시작 -> 레버
        startToEnd(sX, sY, lX, lY);
        int leverMin = min;
        // 레버 최소 값이 변하지 않았다면 L까지 못간다.
        if(leverMin == Integer.MAX_VALUE) {
            return answer;
        }

        // 레버 -> 종료
        min = Integer.MAX_VALUE;
        check = new boolean[xSize][ySize];
        startToEnd(lX, lY, eX, eY);
        int exitMin = min;
        // 종료 최소 값이 변하지 않았다면 Exit까지 못간다.
        if(exitMin == Integer.MAX_VALUE) {
            return answer;
        }

        // 모든 조건을 만족한다면 더한 값을 리턴
        answer = leverMin + exitMin;

        return answer;
    }

    // BFS를 활용하여 갈수있는 위치 탐색
    public static void startToEnd(int sX, int sY, int eX, int eY) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(sX, sY, 0));

        while(!queue.isEmpty()) {
            Point point = queue.poll();
            if(point.x == eX && point.y == eY) {
                min = Math.min(min, point.count);
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < xSize && ny < ySize) {
                    if(!check[nx][ny] && !map[nx][ny].equals("X")) {
                        check[nx][ny] = true;
                        queue.add(new Point(nx, ny, point.count+1));
                    }
                }
            }
        }
    }

    // 시작지점, 종료지점, 레버 3군대에 모두 o가 하나라도 있어야지 가능
    public static boolean aroundCheck(int x, int y) {
        int c = 0;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && ny >= 0 && nx < xSize && ny < ySize) {
                if(map[nx][ny].equals("X")) {
                    c++;
                }
            }
            else {
                c++;
            }
        }
        return c == 4;
    }
}
class Point{
    int x;
    int y;
    int count;
    public Point(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}

