package kakao;

import java.util.ArrayList;
import java.util.HashMap;

public class kakaoTest4 {
    public static void main(String[] args) {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};
        String answer ="";
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        HashMap<String, Integer> hashMap_view = new HashMap<>();
        hashMap.put(1,3);
        hashMap.put(2,2);
        hashMap.put(3,1);
        hashMap.put(4,0);
        hashMap.put(5,1);
        hashMap.put(6,2);
        hashMap.put(7,3);

        hashMap_view.put("R",0);
        hashMap_view.put("T",0);
        hashMap_view.put("C",0);
        hashMap_view.put("F",0);
        hashMap_view.put("J",0);
        hashMap_view.put("M",0);
        hashMap_view.put("A",0);
        hashMap_view.put("N",0);

        String[] tmp;
        ArrayList<String> tmp_list = new ArrayList<>();
        for(int i = 0; i< survey.length; i++){
            tmp = survey[i].split("");
            tmp_list.add(tmp[0]);
            tmp_list.add(tmp[1]);
        }

        int count = 0;
        for(int i = 0; i< choices.length; i++){
            if((tmp_list.get(count).equals("R") && tmp_list.get(count+1).equals("T")) || (tmp_list.get(count).equals("T") && tmp_list.get(count+1).equals("R"))){
                if(choices[i] < 4){
                    hashMap_view.replace(tmp_list.get(count), hashMap_view.get(tmp_list.get(count)) + hashMap.get(choices[i]));
                }
                else if(choices[i]>4){
                    hashMap_view.replace(tmp_list.get(count+1), hashMap_view.get(tmp_list.get(count+1)) + hashMap.get(choices[i]));
                }
            }
            else if((tmp_list.get(count).equals("C") && tmp_list.get(count+1).equals("F")) || (tmp_list.get(count).equals("F") && tmp_list.get(count+1).equals("C"))){
                if(choices[i] < 4){
                    hashMap_view.replace(tmp_list.get(count), hashMap_view.get(tmp_list.get(count)) + hashMap.get(choices[i]));
                }
                else if(choices[i]>4){
                    hashMap_view.replace(tmp_list.get(count+1), hashMap_view.get(tmp_list.get(count+1)) + hashMap.get(choices[i]));
                }
            }
            else if((tmp_list.get(count).equals("J") && tmp_list.get(count+1).equals("M")) || (tmp_list.get(count).equals("M") && tmp_list.get(count+1).equals("J"))){
                if(choices[i] < 4){
                    hashMap_view.replace(tmp_list.get(count), hashMap_view.get(tmp_list.get(count)) + hashMap.get(choices[i]));
                }
                else if(choices[i]>4){
                    hashMap_view.replace(tmp_list.get(count+1), hashMap_view.get(tmp_list.get(count+1)) + hashMap.get(choices[i]));
                }
            }
            else if((tmp_list.get(count).equals("A") && tmp_list.get(count+1).equals("N")) || (tmp_list.get(count).equals("N") && tmp_list.get(count+1).equals("A"))){
                if(choices[i] < 4){
                    hashMap_view.replace(tmp_list.get(count), hashMap_view.get(tmp_list.get(count)) + hashMap.get(choices[i]));
                }
                else if(choices[i]>4){
                    hashMap_view.replace(tmp_list.get(count+1), hashMap_view.get(tmp_list.get(count+1)) + hashMap.get(choices[i]));
                }
            }
            count += 2;
        }

        answer += hashMap_view.get("R") >= hashMap_view.get("T") ? "R" : "T";
        answer += hashMap_view.get("C") >= hashMap_view.get("F") ? "C" : "F";
        answer += hashMap_view.get("J") >= hashMap_view.get("M") ? "J" : "M";
        answer += hashMap_view.get("A") >= hashMap_view.get("N") ? "A" : "N";
        System.out.println(answer);
    }
}
