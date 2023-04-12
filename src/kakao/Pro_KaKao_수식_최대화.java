package kakao;

import java.util.*;
public class Pro_KaKao_수식_최대화 {
    static List<List<String>> gihoPriority = new LinkedList<>();
    public static void main(String[] args) {
        String expression = "100-200*300-500+20";
        System.out.println(solution(expression));
    }
    public static long solution(String expression) {
        long answer = 0;

        String[] input = {"+", "-", "*"};
        boolean[] check = new boolean[3];
        String[] output = new String[3];

        gihoCombi(input, check, output, 0);

        List<Long> calMax = new LinkedList<>();
        for(List<String> value : gihoPriority){
            List<String> expressionBulider = splitExpression(expression.toCharArray());
            calMax.add(cal(expressionBulider, value));
        }

        for(long value : calMax){
            answer = Math.max(answer, Math.abs(value));
        }
        return answer;
    }

    public static long cal(List<String> expressionBuilder, List<String> giho){
        for(String value : giho){
            while(expressionBuilder.contains(value)){
                for(int i = 1; i < expressionBuilder.size(); i+=2){
                    if(expressionBuilder.get(i).equals(value)){
                        long a = Long.parseLong(expressionBuilder.get(i-1));
                        long b = Long.parseLong(expressionBuilder.get(i+1));
                        long sum = sum(a, b, value);
                        expressionBuilder.set(i, String.valueOf(sum));
                        expressionBuilder.remove(i+1);
                        expressionBuilder.remove(i-1);
                        break;
                    }
                }
            }
        }
        return Long.parseLong(expressionBuilder.get(0));
    }

    public static long sum(long a, long b, String giho){
        return switch (giho) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> 0;
        };
    }

    public static List<String> splitExpression(char[] ex){
        StringBuilder sb = new StringBuilder();
        List<String> build = new LinkedList<>();
        for(char value : ex){
            if(value == '+' || value == '-' || value == '*'){
                build.add(sb.toString());
                build.add(String.valueOf(value));
                sb = new StringBuilder();
            }
            else{
                sb.append(value);
            }
        }
        build.add(sb.toString());
        return build;
    }

    public static void gihoCombi(String[] input, boolean[] check, String[] output, int depth){
        if(depth == input.length){
            List<String> save = new LinkedList<>(Arrays.asList(output));
            gihoPriority.add(save);
            return;
        }
        for(int i = 0; i < input.length; i++){
            if(!check[i]){
                check[i] = true;
                output[depth] = input[i];
                gihoCombi(input, check, output, depth+1);
                check[i] = false;
            }
        }
    }
}
