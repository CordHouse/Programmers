package highKit.dfs_bfs;

public class 타겟_넘버 {
    static int count = 0;
    public static void main(String[] args) {
        int[] numbers = {1,1,1,1,1};
        int target = 3;
        System.out.println(solution(numbers, target));
    }
    public static int solution(int[] numbers, int target) {
        int answer = 0;
        dfs(0, numbers, target, 0);
        answer = count;
        return answer;
    }
    public static void dfs(int depth, int[] numbers, int target, int sum) {
        if(depth == numbers.length) {
            if(target == sum) {
                count++;
            }
            return;
        }
        dfs(depth+1, numbers, target, sum+numbers[depth]);
        dfs(depth+1, numbers, target, sum-numbers[depth]);
    }
}
