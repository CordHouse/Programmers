import java.util.*;
public class 호텔_대실 {

    public static void main(String[] args) {
        String[][] book_time = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        System.out.println(solution(book_time));
    }

    public static int solution(String[][] book_time) {
        int answer = 1;
        // 주어진 문자열 시간을 정수형 2차원 배열에 담기
        int[][] reserve = new int[book_time.length][2];

        // 문자열이기 때문에 콜론을 기준으로 스플릿 해준다!
        // 시간과 분으로 나눠지기 때문!
        for(int i = 0; i < book_time.length; i++) {
            String[] start = book_time[i][0].split(":");
            reserve[i][0] = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
            String[] end = book_time[i][1].split(":");
            reserve[i][1] = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]) + 10;
        }

        // 정렬을 사용하였는데, 이전 백준에서 적용한 방식과 동일한 방식으로
        // 시작 시간이 같다면 끝나는 시간을 기준으로 정렬하도록 설정
        Arrays.sort(reserve, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        // 큐를 만들고 시작시간과 끝나는 시간을 클래스 형태에 맞춰 넣어준다.
        Queue<SetTime> queue = new LinkedList<>();
        for(int i = 0; i < reserve.length; i++) {
            SetTime setTime = new SetTime(reserve[i][0], reserve[i][1]);
            queue.add(setTime);
        }

        // 맨 처음 끝나는 시간을 기준으로 반복해야하기 때문에 먼저 큐에서 뽑아준다.
        SetTime first = queue.poll();
        int count = 0; // 카운트는 first와 queue를 비교할 때 무한 반복될 수 있기 때문에 초기화하는 구간을 만들기 위함
        while(!queue.isEmpty()) {
            // 카운트와 큐의 크기가 같은 경우는 한번 다 둘러본 경우이기 때문에 first를 변경, answer를 증가시킨다.
            if(count == queue.size()) {
                count = 0;
                first = queue.poll();
                answer++;
                continue;
            }
            // 방 하나를 가지고 이어 쓸수 있는 경우
            if(first.end <= queue.peek().start) {
                first = queue.poll();
                continue;
            }
            // 이어 쓸 수 없다면 다시 큐에 집어넣고 카운트를 증가시킨다.
            queue.add(queue.poll());
            count++;
        }

        return answer;
    }
}

// 시작시간, 끝나는시간을 클래스로 생성
class SetTime {
    int start;
    int end;

    public SetTime(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

