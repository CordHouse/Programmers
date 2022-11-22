public class 푸드_파이트_대회 {
    public static void main(String[] args) {
        int[] food = {1, 3, 4, 6};
        System.out.println(solution(food));
    }
    public static String solution(int[] food) {
        String answer = "";
        for(int i = 1; i < food.length; i++){
            food[i] = even(food[i]);
        }

        int totalFoodLen = sum(food);

        answer = numSumString(totalFoodLen, food);

        return answer;
    }

    public static String numSumString(int len, int[] food){
        String[] ans = new String[len];
        int left = 0;
        int right = len-1;
        int count = 1;
        String sumString = "";
        for(int i = 1; i < food.length; i++){
            for(int j = 0; j < food[i]/2; j++){
                ans[left] = String.valueOf(count);
                ans[right] = String.valueOf(count);
                left++;
                right--;
            }
            count++;
        }
        ans[((len-1)/2)] = "0";

        for(String value : ans){
            sumString += value;
        }

        return sumString;
    }

    public static int sum(int[] food){
        int sum = 0;
        for(int value : food){
            sum += value;
        }
        return sum;
    }

    public static int even(int num){
        if(num % 2 == 1){
            num -= 1;
        }
        return num;
    }
}
