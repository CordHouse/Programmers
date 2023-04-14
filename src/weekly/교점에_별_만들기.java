package weekly;
import java.util.*;

public class 교점에_별_만들기 {
    // 이미 확인한 쌍인지 확인하기 위해 List 생성
    // ex) (0,0) 이 이미 나왔으면 교차한 점으로 구한 값임
    private static List<String> pointCheck = new ArrayList<>();
    // 중복되지 않는 좌표 쌍을 담는 큐
    private static Queue<Point> pointQueue = new LinkedList<>();
    private static long xMin = Long.MAX_VALUE, xMax = Long.MIN_VALUE;
    private static long yMin = Long.MAX_VALUE, yMax = Long.MIN_VALUE;
    public static void main(String[] args) {
        int[][] line = {{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}};
        System.out.println(Arrays.toString(solution(line)));
    }
    private static String[][] map;
    public static String[] solution(int[][] line) {
        // 들어오는 두 직선의 모든 조합을 만든다.
        for(int i = 0; i < line.length; i++) {
            for(int j = i+1; j < line.length; j++) {
                long x1 = line[i][0], y1 = line[i][1], c1 = line[i][2];
                long x2 = line[j][0], y2 = line[j][1], c2 = line[j][2];
                // 두 직선이 일치한다면 교차하는 한 점이 생기지 않음으로 패스
                if(matchCheck(x1, y1, x2, y2)) {
                    continue;
                }
                // 두 직선을 넣었을 때 실수가 발생한다면 조건에서 제외하라고 했기 때문에 정수인 좌표만 확인한다.
                if(!floatCheck((y1*c2)-(c1*y2), (x1*y2)-(y1*x2)) && !floatCheck((c1*x2)-(x1*c2), (x1*y2)-(y1*x2))) {
                    // 리스트 좌표쌍을 넣으면서 두 직선이 교차하는 점인지 확인한다.
                    listCheck(crossPoint(x1, y1, c1, x2, y2, c2));
                }
            }
        }
        // map에 담을 변수 선언을 위해 x, y 좌표의 최대 크기를 구한다.
        int xLength = (int) (xMax-xMin+1);
        int yLength = (int) (yMax-yMin+1);
        // 센터 값을 찾은 이유는 x, y축의 그래프 처럼 나오기 때문에 배열에 대입하기 위해서 값을 구한다.
        // 최대 길이의 절반이 중간 값이다. -> (0, 0)
        long centerX = xLength/2;
        long centerY = yLength/2;
        map = new String[yLength][xLength];
        // 맵을 초기화 해서 모두 "." 으로 다 채워준다.
        mapInit(xLength, yLength);
        // 이후 큐 안에 값을 뽑아오면서 "*" 로 치환해준다.
        while(!pointQueue.isEmpty()) {
            Point point = pointQueue.poll();
            // 모든 경우의 주의사항 -> 2차원 배열안에 값을 찾아갈때 타입을 주의해줘야 한다. 안그러면 런타임에러 발생
            // 경우 1 : map 안에 x 좌표가 0인 경우는 배열이 [[]] 이런 형태이기 때문에 이런 경우만
            // y 값만 계산해서 적용시킨다.
            if(centerX == 0) {
                int y = Math.abs((int)(point.y - yMax));
                map[y][(int)centerX] = "*";
            }
            // 경우 2 : map 안에 y 좌표가 0인 경우
            // y 값만 계산해서 적용시킨다.
            else if(centerY == 0) {
                int x = Math.abs((int)(point.x - xMin));
                map[(int)centerY][x] = "*";
            }
            // 이외 모든 경우는 아래와 같이 진행한다.
            else {
                map[Math.abs((int)(point.y - yMax))][Math.abs((int)(point.x - xMin))] = "*";
            }
        }
        // 이제 값을 String으로 만들어 원하는 결과 값을 출력한다.
        String[] answer = new String[map.length];
        for(int i = 0; i < map.length; i++) {
            StringBuilder result = new StringBuilder();
            for(String value : map[i]) {
                result.append(value);
            }
            answer[i] = result.toString();
        }

        return answer;
    }

    // 두 직선이 평행 또는 일치하는지 확인하는 함수
    public static boolean matchCheck(long A, long B, long C, long D) {
        return (A*D)-(B*C) == 0;
    }
    // 두 직선의 교점이 유일하게 존재하는 경우 좌표 쌍을 포인트 클래스에 담는 함수
    public static Point crossPoint(long x1, long y1, long c1, long x2, long y2, long c2) {
        long x = (((y1*c2)-(c1*y2)) / ((x1*y2)-(y1*x2)));
        long y = (((c1*x2)-(x1*c2)) / ((x1*y2)-(y1*x2)));
        return new Point(x, y);
    }
    // 실수 좌표를 확인하는 함수
    public static boolean floatCheck(long up, long down) {
        if(up == 0L || up % down == 0L) {
            return false;
        }
        return true;
    }
    // 좌표를 "x, y" 형식으로 담아 이미 있는 쌍인지 확인하는 함수
    public static void listCheck(Point point) {
        StringBuilder value = new StringBuilder();
        value.append(point.x).append(", ").append(point.y);
        if(!pointCheck.contains(value.toString())) {
            xMin = Math.min(xMin, point.x);
            yMin = Math.min(yMin, point.y);
            xMax = Math.max(xMax, point.x);
            yMax = Math.max(yMax, point.y);
            pointCheck.add(value.toString());
            pointQueue.add(point);
        }
    }
    // 맵 2차원 배열을 초기화하는 함수
    public static void mapInit(int xLength, int yLength) {
        for(int i = 0; i < yLength; i++) {
            for(int j = 0; j < xLength; j++) {
                map[i][j] = ".";
            }
        }
    }
}
// 좌표 쌍을 담을 포인트 클래스
class Point {
    long x;
    long y;
    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}
