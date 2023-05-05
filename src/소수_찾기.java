import java.util.*;
public class 소수_찾기 {
    private static boolean[] prime;
    private static final int SIZE = 10000000;
    private static int count = 0;
    private static boolean[] check;
    private static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) {
        String numbers = "17";
        System.out.println(solution(numbers));
    }
    public static int solution(String numbers) {
        int answer = 0;
        prime = new boolean[SIZE];
        primeMake();
        check = new boolean[numbers.length()];
        dfs(0, new String[numbers.length()], numbers.split(""));
        for(String v : set) {
            if(!searchPrime(Integer.parseInt(v))) {
                count++;
            }
        }

        answer = count;
        return answer;
    }

    public static void dfs(int depth, String[] arr, String[] numbers) {
        // set을 통해 중복 제거
        if(depth == numbers.length) {
            StringBuilder sum = new StringBuilder();
            for(int j = 0; j < arr.length; j++) {
                if(arr[j] == null) {
                    continue;
                }
                if(sum.toString().equals("") && arr[j].equals("0")) {
                    continue;
                }
                sum.append(arr[j]);
                set.add(sum.toString());
            }
            return;
        }

        for(int i = 0; i < numbers.length; i++) {
            if(!check[i]) {
                check[i] = true;

                arr[depth] = numbers[i];
                dfs(depth+1, arr, numbers);
                check[i] = false;
            }
        }
    }

    // 해당 값을 통해 찾는다.
    public static boolean searchPrime(int n) {
        return prime[n];
    }

    // 모든 위치에 소수자리는 false, 소수가 아닌 자리는 true
    public static void primeMake() {
        prime[0] = true;
        prime[1] = true;

        for(int i = 2; i < Math.sqrt(prime.length); i++) {
            if(prime[i]) {
                continue;
            }

            for(int j = i*i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }
    }
}
