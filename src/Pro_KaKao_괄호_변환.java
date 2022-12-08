import java.util.*;
public class Pro_KaKao_괄호_변환 {
    public static void main(String[] args) {
        String p = "()))((()";
        System.out.println(solution(p));
    }
    public static String solution(String p) {
        String answer = "";

        if(blacketCheck(p.toCharArray(), p)){
            return p;
        }

        answer = cycle(p);

        return answer;
    }

    public static String cycle(String p){
        String u = p.substring(0, splitPosition(p.toCharArray()));
        String v = p.substring(splitPosition(p.toCharArray()), p.length());
        boolean check = blacketCheck(u.toCharArray(), u);
        if(check && v.equals("")){
            return u+v;
        }
        else if(check && !v.equals("")){
            return u + cycle(v);
        }
        else if(!check){
            u = "(" + cycle(v) + ")" + uReplace(u);
        }
        return u;
    }

    public static String uReplace(String u){
        u = u.substring(1, u.length()-1);
        char[] tmp = u.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < u.length(); i++){
            if(tmp[i] == '('){
                sb.append(")");
            }
            else{
                sb.append("(");
            }
        }
        return sb.toString();
    }

    public static int splitPosition(char[] blacket){
        int leftCount = 0;
        int rightCount = 0;
        for(int i = 0; i < blacket.length; i++){
            if(blacket[i] == '('){
                leftCount++;
            }
            else{
                rightCount++;
            }
            if(leftCount == rightCount){
                return i+1;
            }
        }
        return 0;
    }

    public static Stack<String> createOpenBlacket(char[] blacket){
        Stack<String> open = new Stack<>();
        for(char value : blacket){
            open.add(String.valueOf(value));
        }
        return open;
    }

    public static boolean blacketCheck(char[] blacket, String p){
        Stack<String> open = createOpenBlacket(blacket);
        Stack<String> close = new Stack<>();

        while(!open.empty()){
            if(open.peek().equals(")")){
                close.add(open.pop());
            }
            else{
                if(open.peek().equals("(") && !close.empty()){
                    open.pop();
                    close.pop();
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }

}
