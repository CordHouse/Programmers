import java.util.*;
public class 디펜스_게임 {

    public static void main(String[] args) {
        int n = 7;
        int k = 3;
        int[] enemy = {4, 2, 4, 5, 3, 3, 1};
        System.out.println(solution(n,k,enemy));
    }

    public static int solution(int n, int k, int[] enemy) {
        int answer = enemy.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < enemy.length; i++) {
            n -= enemy[i];
            queue.add(enemy[i]);

            if(n < 0) {
                if(k > 0 && !queue.isEmpty()) {
                    n += queue.poll();
                    k--;
                }
                else {
                    return i;
                }
            }
        }
        return answer;
    }
}
