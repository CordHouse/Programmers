import java.util.*;
public class 광물캐기 {
    private static HashMap<String, Integer> map;
    private static int[][] cost;
    private static int min = 0;
    public static void main(String[] args) {
        int[] picks = {1, 3, 2};
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        System.out.println(solution(picks, minerals));
    }
    public static int solution(int[] picks, String[] minerals) {
        map = new HashMap<>();
        min = Integer.MAX_VALUE;

        map.put("diamond", 0);
        map.put("iron", 1);
        map.put("stone",2);

        // 광물을 캐기 위한 피로도
        // index 0 -> 다이아 곡갱이
        // index 1 -> 철 곡갱이
        // index 2 -> 돌 곡갱이
        cost = new int[][]{{1,1,1},{5,1,1},{25,5,1}};
        int pickLen = 0;
        for(int x : picks) {
            pickLen += x;
        }

        dfs(0, new int[pickLen], picks, minerals);

        return min;
    }
    public static void dfs(int depth, int arr[], int[] picks, String[] minerals){
        if(depth == arr.length){
            min = Math.min(min , getMin(arr, minerals));
            return;
        }
        for(int i= 0; i< picks.length; i++){
            if(picks[i]>0){
                picks[i]--;
                arr[depth] = i;
                dfs(depth+1, arr, picks, minerals);
                picks[i]++;
            }
        }
    }

    // 완탐으로 만든 arr 를 기준으로 어떤 곡갱이를 사용할지 정해서 해당 피로도 만큼 계산해 리턴한다.
    public static int getMin(int[] arr, String[] minerals){
        int sum = 0;
        int index = 0;
        for(int x : arr){
            for(int i = 0; i < 5; i++){
                if(index == minerals.length) {
                    return sum;
                }
                sum += cost[x][map.get(minerals[index++])];
            }
        }
        return sum;
    }
}
