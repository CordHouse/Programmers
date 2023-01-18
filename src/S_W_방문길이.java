import java.util.*;
public class S_W_방문길이 {
    private static int[] px = {1,0,-1,0};
    private static int[] py = {0,-1,0,1};
    private static String[] position = {"R", "D", "L", "U"};
    private static List<String> cmd;
    private static List<List<Integer>> movePoint = new LinkedList<>();
    private static int cx = 5, cy =5;
    private static int distance = 0;

    public static void main(String[] args) {
        String dirs = "ULURRDLLU";
        System.out.println(solution(dirs));
    }
    public static int solution(String dirs) {
        cmd = changeStringToList(dirs);
        for(String value : cmd){
            distanceCount(value);
        }
        return distance;
    }

    public static List<String> changeStringToList(String dirs){
        List<String> stringToList = new LinkedList<>();
        char[] tmp = dirs.toCharArray();
        for(char value : tmp){
            stringToList.add(String.valueOf(value));
        }
        return stringToList;
    }

    public static void distanceCount(String dirs){
        List<Integer> currentPoint = new LinkedList<>();
        List<Integer> reversePoint = new LinkedList<>();
        if(cx >= 0 && cy >= 0 && cx <= 10 && cy <= 10){
            for(int i = 0; i < 4; i++){
                if(dirs.equals(position[i])){
                    if(cx + px[i] >= 0 && cy + py[i] >= 0 && cx + px[i] <= 10 && cy + py[i] <= 10){
                        currentPoint.add(cx);
                        currentPoint.add(cy);
                        cx += px[i];
                        cy += py[i];
                        currentPoint.add(cx);
                        currentPoint.add(cy);
                        reversePoint.add(currentPoint.get(2));
                        reversePoint.add(currentPoint.get(3));
                        reversePoint.add(currentPoint.get(0));
                        reversePoint.add(currentPoint.get(1));
                        if(!movePoint.contains(currentPoint) && !movePoint.contains(reversePoint)){
                            movePoint.add(currentPoint);
                            movePoint.add(reversePoint);
                            distance += 1;
                        }
                    }
                }
            }
        }
    }
}
