import java.util.*;
public class Pro_KaKao_Blind_표_병합 {
    private static String[][] table = new String[51][51];
    private static int[][] tableMerge = new int[51][51];
    private static int number = 1;
    private static List<String> ans = new LinkedList<>();

    public static void main(String[] args) {
        String[] commands = {"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"};
        System.out.println(Arrays.toString(solution(commands)));
    }

    public static String[] solution(String[] commands) {
        String[] answer = {};
        init();
        for(String command : commands) {
            String[] commandSplit = command.split(" ");
            commandStart(commandSplit);
        }
        answer = ans.toArray(String[]::new);
        return answer;
    }

    public static void init() {
        for(int r = 1; r <= 50; r++) {
            for(int c = 1; c <= 50; c++) {
                table[r][c] = "";
                tableMerge[r][c] = 0;
            }
        }
    }

    public static void commandStart(String[] command) {
        if(command[0].equals("UPDATE")) {
            update(command);
        }
        else if(command[0].equals("MERGE")) {
            merge(command);
        }
        else if(command[0].equals("UNMERGE")) {
            unMerge(command);
        }
        else {
            print(command);
        }
    }

    public static void tableCycle(String compare, String value) {
        for(int r = 1; r <= 50; r++) {
            for(int c = 1; c <= 50; c++) {
                if(table[r][c].equals(compare)) {
                    table[r][c] = value;
                }
            }
        }
    }

    public static void tableMergeCycle(int compare, String tableValue, int tableMergeValue) {
        for(int r = 1; r <= 50; r++) {
            for(int c = 1; c <= 50; c++) {
                if(tableMerge[r][c] == compare) {
                    table[r][c] = tableValue;
                    tableMerge[r][c] = tableMergeValue;
                }
            }
        }
    }

    public static void update(String[] command) {
        if(command.length == 4) {
            int r1 = Integer.parseInt(command[1]), c1 = Integer.parseInt(command[2]);
            if(tableMerge[r1][c1] != 0 && !table[r1][c1].equals("")) {
                tableCycle(table[r1][c1], command[3]);
                return;
            }
            table[r1][c1] = command[3];
            return;
        }
        tableCycle(command[1], command[2]);
    }

    public static void merge(String[] command) {
        int r1 = Integer.parseInt(command[1]), c1 = Integer.parseInt(command[2]);
        int r2 = Integer.parseInt(command[3]), c2 = Integer.parseInt(command[4]);
        if(r1 == r2 && c1 == c2) {
            return;
        }
        if(tableMerge[r1][c1] != 0 || tableMerge[r2][c2] != 0){
            if(table[r1][c1].equals("") && !table[r2][c2].equals("")) {
                tableCycle(table[r1][c1], table[r2][c2]);
                tableMergeCycle(tableMerge[r1][c1], table[r2][c2], tableMerge[r2][c2]);
                return;
            }
            if(tableMerge[r1][c1] != 0 && tableMerge[r2][c2] != 0 && table[r1][c1].equals("")){
                table[r1][c1] = table[r2][c2];
                tableMerge[r1][c1] = tableMerge[r2][c2];
                return;
            }
            else if(tableMerge[r1][c1] != 0 && tableMerge[r2][c2] != 0 && table[r2][c2].equals("")){
                table[r2][c2] = table[r1][c1];
                tableMerge[r2][c2] = tableMerge[r1][c1];
                return;
            }
            else if(tableMerge[r1][c1] != 0 && tableMerge[r2][c2] != 0) {
                tableCycle(table[r2][c2], table[r1][c1]);
                tableMergeCycle(tableMerge[r2][c2], table[r1][c1], tableMerge[r1][c1]);
                return;
            }
            else if(tableMerge[r1][c1] != 0) {
                table[r2][c2] = table[r1][c1];
                tableMerge[r2][c2] = tableMerge[r1][c1];
                return;
            }
            else if(tableMerge[r2][c2] != 0) {
                table[r1][c1] = table[r2][c2];
                tableMerge[r1][c1] = tableMerge[r2][c2];
                return;
            }
        }
        tableMerge[r1][c1] = number;
        tableMerge[r2][c2] = number;
        number++;
        table[r2][c2] = table[r1][c1];
    }

    public static void unMerge(String[] command) {
        int r1 = Integer.parseInt(command[1]), c1 = Integer.parseInt(command[2]);
        int temp = tableMerge[r1][c1];
        int tempNumber = tableMerge[r1][c1];
        String tempString = table[r1][c1];
        tableMergeCycle(temp, "", 0);
        table[r1][c1] = tempString;
        tableMerge[r1][c1] = tempNumber;
    }

    public static void print(String[] command) {
        int r1 = Integer.parseInt(command[1]), c1 = Integer.parseInt(command[2]);
        if(table[r1][c1].equals("")){
            ans.add("EMPTY");
            return;
        }
        ans.add(table[r1][c1]);
    }
}
