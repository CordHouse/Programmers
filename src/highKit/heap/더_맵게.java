package highKit.heap;

import java.util.*;
public class 더_맵게 {
    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        System.out.println(solution(scoville, K));
    }

    public static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for(int scovil : scoville) {
            queue.add(scovil);
        }

        while(queue.size()>1) {
            if(queue.peek() >= K) {
                return answer;
            }
            int firstScovil = queue.poll();
            int secondsScovil = queue.poll() * 2;
            int sum = firstScovil + secondsScovil;
            queue.add(sum);
            answer++;
        }
        if(!queue.isEmpty()) {
            if(queue.peek() >= K) {
                return answer;
            }
        }

        return -1;
    }

}
