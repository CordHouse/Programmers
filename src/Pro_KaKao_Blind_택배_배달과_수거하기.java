import java.util.*;

public class Pro_KaKao_Blind_택배_배달과_수거하기 {
    public static void main(String[] args) {
        int[] pickups = {0, 3, 0, 4, 0};
        List<Integer> pickupsList = Arrays.stream(pickups).boxed().toList();
        System.out.println(pickupsList);
    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        List<Integer> deliveriesList = intArrayToList(deliveries); // 배달 -> list 변환
        List<Integer> pickupsList = intArrayToList(pickups); // 수거 -> list 변환
        long d = 0; // 배달 포인트
        long p = 0; // 수거 포인트

        for(int i = n-1; i >= 0; i--) {
            d += deliveriesList.get(i);
            p += pickupsList.get(i);

            while(d > 0 || p > 0) {
                d -= cap;
                p -= cap;
                answer += (i+1) * 2;
            }
        }
        return answer;
    }

    public static List<Integer> intArrayToList(int[] arr) {
        List<Integer> tempList = new LinkedList<>();
        for(int value : arr) {
            tempList.add(value);
        }
        return tempList;
    }
}
