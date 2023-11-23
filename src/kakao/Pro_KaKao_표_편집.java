package kakao;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Pro_KaKao_표_편집 {
    static boolean[] deleteCheck;
    static Stack<Index> deleteStack = new Stack<>();
    static List<Index> map = new ArrayList<>();
    static Index cur;

    public static void main(String[] args) {
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};

        System.out.println(solution(n, k, cmd));
    }
    public static String solution(int n, int k, String[] cmd) {
        deleteCheck = new boolean[n];

        for(int i = 0; i < n; i++) {
            deleteCheck[i] = true;
            map.add(new Index(i));
        }

        init(n, k);

        for(String commend : cmd) {
            if(commend.length() > 1) {
                StringTokenizer st = new StringTokenizer(commend);
                String status = st.nextToken();
                int indexMove = Integer.parseInt(st.nextToken());
                move(indexMove, status);
            }
            else {
                if(commend.equals("C")) {
                    delete();
                }
                else {
                    rollback();
                }
            }
        }

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

    public static void init(int n, int k) {
        map.get(0).next = map.get(1);
        map.get(map.size()-1).prev = map.get(map.size()-2);

        for(int i = 1; i < n-1; i++) {
            map.get(i).prev = map.get(i-1);
            map.get(i).next = map.get(i+1);
        }

        cur = map.get(k);
    }

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

    public static void delete() {
        deleteCheck[cur.index] = false;
        deleteStack.push(cur);
        Index prevIndex = cur.prev;
        Index nextIndex = cur.next;

        if(prevIndex != null && nextIndex != null) {
            prevIndex.next = nextIndex;
            nextIndex.prev = prevIndex;
            cur = nextIndex;
        } else if (prevIndex != null && nextIndex == null) {
            prevIndex.next = nextIndex;
            cur = prevIndex;
        } else if (prevIndex == null && nextIndex != null){
            nextIndex.prev = prevIndex;
            cur = nextIndex;
        }

    }

    public static void rollback() {
        Index index = deleteStack.pop();
        deleteCheck[index.index] = true;

        Index prevIndex = index.prev;
        Index nextIndex = index.next;

        if(prevIndex != null) {
            prevIndex.next = index;
        }
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
