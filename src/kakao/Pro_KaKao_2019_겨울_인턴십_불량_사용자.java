package kakao;

import java.util.*;
public class Pro_KaKao_2019_겨울_인턴십_불량_사용자 {
    static HashSet<String> set = new HashSet<>();
    static boolean[] check;
    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "abc1**"};

        System.out.println(solution(user_id, banned_id));
    }
    public static int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        check = new boolean[user_id.length];
        for(int i = 0; i < banned_id.length; i++) {
            // 정규표현식 사용
            banned_id[i] = banned_id[i].replaceAll("\\*", ".");
        }
        backTracking(0, user_id, banned_id, "");
        answer = set.size();
        return answer;
    }

    public static void backTracking(int depth, String[] user_id, String[] ban, String value) {
        if(depth == ban.length) {
            String[] valueSplit = value.split(" ");
            Arrays.sort(valueSplit);
            StringBuilder sb = new StringBuilder();
            for(String v : valueSplit) {
                sb.append(v);
            }
            set.add(sb.toString());
            return;
        }

        for(int i = 0; i < user_id.length; i++) {
            if(!check[i] && user_id[i].matches(ban[depth])) {
                check[i] = true;
                backTracking(depth+1, user_id, ban, value+user_id[i]+" ");
                check[i] = false;
            }
        }
    }

}
