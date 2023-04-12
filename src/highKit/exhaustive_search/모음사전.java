package highKit.exhaustive_search;

import java.util.*;

public class 모음사전 {
    public static void main(String[] args) {
        String word = "AAAAE";
        System.out.println(solution(word));
    }
    static List<String> wordList = new LinkedList<>();
    public static int solution(String word) {
        int answer = 0;
        String[] moumWord = {"A","E","I","O","U"};
        for(int count = 1; count <= moumWord.length; count++){
            StringBuilder wordMake = new StringBuilder();
            wordCycle(moumWord, count, wordMake);
        }
        Collections.sort(wordList);
        answer = searchWord(word);
        return answer;
    }

    public static void wordCycle(String[] moumWord, int count, StringBuilder wordMake){
        if(count == 0){
            wordList.add(wordMake.toString());
            return;
        }
        for(int i = 0; i < moumWord.length; i++){
            wordMake.append(moumWord[i]);
            wordCycle(moumWord, count-1, wordMake);
            wordMake.setLength(wordMake.length()-1);
        }
    }

    public static int searchWord(String word){
        for(int i = 0; i < wordList.size(); i++){
            if(wordList.get(i).equals(word)){
                return i+1;
            }
        }
        return 0;
    }

}
