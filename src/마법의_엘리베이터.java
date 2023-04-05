import java.util.*;
public class 마법의_엘리베이터 {
    private static int count = 0;
    private static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) {
        int storey = 16;
        System.out.println(solution(storey));
    }

    public static int solution(int storey) {
        int answer = 0;

        answer = bfs(storey);

        return answer;
    }

    public static int bfs(int storey) {
        int answer = Integer.MAX_VALUE;
        queue.add(new Node(storey, 0));

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            // 10으로 나누어 떨어져야 하기 때문에 일의 자리만 남는다.
            int nodeStorey = node.storey % 10;

            if(node.storey < 10) {
                // 5보다 작거나 같은 경우
                answer = Math.min(answer, node.result + nodeStorey);
                // 5보다 크고 10보다작은 경우
                answer = Math.min(answer, node.result + 10 - nodeStorey + 1);
                continue;
            }

            // 나눠 떨어진 값이 0이라면 10,100, 20, 200 등과 같으므로, 10으로 나눠 십의 자리를 일의 자리로 바꿔준다.
            // ex ) 150 -> 15 이런식으로 변경
            if(nodeStorey == 0) {
                int s = node.storey / 10;
                queue.add(new Node(s, node.result));
                continue;
            }
            /**
             값을 하나는 0으로 가까워지게 하나는 10으로 가까워지게 경우의 수를 두개로 나눠 생각한다.
             10으로 올라가는 것은 숫자가 커지는 것이지만, 위 if문에서 10으로 나눠버리기 때문에 무한 루프에 빠지는 경우는 없다.
             Node 클래스에는 storey와 value를 담아주는데, value는 일의 자리에서만 생각하기 때문에 10에서 빼준 숫자만큼
             더해줘야한다. -> 여기서 +1 해줄 필요가 없음
             */
            queue.add(new Node(node.storey - nodeStorey, node.result + nodeStorey));
            queue.add(new Node(node.storey + (10 - nodeStorey), node.result + 10 - nodeStorey));

        }

        return answer;
    }
}

class Node {
    int storey;
    int result;

    public Node(int storey, int result) {
        this.storey = storey;
        this.result = result;
    }
}
