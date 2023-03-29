import java.util.*;

public class 귤_고르기 {
    public static void main(String[] args) {
        int k = 6;
        int[] tangerine = {1, 3, 2, 5, 4, 5, 2, 3};
        System.out.println(solution(k, tangerine));
    }

    public static int solution(int k, int[] tangerine) {
        int answer = 0;
        // table 맵에 변수에 대한 갯수를 지정해서 담아준다.
        Map<Integer, Integer> table = new HashMap<>();

        // 주어진 int 배열에서 값을 하나씩 꺼내어 table에 이미 있는 숫자라면 기존 value + 1 그게 아니라면 1로 초기화를 진행한다.
        for(int value : tangerine) {
            if(table.containsKey(value)) {
                table.put(value, table.get(value)+1);
                continue;
            }
            table.put(value, 1);
        }

        // map을 사용하게 되면 이런식으로 keySet을 받아와 value 기준으로 정렬이 가능하다.
        // 정렬을 통해 큰 숫자가 앞으로 오게 내림차순 정렬한다.
        List<Integer> keys = new LinkedList<>(table.keySet());
        Collections.sort(keys, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                if(table.get(o1) == table.get(o2)) {
                    return table.get(o1) - table.get(o2);
                }
                return table.get(o2) - table.get(o1);
            }
        });

        // 마지막으로 내림차순 정렬을 기준으로 k 값을 빼주면서 최소한의 갯수를 찾아간다.
        for(int key : keys) {
            answer++;
            if(table.get(key) >= k) {
                break;
            }
            k -= table.get(key);
        }

        return answer;
    }

}
