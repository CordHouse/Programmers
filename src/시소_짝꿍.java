import java.util.*;
public class 시소_짝꿍 {
    // seat에 앉는 비율은 weights가 오름차순 정렬 되기 때문에 왼쪽이 오른쪽보다 큰 경우만 고려한다.
    // 단, 1:1 비율은 2:2, 3:3, 4:4와 같으므로 1:1은 따로 적용한다.
    private static int[][] seat = {{1,1}, {3,2}, {4,2}, {4,3}};

    public static void main(String[] args) {
        int[] weights = {100,180,360,100,270};
        System.out.println(solution(weights));
    }

    public static long solution(int[] weights) {
        long answer = 0;
        // 오름차순 정렬
        Arrays.sort(weights);

        for(int i = 0; i < weights.length; i++) {
            for(int j = 0; j < seat.length; j++) {
                // 현재 weights[i]에서 왼쪽 비율만큼 곱해주고, 오른쪽 비율로 나눈 나머지가 0이 아니라면
                // 둘은 어떤 수를 곱해도 같은 수가 나올 수 없기 때문에 스킵한다.
                // 오름차순 정렬했기 때문에 가능함.
                if((weights[i] * seat[j][0]) % seat[j][1] != 0) {
                    continue;
                }

                // search는 비례식을 통해 비교할 값을 통해 비교해야할 값을 구하는 과정이다.
                // a : b = x : y -> y = b * x / a 의 식이 성립
                int search = ((weights[i] * seat[j][0]) / seat[j][1]);
                // answer는 up과 down의 두 가지 경우의 차를 더해준다.
                // up과 down으로 나눈 이유는 중복되는 사잇값을 찾기 위해서이다.
                // ex) 100, 100, 180, 270, 360 -> up은 2번, down 1번이 선택된다.
                answer += up(i+1, weights.length, weights, search) - down(i+1, weights.length, weights, search);
            }
        }
        return answer;
    }

    public static int up(int left, int right, int[] weights, int search) {
        while(left < right) {
            int mid = (left + right) / 2;
            if(search < weights[mid]) {
                right = mid;
            }
            else {
                left = mid+1;
            }
        }
        return left;
    }

    public static int down(int left, int right, int[] weights, int search) {
        while(left < right) {
            int mid = (left + right) / 2;
            if(search <= weights[mid]) {
                right = mid;
            }
            else {
                left = mid+1;
            }
        }
        return left;
    }
}
