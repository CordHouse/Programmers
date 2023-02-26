import java.util.*;
public class Pro_KaKao_Blind_표현_가능한_이진트리 {

    public static void main(String[] args) {
        long[] numbers = {7, 42, 5};
        System.out.println(Arrays.toString(solution(numbers)));
    }

    public static int[] solution(long[] numbers) {
        int[] answer = {};
        List<String> binaryNumbers = changeToBinary(numbers);
        List<Integer> answerTemp = new LinkedList<>();
        for(String binary : binaryNumbers) {
            answerTemp.add(treeCheck(binary));
        }
        answer = answerTemp.stream().mapToInt(i -> i).toArray();
        return answer;
    }

    public static int treeCheck(String number) {
        int centerPointer = number.length() / 2;
        if(centerNumberCheck(number.toCharArray(), centerPointer)) {
            return 0;
        }
        return cycle(number.toCharArray(), centerPointer - 1, centerPointer + 1);
    }

    public static int cycle(char[] number ,int left, int right) {
        while(true){
            left = left / 2;
            right = right + left;
            if(left == 0) {
                break;
            }
            if(centerNumberCheck(number, left) || centerNumberCheck(number, right)) {
                return 0;
            }
        }
        return 1;
    }

    public static boolean centerNumberCheck(char[] number, int pointer) {
        if(number[pointer] == '0') {
            return true;
        }
        return false;
    }

    public static List<String> changeToBinary(long[] numbers) {
        List<String> binaryNumbers = new LinkedList<>();
        int n = 1;
        for(long value : numbers) {
            String binaryType = Long.toBinaryString(value);
            while(true) {
                int max = (int) Math.pow(2, n) - 1;
                if(binaryType.length() - max <= 0) {
                    binaryType = "0".repeat(max-binaryType.length()) + binaryType;
                    break;
                }
                n++;
            }
            binaryNumbers.add(binaryType);
        }
        return binaryNumbers;
    }

}
