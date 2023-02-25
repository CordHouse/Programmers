import java.util.*;
public class Pro_KaKao_Blind_이모티콘_할인행사 {
    private static int[][] globalUsers;
    private static HashMap<Integer, Integer> table = new HashMap<>();

    public static void main(String[] args) {
        int[][] users = {{40, 10000}, {25, 10000}};
        int[] emoticons = {7000, 9000};
        System.out.println(Arrays.toString(solution(users, emoticons)));
    }
    public static int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        globalUsers = users;
        List<Integer> percent = List.of(10, 20, 30, 40);
        allPercentList(0, percent, emoticons, new ArrayList<>());
        int answerKey = 0;
        for(int key : table.keySet()) {
            answerKey = Math.max(answerKey, key);
        }
        answer[0] = answerKey;
        answer[1] = table.get(answerKey);
        return answer;
    }
    // 모든 할인율 경우 수 구하기
    public static void allPercentList(int depth, List<Integer> percent, int[] emoticons, List<Integer> temp) {
        if(depth == emoticons.length) {
            calculator(temp, emoticons);
            return;
        }
        for(int i = 0; i < percent.size(); i++) {
            temp.add(percent.get(i));
            allPercentList(depth+1, percent, emoticons, temp);
            temp.remove(depth);
        }
    }

    public static void calculator(List<Integer> reflectPercent, int[] emoticons) {
        int person = 0;
        int price = 0;
        for(int[] user : globalUsers) {
            int sumMoney = 0;
            for(int i = 0; i < reflectPercent.size(); i++) {
                if(user[0] <= reflectPercent.get(i)) {
                    sumMoney += emoticons[i] * ((100 - reflectPercent.get(i)) / 100.0);
                }
            }
            if(user[1] <= sumMoney) {
                person += 1;
            }
            else {
                price += sumMoney;
            }
        }
        if(table.containsKey(person)) {
            table.put(person, Math.max(table.get(person), price));
        }
        else {
            table.put(person, price);
        }
    }
}
