import java.util.*;
public class 전력망을_둘로_나누기 {
    static int count;
    public static void main(String[] args) {
        int n = 9;
        int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        System.out.println(solution(n, wires));
    }
    public static int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        for(int node = 0; node < wires.length; node++){
            Map<Integer, List<Integer>> map = createMap(n);
            count = 1;
            for(int i = 0; i < wires.length; i++){
                if(node != i){
                    map.get(wires[i][0]).add(wires[i][1]);
                    map.get(wires[i][1]).add(wires[i][0]);
                }
            }
            dfs(wires[node][0], map);
            int rightLen = count;
            int leftLen = n - rightLen;
            answer = Math.min(answer, Math.abs(leftLen-rightLen));
        }
        return answer;
    }

    public static void dfs(int start, Map<Integer, List<Integer>> map){
        if(map.get(start).size() == 0){
            return ;
        }
        while(map.get(start).size() != 0){
            int next = map.get(start).get(0);
            map.get(start).remove(0);
            map.get(next).remove((Integer) start);
            count++;
            dfs(next, map);
        }
    }

    public static Map<Integer, List<Integer>> createMap(int n){
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 1; i <= n; i++){
            map.put(i, new LinkedList<>());
        }
        return map;
    }
}
