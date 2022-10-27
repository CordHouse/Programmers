import java.util.*;
public class Pro_KaKao_두_큐_합_같게_만들기 {
    static long s1, s2;
    public static void main(String[] args) {
        int[] queue1 = {3, 2, 7, 2};
        int[] queue2 = {4, 6, 5, 1};
        System.out.println(solution(queue1, queue2));
    }
    public static int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        // Step 1. 입력 파라미터를 Queue 타입으로 담아주기 위해 선언
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        // Step 2. 입력 배열을 큐에 담아주기
        for(int value : queue1){
            q1.add(value);
        }

        for(int value : queue2){
            q2.add(value);
        }
        // Step 3. 합을 구하고 평균을 구한다. 단, 합의 절반이 정수여야 하기 때문에 홀수일때는 자동으로 -1를 리턴한다.
        s1 = sum(q1);
        s2 = sum(q2);
        long goal = (s1+s2);
        if(goal % 2 == 1)
            return -1;
        goal /= 2;

        // Step 4. 함수 호출
        answer = sameQueue(q1, q2, goal);

        return answer;
    }

    public static int sameQueue(Queue<Integer> q1, Queue<Integer> q2, long goal){
        // Step 5. 계산 -> s1과 s2의 합이 평균보다 큰지 작은지에 따라 움겨주고, 값이 같아지는 순간 리턴 그게 아니라면 -1리턴
        int p1 =0, p2 = 0;
        int limit = q1.size()*2;
        while(p1 <= limit && p2 <= limit){
            if(s1 == goal){
                return p1+p2;
            }
            if(s2 > goal){
                s1 += q2.peek();
                s2 -= q2.peek();
                q1.add(q2.poll());
                p1 += 1;
            }
            else{
                s2 += q1.peek();
                s1 -= q1.peek();
                q2.add(q1.poll());
                p2 += 1;
            }
        }

        return -1;
    }

    public static int sum(Queue<Integer> queue){
        int sum = 0;

        for(int value : queue){
            sum += value;
        }
        return sum;
    }
}

