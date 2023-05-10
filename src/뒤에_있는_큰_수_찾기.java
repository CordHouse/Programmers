import java.util.*;
public class 뒤에_있는_큰_수_찾기 {
    public static void main(String[] args) {
        int[] numbers = {2, 3, 3, 5};
        System.out.println(Arrays.toString(solution(numbers)));
    }

    public static int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1); // 처음에 모든 값을 -1로 초기화한다.

        // 스택을 써야하는 이유는 제한사항에서 주어진 값이 1,000,000이기 때문에 n^2이 되면 시간초과가 발생하기 때문이다.
        // 스택을 썻을때 차이점은 현재 위치보다 이전 위치 값 중에서 큰 값들은 스택에 담기기 때문에 다음에 나온 값보다 작다면 변경해주면 된다.
        // ex) 5, 3, 6, 7 -> 6, 6, 7, -1
        Stack<IndexValue> stack = new Stack<>();

        for(int i = 1; i < numbers.length; i++) {
            // 두 값을 비교 했을 때 이전 값이 크거나 같다면 스택에 집어넣는다.
            if(numbers[i-1] >= numbers[i]) {
                stack.add(new IndexValue(i-1, numbers[i-1]));
                continue;
            }
            // 만약 작은 값이라면 현재 i-1 위치에 i 값을 넣어주고, 이후에 while 문을 통해 가장 최근에 넣었던 값 중에
            // 작은 값이 있다면 변경해준다. -> 이 과정을 스택이 비어 있을 때 까지 반복한다.
            // 단, 스택에 있는 값이 i 값보다 크다면 종료한다.
            answer[i-1] = numbers[i];
            while(!stack.isEmpty()) {
                if(numbers[i] > stack.peek().value) {
                    answer[stack.pop().index] = numbers[i];
                    continue;
                }
                break;
            }
            // 미리 모든 값을 -1로 변환했기 때문에, 이후 출력하면 되지만 미리 변경하지 않았다면
            // while 문을 한번 더 사용해 stack 이 빌때까지 -1의 값을 넣어주면 된다.
        }
        return answer;
    }

    static class IndexValue {
        int index;
        int value;

        public IndexValue(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}

