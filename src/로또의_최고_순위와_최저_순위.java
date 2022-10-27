import java.util.*;
public class 로또의_최고_순위와_최저_순위 {
    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};
        System.out.println(Arrays.toString(solution(lottos, win_nums)));
    }

    public static int[] solution(int[] lottos, int[] win_nums) {
        // Map 클래스를 통해 맞춘 갯수, 순위로 키 벨류를 잡아준다.
        int max = 0, min = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 6);
        map.put(1, 6);
        map.put(2, 5);
        map.put(3, 4);
        map.put(4, 3);
        map.put(5, 2);
        map.put(6, 1);


        // max = 최대 값으로, 0은 조커와 같은 숫자이기 때문에 무조건 합쳐준다.
        max += Arrays.stream(lottos).filter(s -> s == 0).count();

        // 같은 숫자가 있는지 판단하고, stream에 anyMatch를 통해 값의 여부를 판단한다.
        for(int value : win_nums){
            if(Arrays.stream(lottos).anyMatch(num -> num == value)){
                max++;
                min++;
            }
        }

        int[] answer = new int[2];

        // 최소와 최대를 구하는 문제이기 때문에 정해진 자리에 값을 넣어준다.
        for(int key : map.keySet()){
            if(key == max)
                answer[0] = map.get(key);
            if(key == min)
                answer[1] = map.get(key);
        }

        return answer;
    }
}

