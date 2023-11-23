package kakao;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Pro_KaKao_표_편집 {
    static boolean[] deleteCheck;
    static Stack<Index> deleteStack = new Stack<>();
    static List<Index> map = new ArrayList<>();
    static Index cur; // 현재 노드 위치

    public static void main(String[] args) {
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};

        System.out.println(solution(n, k, cmd));
    }
    public static String solution(int n, int k, String[] cmd) {
        deleteCheck = new boolean[n];

        // Step 1. 기본 노드와 배열 초기화
        for(int i = 0; i < n; i++) {
            deleteCheck[i] = true;
            map.add(new Index(i));
        }

        // Step 2. 각 노드에 대한 연결
        init(n, k);

        // Step 3. 실행
        for(String commend : cmd) {
            if(commend.length() > 1) {
                StringTokenizer st = new StringTokenizer(commend);
                String status = st.nextToken();
                int indexMove = Integer.parseInt(st.nextToken());
                move(indexMove, status); // 위 아래 움직임
            }
            else {
                if(commend.equals("C")) {
                    delete(); // 삭제
                }
                else {
                    rollback(); // 복구
                }
            }
        }

        // Step 4. 결과 출력
        StringBuilder answer = new StringBuilder();
        for(boolean type : deleteCheck) {
            if(type) {
                answer.append("O");
            }
            else {
                answer.append("X");
            }
        }
        return answer.toString();
    }

    // LinkedList 로 만들어주는 작업
    // 노드간 앞과 뒤에 대한 연결을 나타냄
    public static void init(int n, int k) {
        map.get(0).next = map.get(1);
        map.get(map.size()-1).prev = map.get(map.size()-2);

        for(int i = 1; i < n-1; i++) {
            map.get(i).prev = map.get(i-1);
            map.get(i).next = map.get(i+1);
        }

        cur = map.get(k);
    }

    // 연결된 노드간 앞과 뒤를 움직이는 함수
    public static void move(int indexMove, String status) {
        if(status.equals("U")) {
            while(indexMove-- > 0){
                cur = cur.prev;
            }
        }
        else {
            while(indexMove-- > 0) {
                cur = cur.next;
            }
        }
    }

    // map 의 remove 를 쓰는 것이 아닌 연결되어 있는 노드만 갱신
    public static void delete() {
        deleteCheck[cur.index] = false;
        deleteStack.push(cur);
        Index prevIndex = cur.prev;
        Index nextIndex = cur.next;

        if(prevIndex != null && nextIndex != null) {
            prevIndex.next = nextIndex;
            nextIndex.prev = prevIndex;
            cur = nextIndex;
        } else if (prevIndex != null) {
            prevIndex.next = null;
            cur = prevIndex;
        } else if (nextIndex != null){
            nextIndex.prev = null;
            cur = nextIndex;
        }

    }

    // 복구
    public static void rollback() {
        Index index = deleteStack.pop();
        deleteCheck[index.index] = true;

        Index prevIndex = index.prev;
        Index nextIndex = index.next;

        // 이전 노드가 살아 있다면 이전 노드에 다음 노드는 현재 노드로 갱신
        if(prevIndex != null) {
            prevIndex.next = index;
        }
        // 다음 노드가 살아 있다면 다음 노드에 이전 노드는 현재 노드로 갱신
        if(nextIndex != null) {
            nextIndex.prev = index;
        }
    }

    public static class Index {
        int index;
        Index prev = null;
        Index next = null;
        public Index(int index) {
            this.index = index;
        }
    }
}
