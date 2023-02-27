import java.util.*;
public class Pro_KaKao_Blind_표현_가능한_이진트리 {

    public static void main(String[] args) {
        long[] numbers = {7, 42, 5};
        System.out.println(Arrays.toString(solution(numbers)));
    }

    public static int[] solution(long[] numbers) {
        int[] answer = {};
        List<Integer> tempAnswer = changeBinary(numbers);
        answer = tempAnswer.stream().mapToInt(i -> i).toArray();
        return answer;
    }

    public static List<Integer> changeBinary(long[] numbers) {
        List<Integer> value = new LinkedList<>();
        boolean check = false;
        for(long number : numbers) {
            String binary = Long.toBinaryString(number);
            int n = 1;
            while(true) {
                int max = (int) Math.pow(2, n) - 1;
                if(binary.length() <= max) {
                    binary = "0".repeat(max - binary.length()) + binary;
                    break;
                }
                n++;
            }
            check = cycle(binary);

            if(check) {
                value.add(1);
            } else {
                value.add(0);
            }
        }
        return value;
    }

    public static boolean cycle(String number) {
        int mid = number.length() / 2;
        boolean value = true;
        String left = number.substring(0, mid);
        String right = number.substring(mid+1);
        char[] charNumber = number.toCharArray();
        if(charNumber[mid] == '0' && (left.charAt(left.length() / 2) == '1' || right.charAt(right.length() / 2) == '1')) {
            return false;
        }

        if(left.length() >= 3) {
            value = cycle(left);
            if(value) {
                value = cycle(right);
            }
        }

        return value;
    }

}
