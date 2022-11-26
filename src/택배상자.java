import java.util.*;
public class 택배상자 {
    static List<Integer> saveBoxContainer = new LinkedList<>();
    static List<Integer> containerBelt;
    static int answer;

    public static void main(String[] args) {
        int[] order = {4, 3, 1, 2, 5};
        System.out.println(solution(order));
    }
    public static int solution(int[] order) {
        answer = 0;
        int count = 0;
        boolean containerCheck;
        boolean saveContainerCheck;
        containerBelt = createContainerBelt(order.length);

        while(count != order.length){
            saveContainerCheck = saveBoxContainerCompare(order[count]);
            if(!saveContainerCheck) {
                containerCheck = containerCompare(order[count]);
                if(!containerCheck){
                    break;
                }
            }
            count++;
        }

        return answer;
    }

    public static boolean saveBoxContainerCompare(int orderValue){
        if (saveBoxContainer.size() != 0) {
            if (getSaveBox() == orderValue) {
                removeBox();
                orderCount();
                return true;
            }
        }
        return false;
    }

    public static boolean containerCompare(int orderValue){
        while(containerBelt.size() != 0) {
            if(containerBelt.get(0) == orderValue){
                removeContainer();
                orderCount();
                return true;
            }
            else {
                setSaveBox(containerBelt.get(0));
                removeContainer();
            }
        }
        return false;
    }

    public static List<Integer> createContainerBelt(int orderLength){
        List<Integer> containerBelt = new LinkedList<>();
        for(int i = 1; i <= orderLength; i++){
            containerBelt.add(i);
        }
        return containerBelt;
    }

    public static void setSaveBox(int boxNumber){
        saveBoxContainer.add(boxNumber);
    }

    public static int getSaveBox(){
        return saveBoxContainer.get(saveBoxContainer.size()-1);
    }

    public static void removeBox(){
        saveBoxContainer.remove(saveBoxContainer.size()-1);
    }

    public static void removeContainer(){
        containerBelt.remove(0);
    }

    public static int orderCount(){
        return answer+=1;
    }
}
