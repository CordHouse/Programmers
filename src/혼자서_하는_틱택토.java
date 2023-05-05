public class 혼자서_하는_틱택토 {
    public static void main(String[] args) {
        String[] board = {};
        System.out.println(solution(board));
    }

    // 틱택토가 끝날 수 있는 기본 틀
    private static String[] possible = {
            "OOO......",
            "...OOO...",
            "......OOO",
            "O..O..O..",
            ".O..O..O.",
            "..O..O..O",
            "O...O...O",
            "..O.O.O..",
    };
    public static int solution(String[] board) {
        int answer = 1;
        int o = 0;
        int x = 0;
        String[][] map = new String[3][3];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 3; i++) {
            String[] line = board[i].split("");
            for(int j = 0; j < 3; j++) {
                map[i][j] = line[j];
                if(line[j].equals("O")) {
                    o++;
                }
                else if(line[j].equals("X")) {
                    x++;
                }
                sb.append(line[j]);
            }
        }

        // "O"를 표시할 차례인데 "X"를 표시하거나 반대로 "X"를 표시할 차례인데 "O"를 표시한다.
        // O < X 개수가 더 많은 경우
        if(o < x || o-x > 1) {
            return 0;
        }

        // 선공이나 후공이 승리해서 게임이 종료되었음에도 그 게임을 진행한다.
        // O or X가 1줄이 만들어진 경우
        boolean oBingo = oneBingoCheck(sb.toString(), "O");
        boolean xBingo = oneBingoCheck(sb.toString(), "X");

        // 선공이 먼저 원빙고를 만들고 이후 후공이 원빙고를 만들면서 빈칸이 남아있지 않는 경우
        // return = 0 이어갈 수 없다.
        // return = 1 이어갈 수 있다.
        if(!oBingo && !xBingo && !sb.toString().contains(".")) {
            return 0;
        }
        // 선공만 빙고를 완성한 경우
        if(!oBingo) {
            // 후공이 빙고 완성을 못하고 o가 먼저 앞서가고 있다면 이어갈 수 있다.
            if(xBingo && o-x == 1) {
                return 1;
            }
            // 그게 아니라면 이어갈 수 없다.
            return 0;
        }

        // 후공만 빙고를 완성한 경우
        if(!xBingo) {
            // 선공이 완성한 상태에서 후공이 빙고를 완성하고 o 와 x가 같다는 건 이어갈 수 있다는 뜻이다.
            if(o == x) {
                return 1;
            }
            // 그게 아니라면 이어갈 수 없다.
            return 0;
        }

        return answer;
    }

    // 빙고가 되는 구간 찾기
    public static boolean oneBingoCheck(String input, String status) {
        if(status.equals("O")) {
            for(int i = 0; i < possible.length; i++) {
                if(input.matches(possible[i])) {
                    return false;
                }
            }
        }
        else {
            for(int i = 0; i < possible.length; i++) {
                String impossible = possible[i].replaceAll("O", "X");
                if(input.matches(impossible)) {
                    return false;
                }
            }
        }
        return true;
    }

}
