import java.util.*;

public class 숫자_카드_나누기 {
    public static void main(String[] args) {
        int[] arrayA = {10, 17};
        int[] arrayB = {5, 20};
        System.out.println(solution(arrayA, arrayB));
    }


    public static int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        List<Integer> arrA = erato(arrayA);
        List<Integer> arrB = erato(arrayB);
        int a = cycle(arrA, arrayA, arrayB);
        int b = cycle(arrB, arrayB, arrayA);
        answer = Math.max(a, b);
        return answer;
    }

    // 나눠진 소수 중 해당 조건을 가진 값을 찾는다.
    // arrayA가 가진 카드들에 적힌 모든 숫자를 나눌 수 있고 arrayB가 가진 카드들에 적힌 모든 숫자들 중 하나도 나눌 수 없는 양의 정수 a
    // arrayB가 가진 카드들에 적힌 모든 숫자를 나눌 수 있고, arrayA가 가진 카드들에 적힌 모든 숫자들 중 하나도 나눌 수 없는 양의 정수 b
    public static int cycle(List<Integer> arrA, int[] arrayA, int[] arrayB){
        int ans = 0;
        for(int value : arrA){
            int count = 0;
            for(int i = 0; i < arrayA.length; i++){
                if(arrayA[i] % value == 0 && arrayB[i] % value != 0){
                    count++;
                }
            }
            if(count == arrayA.length){
                ans = Math.max(ans, value);
            }
        }
        return ans;
    }

    // 가장 작은 수의 소수를 찾는다.
    public static List<Integer> erato(int[] arrayA){
        List<Integer> list = new LinkedList<>();
        for(int i = 1; i <= arrayA[0]; i++){
            if(arrayA[0] % i == 0){
                list.add(i);
            }
        }
        return list;
    }
}
