import java.util.*;
public class Pro_KaKao_Blind_표_병합 {
    private static String[][] table = new String[51][51];
    private static int[][] tableNumber = new int[51][51];
    private static List<String> ans = new LinkedList<>();
    private static int number = 1;

    public static void main(String[] args) {
        String[] commands = {"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"};
        System.out.println(Arrays.toString(solution(commands)));
    }

    public static String[] solution(String[] commands) {
        init();
        for(String command : commands) {
            startCommand(command.split(" "));
        }
        String[] answer = new String[ans.size()];
        for(int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }

    public static void init(){
        for(int r = 1; r < 51; r++) {
            for(int c = 1; c < 51; c++) {
                table[r][c] = "";
                tableNumber[r][c] = 0;
            }
        }
    }

    public static void startCommand(String[] command) {
        String cmd = command[0];
        if(cmd.equals("UPDATE")) {
            update(command);
        }
        else if(cmd.equals("MERGE")) {
            merge(command);
        }
        else if(cmd.equals("UNMERGE")) {
            unMerge(command);
        }
        else {
            print(command);
        }
    }

    public static void update(String[] command) {
        // 위치를 지정해서 값 넣기
        if(command.length == 4) {
            int r1 = Integer.parseInt(command[1]), c1 = Integer.parseInt(command[2]);
            if(tableNumber[r1][c1] == 0) {
                table[r1][c1] = command[3];
                return;
            }
            for(int r = 1; r < 51; r++) {
                for(int c = 1; c < 51; c++) {
                    if(tableNumber[r][c] == tableNumber[r1][c1]) {
                        table[r][c] = command[3];
                    }
                }
            }
            return;
        }
        for(int r = 1; r < 51; r++) {
            for(int c = 1; c < 51; c++) {
                if(table[r][c].equals(command[1])) {
                    table[r][c] = command[2];
                }
            }
        }
    }

    public static void merge(String[] command) {
        int r1 = Integer.parseInt(command[1]), c1 = Integer.parseInt(command[2]);
        int r2 = Integer.parseInt(command[3]), c2 = Integer.parseInt(command[4]);
        int min = tableNumber[r2][c2];
        int max = tableNumber[r1][c1];

        // 선택한 두 위치의 셀이 같은 값일 경우 무시
        if(tableNumber[r1][c1] == tableNumber[r2][c2] && tableNumber[r1][c1] != 0 && tableNumber[r2][c2] != 0) {
            return ;
        }

        if(table[r1][c1].equals("") && !table[r2][c2].equals("")) {
            if(tableNumber[r1][c1] == 0 && tableNumber[r2][c2] == 0) {
                set(r1, c1, r2, c2);
                return;
            }
            if(min == 0) {
                table[r1][c1] = table[r2][c2];
                tableNumber[r1][c1] = tableNumber[r2][c2];
                return;
            }
            cycle(min, max, table[r2][c2]);
        }
        else {
            if(tableNumber[r1][c1] == 0 && tableNumber[r2][c2] == 0) {
                set(r2, c2, r1, c1);
                return;
            }
            if(min == 0) {
                table[r2][c2] = table[r1][c1];
                tableNumber[r2][c2] = tableNumber[r1][c1];
                return;
            }
            cycle(min, max, table[r1][c1]);
        }
    }

    public static void set(int r1, int c1, int r2, int c2) {
        tableNumber[r1][c1] = number;
        tableNumber[r2][c2] = number;
        table[r1][c1] = table[r2][c2];
        number ++;
    }

    public static void cycle(int min, int max, String value) {
        for(int r = 1; r < 51; r++) {
            for(int c = 1; c < 51; c++) {
                if(tableNumber[r][c] == min) {
                    table[r][c] = value;
                    tableNumber[r][c] = max;
                }
            }
        }
    }

    public static void unMerge(String[] command) {
        int r1 = Integer.parseInt(command[1]), c1 = Integer.parseInt(command[2]);
        String value = table[r1][c1];
        if(tableNumber[r1][c1] == 0) {
            return;
        }
        cycle(tableNumber[r1][c1], 0, "");
        table[r1][c1] = value;
    }

    public static void print(String[] command) {
        int r1 = Integer.parseInt(command[1]), c1 = Integer.parseInt(command[2]);
        if(table[r1][c1].equals("")) {
            ans.add("EMPTY");
            return;
        }
        ans.add(table[r1][c1]);
    }


}
