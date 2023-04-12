package kakao;

import java.util.*;
public class Pro_KaKao_Blind_순위_검색 {
    static HashMap<String, List<Integer>> map = new HashMap<>();

    public static void main(String[] args){
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        System.out.println(Arrays.toString(solution(info, query)));
    }

    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        for(int i = 0; i < info.length; i++){
            dfs(0, "", info[i].split(" "));
        }

        // 정렬 -> List Value 를 정렬
        for(String key : map.keySet()){
            Collections.sort(map.get(key));
        }

        for(int i = 0; i < query.length; i++){
            query[i] = query[i].replaceAll(" and ", "");
            String[] score = query[i].split(" ");
            answer[i] = binarySearch(score[0], Integer.parseInt(score[1]));
        }

        return answer;
    }

    // 이분 탐색
    public static int binarySearch(String str, int score){
        if(!map.containsKey(str))
            return 0;
        List<Integer> input = map.get(str);

        int start = 0, end = input.size()-1;

        while(start <= end){
            int mid = (start + end) / 2;
            if(input.get(mid) < score){
                start = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
        return input.size() - start;
    }

    // 문자열 조합
    public static void dfs(int cnt, String str, String[] p){
        if(cnt == 4){
            if(!map.containsKey(str)){
                List<Integer> list = new ArrayList<>();
                map.put(str, list);
            }
            map.get(str).add(Integer.parseInt(p[4]));
            return;
        }

        dfs(cnt+1, str+"-", p);
        dfs(cnt+1, str+p[cnt], p);
    }
}
