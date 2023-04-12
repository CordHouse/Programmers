package highKit.hash;

import java.util.*;
public class 폰켓몬 {
    public static void main(String[] args) {
        int[] nums = {3,1,2,3};
        System.out.println(solution(nums));
    }
    public static int solution(int[] nums) {
        int numsHalf = nums.length / 2;
        HashMap<Integer, Integer> PhoneKetMon = new HashMap<Integer, Integer>();

        for(int i = 0; i < nums.length; i++) {
            if(!PhoneKetMon.containsKey(nums[i])) {
                PhoneKetMon.put(nums[i], 1);
            }
            PhoneKetMon.put(nums[i], PhoneKetMon.get(nums[i])+1);
        }

        List<Integer> PhoneKetMonList = new ArrayList<>(PhoneKetMon.keySet());

        if(PhoneKetMonList.size() >= numsHalf) {
            return numsHalf;
        }

        return PhoneKetMonList.size();
    }

}
