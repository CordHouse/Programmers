import java.util.*;
public class 괄호_회전하기 {
    public static void main(String[] args) {
        String s = "[](){}";
        System.out.println(solution(s));
    }
    public static int solution(String s) {
        int answer = 0;
        HashMap<String, String> map = new HashMap<>();
        map.put("(", ")");
        map.put("{", "}");
        map.put("[", "]");

        for(int i = 0; i < s.length(); i++){
            s = spinGiho(s.toCharArray());
            s = createGiho(s.toCharArray());
            int num = checkGiho(s);
            answer += num;
        }

        return answer;
    }

    public static String spinGiho(char[] s){
        String sum = "";
        for(int i = 1; i < s.length; i++){
            sum+=s[i];
        }
        sum+=s[0];
        return sum;
    }

    public static String createGiho(char[] c){
        String create = "";

        for(char value : c){
            create += String.valueOf(value);
        }

        return create;
    }

    public static int checkGiho(String s){
        Stack<String> stack1 = stackCreate(s);
        Stack<String> stack2 = new Stack<>();
        List<String> giho = List.of("()","{}","[]");
        while(!stack1.empty()){
            String tmp = stack1.pop();
            if(tmp.equals(")") || tmp.equals("}") || tmp.equals("]")){
                stack2.push(tmp);
            }
            else{
                if(stack2.empty()){
                    return 0;
                }
                else{
                    if(!giho.contains((tmp+stack2.pop()))){
                        return 0;
                    }
                }
            }
        }
        if(!stack2.empty()){
            return 0;
        }
        return 1;
    }

    public static Stack<String> stackCreate(String s){
        Stack<String> stack1 = new Stack<>();
        char[] tmp = s.toCharArray();
        for(char value : tmp){
            stack1.push(String.valueOf(value));
        }
        return stack1;
    }

}
