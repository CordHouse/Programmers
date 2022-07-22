import java.util.*;

public class Line {
    public static void main(String[] args) {
        String[] answer = {"morgan string_compare", "felix string_compare", "morgan reverse", "rohan sort", "andy reverse", "morgan sqrt"};
//        String[] answer = {"morgan sort", "felix sort", "morgan sqrt", "morgan sqrt", "rohan reverse", "rohan reverse"};
        String[] r = {};
        String name[];
        HashMap<String, Integer> hashMap = new HashMap<>();
        HashSet<String> hashSet = new HashSet<>();
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> arrayList_1 = new ArrayList<>();
        int count = 0;
        int start = 0;
        for(int i = 0; i< answer.length; i++){
            hashSet.add(answer[i]);
            if(!hashMap.containsKey(answer[i].split(" ")[0]))
                start++;
        }

        Iterator iterator = hashSet.iterator();
        while(iterator.hasNext()){
            arrayList.add(iterator.next().toString());
        }

        for(int i =0; i< hashSet.size(); i++) {
            name = arrayList.get(i).split(" ");
            if(!hashMap.containsKey(name[1]))
                hashMap.put(name[1], 1);
            else
                hashMap.put(name[1], hashMap.get(name[1]) + 1);
            System.out.println(start);
            System.out.println(arrayList.size());
            if(hashMap.get(name[1]) >= arrayList.size()/2) {
                arrayList_1.add(count, name[1]);
                count++;
            }
        }
        String[] tmp = new String[arrayList_1.size()];
        for(int i = 0; i<arrayList_1.size(); i++){
            tmp[i] = arrayList_1.get(i);
        }
        r = tmp;
        Arrays.sort(r);
        System.out.println(Arrays.toString(r));
    }
}
