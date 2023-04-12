package kakao;

import java.util.*;

public class Pro_KaKao_Blind_택배_배달과_수거하기 {
    public static void main(String[] args) {
        int[] pickups = {0, 3, 0, 4, 0};
        List<Integer> pickupsList = Arrays.stream(pickups).boxed().toList();
        System.out.println(pickupsList);
    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        long d = 0; // 배달 포인트
        long p = 0; // 수거 포인트

        for(int i = n-1; i >= 0; i--) {
            d += deliveries[i];
            p += pickups[i];

            while(d > 0 || p > 0) {
                d -= cap;
                p -= cap;
                answer += (i+1) * 2L;
            }
        }
        return answer;
    }
}
