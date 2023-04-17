package highKit.binary;
import java.util.*;

public class 입국심사 {
    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};
        System.out.println(solution(n, times));
    }
    public static long solution(int n, int[] times) {
        long answer = 0;

        Arrays.sort(times);
        long minTime = 0;
        long maxTime = (long) n * times[times.length - 1];
        while (minTime <= maxTime) {
            // 0분부터 가장 길게 걸리는 시간만큼을 더해 중간 값을 구해준다.
            long mid = (minTime + maxTime) / 2;
            // 데스크 심사위원들이 검사하는 인원을 담을 변수
            long desk = 0;
            // 정렬한 값을 통해 작은 값부터 접근할 수 있다.
            // 해당 중간 값을 기준으로 주어진 시간으로 나눠주면 한 데스크당 검사하는 인원 수를 알 수 있다.
            for (int i = 0; i < times.length; i++) {
                desk += mid / times[i];
            }
            // 검사한 총 인원이 검사해야할 인원보다 작은 경우
            // 위 반복문에서 큰 값에서 적게 나눠적기 때문에 발생한 경우이다.
            // 따라서 최소 시간을 높여주어 큰 값이 많이 나눠지도록 해야한다.
            if (desk < n) {
                minTime = mid + 1;
            }
            // 검사한 총 인원이 검사해야할 인원보다 큰 경우
            // 위 반복문에서 큰 값으로 많이 나눴기 때문에 인원이 증가한 경우이다.
            // 따라서 큰 값을 줄여줄 필요가 있다.
            // 또한 큰 값이 고정되고 있는 경우가 우리가 찾고자 하는 답이다.
            else {
                maxTime = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}
