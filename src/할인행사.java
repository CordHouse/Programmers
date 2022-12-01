import java.util.*;
public class 할인행사 {
    public static void main(String[] args) {
        String[] want = {"banana", "apple", "rice", "pork", "pot"};
        int[] number = {3, 2, 2, 2, 1};
        String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};

        System.out.println(solution(want, number, discount));
    }
    public static int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        for(int i = 0; i <= discount.length - 10; i++){
            boolean check = checkMenu(wantTableCreate(want, number), discount, i);
            if(check)
                answer = countResult(answer);
        }

        return answer;
    }

    public static int countResult(int count){
        return count+=1;
    }

    public static HashMap<String, Integer> backUpWantTable(HashMap<String, Integer> wantTable){
        return wantTable;
    }

    public static boolean checkMenu(HashMap<String, Integer> wantTable, String[] discount, int startDiscount){
        for(int i = startDiscount; i < startDiscount+10; i++){
            if(!wantTable.containsKey(discount[i])){
                return false;
            }
            else{
                if(wantTable.get(discount[i]) != 0){
                    wantTable.put(discount[i], wantTable.get(discount[i])-1);
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }

    public static HashMap<String, Integer> wantTableCreate(String[] want, int[] number){
        HashMap<String, Integer> wantTable = new HashMap<>();
        for(int i = 0; i < want.length; i++){
            wantTable.put(want[i], number[i]);
        }
        return wantTable;
    }

}
