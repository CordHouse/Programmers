import java.util.Arrays;

public class Pro_KaKao_비밀지도 {
    public static void main(String[] args){
        int n = 5;
        int[] arr1 = {9,20,28,18,11};
        int[] arr2 = {30,1,21,17,28};
        System.out.println(Arrays.toString(solution(n, arr1, arr2)));
    }
    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        int[][] map1 = new int[n][n];
        int[][] map2 = new int[n][n];
        String[][] sum = new String[n][n];

        map1 = mapping(n, arr1, map1);
        map2 = mapping(n, arr2, map2);

        for(int i = 0; i < n; i++){
            String strSum = "";
            for(int j = 0; j < n; j++){
                if(map1[i][j] == 0 && map2[i][j] == 0){
                    sum[i][j] = " ";
                }
                else{
                    sum[i][j] = "#";
                }
                strSum += sum[i][j];
            }
            answer[i] = strSum;
        }
        return answer;
    }

    public static int[][] mapping(int n, int[] arr, int[][] map){
        int count = 0;
        for(int num : arr){
            String binary = Integer.toBinaryString(num); // 2진수로 가져오기
            int interCount = 0;
            for(int i = n - binary.length(); i < n; i++){
                map[count][i] = binary.charAt(interCount) - '0';
                interCount++;
            }
            count++;
        }
        return map;
    }
}
