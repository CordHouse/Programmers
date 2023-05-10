import java.util.*;
public class 요격_시스템 {
    public static void main(String[] args) {
        int[][] targets = {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}};
        System.out.println(solution(targets));
    }

    public static int solution(int[][] targets) {
        int answer = 1; // 최초 1회는 사격해야 하기 때문에 1로 초기화
        PriorityQueue<Integer> queue = new PriorityQueue<>(); // 큐 안에 값은 종료시간을 기준으로 가장 낮은 순위가 출력되야 한다.
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]); // 종료시간 기준으로 오름차순
        // 왜 종료시간 기준으로 오름차순 정렬을 해야할까?
        // 그림을 보면 종료시간을 기준으로 요격은 항상 겹치는 부분을 요격할 수 있기 때문이다.

        queue.add(targets[0][1]); // 최초 값 삽입
        for(int i = 1; i < targets.length; i++) {
            // 최초 값의 종료시간 대비 타켓 시작 시간이 크다면 값을 추가시키고 새로운 종료 값으로 갱신시켜준다.
            if(queue.peek() <= targets[i][0]) {
                answer++;
                queue.poll();
                queue.add(targets[i][1]);
            }
        }

        return answer;
    }
}
