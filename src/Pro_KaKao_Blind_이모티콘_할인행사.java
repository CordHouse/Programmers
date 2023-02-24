import java.util.*;
public class Pro_KaKao_Blind_이모티콘_할인행사 {

    // 1. 할인율이 해당되는가 ?
    // 2. 할인율이 해당될 때 가격을 더해서 합산이 예상 금액보다 큰 가?
    // 3. 클 경우 -> 플러스 서비스 가입 추천, 작을 경우 -> 해당 이모티콘 모두 구매
    // 4. return -> 예상 비용과 크거나 같은 애들은 플러스 인원으로 출력, 그게 아닌 나머지는 비용 다 더해서 처리
    static List<List<Integer>> allDiscount = new LinkedList<>(); // 구매 금액
    static List<List<Integer>> allPercent = new LinkedList<>(); // 할인율

    public static void main(String[] args) {

    }

    public static int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        int[] discount = {10, 20, 30, 40};
        Map<Integer, Integer> table = new HashMap<>();
        int maxKeyIndex = 0;

        // 1. 유저가 생각하는 할인율을 10 ~ 40까지의 숫자로 바꾼다. (해결)
        for(int i = 0; i < users.length; i++) {
            users[i][0] = changePercent(String.valueOf(users[i][0]));
        }

        // 2. 할인율의 모든 경우의 수를 구하기
        List<Integer> unit = new ArrayList<>();
        calPercent(0, emoticons, discount, unit);

        // 3. 해당 할인율을 가져다가 비교해서 유저의 값과 비교해 result값 찾기
        for(int i = 0; i < allDiscount.size(); i++) {
            int[] confirm = usersConfirm(users, i);
            if(table.containsKey(confirm[0])) {
                table.put(confirm[0], Math.max(table.get(confirm[0]), confirm[1]));
            }
            else {
                table.put(confirm[0], confirm[1]);
            }
        }

        for(int key : table.keySet()){
            maxKeyIndex = Math.max(key, maxKeyIndex);
        }

        answer[0] = maxKeyIndex;
        answer[1] = table.get(maxKeyIndex);

        return answer;
    }

    public static int[] usersConfirm(int[][] users, int index) {
        // 1. 금액과 크거나 같다면 플러스 구매
        // 2. 금액보다 작다면 이모티콘 구매
        int[] result = new int[2];
        int person = 0;
        int price = 0;
        int unitTotal = totalMoney(index);
        // 한명씩 훑어보기
        for(int i = 0; i < users.length; i++) {
            int sumMoney = 0;
            // 한명씩 할인금액 계산해보기
            for(int j = 0; j < allPercent.get(index).size(); j++) {
                if(users[i][0] <= allPercent.get(index).get(j)) {
                    sumMoney += allDiscount.get(index).get(j);
                }
            }
            if(users[i][1] <= sumMoney) {
                person += 1;
            }
            if(sumMoney < unitTotal && sumMoney < users[i][1]) {
                price += sumMoney;
            }
        }
        result[0] = person;
        result[1] = price;
        return result;
    }

    public static int totalMoney(int index) {
        int sum = 0;
        for(int i = 0; i < allDiscount.get(index).size(); i++) {
            sum += allDiscount.get(index).get(i);
        }
        return sum;
    }

    public static void calPercent(int depth, int[] emoticons, int[] discount, List<Integer> unit) {
        if(depth == emoticons.length) {
            List<Integer> tempDiscount = new LinkedList<>();
            List<Integer> tempPercent = new LinkedList<>();
            for(int i = 0; i < emoticons.length; i++) {
                tempPercent.add(unit.get(i));
                int p = emoticons[i] - (unit.get(i) * emoticons[i]) / 100;
                tempDiscount.add(p);
            }

            allDiscount.add(tempDiscount);
            allPercent.add(tempPercent);
            return;
        }
        for(int i = 0; i < discount.length; i++) {
            unit.add(discount[i]);
            calPercent(depth+1, emoticons, discount, unit);
            unit.remove(depth);
        }
    }

    public static int changePercent(String number) {
        if(number.length() == 1){
            number = "0" + number;
        }
        if(Integer.parseInt(number.substring(1,2)) > 0) {
            return Integer.parseInt(String.valueOf(Integer.parseInt(number.substring(0,1))+1) + "0");
        }
        return Integer.parseInt(number);
    }
}
