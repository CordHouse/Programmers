package weekly;
import java.util.*;

public class 부족한_금액_계산하기 {
    public static void main(String[] args) {
        int price = 3;
        int money = 20;
        int count = 4;
        System.out.println(solution(price, money, count));
    }
    public static long solution(int price, int money, int count) {
        long answer = 0;
        long totalPrice = 0;
        for(int i = 1; i <= count; i++) {
            totalPrice += price * i;
        }

        if(money < totalPrice) {
            return totalPrice - money;
        }

        return answer;
    }
}
