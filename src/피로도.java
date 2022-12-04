import java.util.*;
public class 피로도 {
    static List<int[][]> changeDungeons = new LinkedList<>();
    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80,20},{50,40},{30,10}};
        System.out.println(solution(k, dungeons));
    }
    public static int solution(int k, int[][] dungeons) {
        int answer = 0;
        boolean[] check = new boolean[dungeons.length];
        List<int[]> output = new LinkedList<>();

        change(dungeons, output, check, 0);

        for(int i = 0; i < changeDungeons.size(); i++){
            answer = maxResult(answer, changeDungeons.get(i), k);
        }
        return answer;
    }

    public static int maxResult(int answer, int[][] dungeons, int k){
        int count = 0;
        for(int i = 0; i < dungeons.length; i++){
            if(k >= dungeons[i][0]){
                k -= dungeons[i][1];
                count++;
            }
            else
                break;
        }
        return Math.max(answer, count);
    }

    public static void change(int[][] dungeons, List<int[]> output, boolean[] check, int depth){
        if(depth == dungeons.length){
            int[][] tmp = new int[dungeons.length][2];
            for(int i = 0; i < output.size(); i++){
                tmp[i][0] = output.get(i)[0];
                tmp[i][1] = output.get(i)[1];
            }
            changeDungeons.add(tmp);
            return;
        }
        for(int i = 0; i < dungeons.length; i++){
            if(!check[i]){
                output.add(dungeons[i]);
                check[i] = true;
                change(dungeons, output, check, depth+1);
                check[i] = false;
                output.remove(output.size()-1);
            }
        }
    }

}
