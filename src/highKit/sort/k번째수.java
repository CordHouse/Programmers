package highKit.sort;
import java.util.*;

public class k번째수 {
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        System.out.println(Arrays.toString(solution(array, commands)));
    }
    public static int[] solution(int[] array, int[][] commands) {
        int[] solu = new int[commands.length];

        for(int i = 0; i < commands.length; i++){
            int count = 0;
            int[] answer = new int[commands[i][1] - commands[i][0] + 1];
            for(int n1 = commands[i][0]-1; n1 < commands[i][1]; n1 ++){
                answer[count] = array[n1];
                count++;
            }
            Arrays.sort(answer);
            solu[i] = answer[commands[i][2]-1];
        }

        return solu;
    }
}
