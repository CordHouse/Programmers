import java.util.*;
public class 베스트앨범 {
    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        System.out.println(Arrays.toString(solution(genres, plays)));
    }
    public static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        // value 를 놓음으로써 index : number 로 만든다.
        HashMap<String, HashMap<Integer, Integer>> map = new HashMap<>();
        // 각 장르마다 누적 값을 담아준다.
        Map<String, Integer> total = new HashMap<>();

        for(int i = 0; i < genres.length; i++) {
            // 장르가 없다면 초기화 해주고 값을 넣어준다.
            if(!map.containsKey(genres[i])) {
                HashMap<Integer, Integer> value = new HashMap<>();
                map.put(genres[i], value);
            }
            // value 는 index : number 로 담아준다.
            map.get(genres[i]).put(i, plays[i]);
            // total 에 장르가 없다면 해당 값을 담아주고
            if(!total.containsKey(genres[i])) {
                total.put(genres[i], plays[i]);
                continue;
            }
            // 있다면 기존 값에 누적시킨다.
            total.put(genres[i], total.get(genres[i])+plays[i]);
        }

        // 장르에 대한 값을 List 로 담아준다.
        List<String> totalList = new ArrayList<>(total.keySet());
        // 장르를 최고 누적 값을 기준으로 내림차순 정렬한다.
        Collections.sort(totalList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(total.get(o1).equals(total.get(o2))) {
                    return total.get(o1) - total.get(o2);
                }
                return total.get(o2) - total.get(o1);
            }
        });

        // arr 는 답을 담아놓을 변수로 선언
        List<Integer> arr = new ArrayList<>();
        for(String key : totalList) {
            // map 에서 장르에 대한 index 값을 가져온다.
            List<Integer> list = new ArrayList<>(map.get(key).keySet());
            // index 값은 number를 기준으로 내림차순 정렬된다.
            Collections.sort(list, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if(map.get(key).get(o1) == map.get(key).get(o2)) {
                        return map.get(key).get(o1) - map.get(key).get(o2);
                    }
                    return map.get(key).get(o2) - map.get(key).get(o1);
                }
            });
            // 2개 까지만 담을 수 있기 때문에 1보다 큰 경우 실행
            if(list.size() > 1) {
                arr.add(list.get(0));
                arr.add(list.get(1));
                continue;
            }
            // 그게 아닌 경우 하나인 경우 임으로 하나만 실행
            arr.add(list.get(0));
        }

        answer = arr.stream().mapToInt(i -> i).toArray();

        return answer;
    }
}
