import java.util.ArrayList;

public class kakaoTest2 {
    public static void main(String[] args) {
        String s = "one4seveneight";
        int answer = 0;
        ArrayList<String> s_tmp = new ArrayList<>();
        String[] tmp;
        tmp = s.split("");

        for(int i =0; i<s.length(); i++) {
            s_tmp.add(tmp[i]);
        }

        String search, answer_save = "";
        for(int i = 0; i<s_tmp.size();){
            search = s_tmp.get(i);
            switch (search){
                case "z":
                    if(s_tmp.get(i+2).equals("r")) {
                        answer_save += "0";
                        i += 4;
                    }
                    break;
                case "o":
                    if(s_tmp.get(i+2).equals("e")) {
                        answer_save += "1";
                        i += 3;
                    }
                    break;
                case "t":
                    if(s_tmp.get(i+2).equals("o")) {
                        answer_save += "2";
                        i += 3;
                    }
                    else if(s_tmp.get(i+2).equals("r")) {
                        answer_save += "3";
                        i += 5;
                    }
                    break;
                case "f":
                    if(s_tmp.get(i+2).equals("u")) {
                        answer_save += "4";
                        i += 4;
                    }
                    else if(s_tmp.get(i+2).equals("v")) {
                        answer_save += "5";
                        i += 4;
                    }
                    break;
                case "s":
                    if(s_tmp.get(i+2).equals("x")) {
                        answer_save += "6";
                        i += 3;
                    }
                    else if(s_tmp.get(i+2).equals("v")) {
                        answer_save += "7";
                        i += 5;
                    }
                    break;
                case "e":
                    if(s_tmp.get(i+2).equals("g")) {
                        answer_save += "8";
                        i += 5;
                    }
                    break;
                case "n":
                    if(s_tmp.get(i+2).equals("n")) {
                        answer_save += "9";
                        i += 4;
                    }
                    break;
                default: {
                    answer_save += search;
                    i += 1;
                    break;
                }
            }
        }
        answer = Integer.parseInt(answer_save);
        System.out.println(answer);
    }
}
