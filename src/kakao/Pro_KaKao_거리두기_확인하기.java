package kakao;

import java.util.*;
public class Pro_KaKao_거리두기_확인하기 {
    static int[] vx = {-1, 1, 0, 0};
    static int[] vy = {0, 0, -1, 1};
    public static void main(String[] args) {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        System.out.println(Arrays.toString(solution(places)));
    }
    public static int[] solution(String[][] places) {
        int[] answer = {};

        answer = tmp(places);

        return answer;
    }

    public static int[] tmp(String[][] places){
        int[] ans = new int[5];
        String[][] arr = new String[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                char[] c = places[i][j].toCharArray();
                for(int k = 0; k < 5; k++){
                    arr[j][k] = String.valueOf(c[k]);
                }
            }
            ans[i] = run(arr);
        }
        return ans;
    }

    public static int run(String[][] places){
        List<Integer> result = new LinkedList<>();
        boolean[][] check = new boolean[5][5];
        for(int i = 0; i < places.length; i++){
            for(int j = 0; j < places[i].length; j++){
                if(places[i][j].equals("P")){
                    result.add(table(places, i, j, check));
                }
            }
        }
        if(result.contains(0)){
            return 0;
        }
        return 1;
    }

    public static int table(String[][] places, int nx, int ny, boolean[][] check){
        if(check[nx][ny] && places[nx][ny].equals("P")){
            return 0;
        }
        for(int i = 0; i < 4; i++){
            int x = nx + vx[i];
            int y = ny + vy[i];
            if(x >= 0 && y >= 0 && x < 5 && y < 5){
                if(places[x][y].equals("O")){
                    for(int j = 0; j < 4; j++){
                        int xx = x + vx[j];
                        int yy = y + vy[j];
                        if(xx >= 0 && yy >= 0 && xx < 5 && yy < 5){
                            check[xx][yy] = true;
                        }
                    }
                }
                else if(places[x][y].equals("P")){
                    return 0;
                }

            }
        }
        return 1;
    }
}
