package kakao;

import java.util.LinkedList;
import java.util.Queue;

public class kakaoTest5 {
    public static void main(String[] args) {
//        int[] queue1 = {1,2,3,4,6}, queue2 = {2,3,4,5,6};
//        int[] queue1 = {1,2,1,2}, queue2 = {1,10,1,2};
        int[] queue1 = {1,3}, queue2 = {4,4};
        int answer = -2;
        int count = 0;
        long total_sum = 0;
        int sum1=0, sum2=0;
        Queue<Integer> queue_link1 = new LinkedList<>();
        Queue<Integer> queue_link2 = new LinkedList<>();
        for(int i = 0; i<queue1.length; i++){
            queue_link1.add(queue1[i]);
            queue_link2.add(queue2[i]);
            total_sum += queue1[i] + queue2[i];
            sum1 += queue1[i];
            sum2 += queue2[i];
        }
        int len_sum = queue1.length + queue2.length;
        for(int i = 0; i < len_sum; i++){
            if(total_sum/2 == sum1 && total_sum/2 == sum2)
                break;
            else if(total_sum/2 < sum1){
                sum2 += queue_link1.peek();
                sum1 -= queue_link1.peek();
                queue_link2.offer(queue_link1.remove());
                count += 1;
            }
            else if(total_sum/2 > sum1){
                sum1 += queue_link2.peek();
                sum2 -= queue_link2.peek();
                queue_link1.offer(queue_link2.remove());
                count += 1;
            }
            else if(total_sum/2 == sum1 && total_sum/2 != sum2){
                break;
            }
            else if(total_sum/2 == sum2 && total_sum/2 != sum1){
                break;
            }
        }
        if(sum1 != sum2 || count == 0){
            count = -1;
        }
        answer = count;
        System.out.println(answer);
    }
}
