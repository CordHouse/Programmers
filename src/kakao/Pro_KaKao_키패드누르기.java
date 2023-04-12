package kakao;

import java.util.ArrayList;
import java.util.Arrays;

public class Pro_KaKao_키패드누르기 {
    static int[][] map;
    static int[] vx = {-1,1,0,0}, vy = {0,0,-1,1};
    static int leftHand=10, rightHand=11;

    public static void main(String[] args){
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        System.out.println(solution(numbers, "right"));
    }

    public static String solution(int[] numbers, String hand) {
        String answer = "";
        map = new int[][]{{1,2,3},{4,5,6},{7,8,9},{10,0,11}}; // 키패드를 2차원 배열로 초기화 해준다.
        ArrayList arrLeft = new ArrayList<>(Arrays.asList(1,4,7)); // arrLeft는 왼쪽 손 1, 4, 7
        ArrayList arrRight = new ArrayList<>(Arrays.asList(3,6,9)); // arrRight는 오른쪽 손 3, 6, 9

        // 반복
        // numbers의 입력 길이만큼
        for(int i = 0; i<numbers.length; i++){
            // arrLeft에 포함된 넘버라면 왼쪽 손을 answer에 추가하고 leftHand값을 변경
            if(arrLeft.contains(numbers[i])){
                leftHand = numbers[i];
                answer += "L";
            }
            // arrRight에 포함된 넘버라면 오른쪽 손을 answer에 추가하고 rightHand값을 변경
            else if(arrRight.contains(numbers[i])){
                rightHand = numbers[i];
                answer += "R";
            }
            // 2, 5, 8, 0에 해당하는 경우라면 cal 함수 호출
            else{
                // 값을 리턴받아 L, R을 표현
                String Return = cal(leftHand, rightHand, numbers[i], hand);
                if(Return == "L")
                    leftHand = numbers[i];
                else if(Return == "R")
                    rightHand = numbers[i];
                answer += Return;
            }
        }
        return answer;
    }
    // 해당 함수 파라미터는 왼쪽 손 값, 오른쪽 손 값, 눌러야 할 값, 입력된 손잡이
    public static String cal(int leftHand, int rightHand, int numbers, String hand){
        // 값을 좌표로 비교하기 때문에 좌표를 찾아줘야하는데 각 좌표는 처음에 -1로 설정해준다
        int xLeft = -1, yLeft = -1, xRight = -1, yRight = -1, xNumbers = -1, yNumbers = -1;
        // 상하좌우를 돌면서 눌러야할 값 기준으로 왼쪽 혹은 오른쪽 손의 좌표차이가 1이 나는 경우판단하기 위해 boolean 선언
        boolean leftCheck = false, rightCheck = false;
        // 브루트포스를 통해서 각 손에 대한 좌표와 눌러야할 값에 대한 좌표를 설정
        for(int i = 0; i<4; i++){
            for(int j = 0; j<3; j++){
                if(xNumbers != -1 && yNumbers != -1 && xLeft != -1 && yLeft != -1 && xRight != -1 && yRight != -1)
                    break;
                if(map[i][j] == numbers){
                    xNumbers = i;
                    yNumbers = j;
                }
                if(map[i][j] == leftHand){
                    xLeft = i;
                    yLeft = j;
                }
                if(map[i][j] == rightHand){
                    xRight = i;
                    yRight = j;
                }
            }
            if(xNumbers != -1 && yNumbers != -1 && xLeft != -1 && yLeft != -1 && xRight != -1 && yRight != -1)
                break;
        }
        // 반복 상하좌우를 돌아보며 거리가 왼쪽, 오른쪽이 찾을 값과 두개가 동일 한 경우를 찾기 위함
        for(int i = 0; i<4; i++){
            int nxNumbers = xNumbers + vx[i];
            int nyNumbers = yNumbers + vy[i];

            if(nxNumbers >= 0 && nyNumbers >= 0 && nxNumbers < 4 && nyNumbers < 3){
                if(map[nxNumbers][nyNumbers] == leftHand)
                    leftCheck = true;
                if(map[nxNumbers][nyNumbers] == rightHand)
                    rightCheck = true;
            }
        }
        // 두 손의 좌표 거리가 같은 경우
        if(leftCheck == true && rightCheck == true){
            if(hand.equals("left")){
                return "L";
            }
            else if(hand.equals("right")){
                return "R";
            }
        }
        // 좌표 거리로 계산하는데 멘헤튼 방식을 사용, 손은 상하좌우로 움직이기 때문에 이 방식을 적용해야함
        else if(Math.abs(xLeft-xNumbers) + Math.abs(yLeft-yNumbers) < Math.abs(xRight-xNumbers) + Math.abs(yRight-yNumbers)){
            return "L";
        }
        else if(Math.abs(xLeft-xNumbers) + Math.abs(yLeft-yNumbers) > Math.abs(xRight-xNumbers) + Math.abs(yRight-yNumbers)){
            return "R";
        }
        // 상하좌우에 있는 값도 아니면서, 두 값의 차이가 나지 않는 경우
        else{
            if(hand.equals("left"))
                return "L";
            else if(hand.equals("right"))
                return "R";
        }
        return "-1";
    }
}
