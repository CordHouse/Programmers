package kakao;

public class Pro_KaKao_다트_게임 {
    public static void main(String[] args){
        System.out.println(solution("1S2D*3T"));
    }
    public static int solution(String dartResult) {
        // Step 1. 변수 선언
        int answer = 0;

        int point = 0; // 점수
        int[] dartPoint = new int[3]; // 3번의 기회를 각각 저장할 변수
        int index = 0; // 현재 위치를 나타내는 변수

        // Step 2. 반복
        // Switch 문을 활용해 dartResult의 문자를 하나하나 가져와 해당 case별로 분류하고 dartPoint에 저장한다.
        for(int loop = 0; loop < dartResult.length(); loop++){
            char dartStr = dartResult.charAt(loop);
            // char으로 받아오기 때문에 10 = 1, 0으로 따로 입력된다. 따라서 두개의 숫자를 비교해 10인 경우 수행
            // 단, 0을 기준으로 한다면 0점인 경우도 계산되기 때문에 현재 위치와 다음 위치를 꼭 비교해야한다.
            if(loop < dartResult.length()-2 && dartResult.charAt(loop) == '1' && dartResult.charAt(loop+1) == '0'){
                point = 10;
                dartStr = dartResult.charAt(loop+2);
                loop+=2;
            }
            switch(dartStr){
                case '*':
                    if(index == 1){
                        dartPoint[index-1] *= 2;
                    }
                    else{
                        dartPoint[index-2] *= 2;
                        dartPoint[index-1] *= 2;
                    }
                    break;
                case '#':
                    dartPoint[index-1] = -dartPoint[index-1];
                    break;
                case 'S':
                    dartPoint[index] = (int) Math.pow(point,1);
                    index++;
                    break;
                case 'D':
                    dartPoint[index] = (int) Math.pow(point,2);
                    index++;
                    break;
                case 'T':
                    dartPoint[index] = (int) Math.pow(point,3);
                    index++;
                    break;
                default:
                    point = dartStr - '0';
                    break;
            }
        }
        for(int value : dartPoint){
            answer += value;
        }
        return answer;
    }
}
