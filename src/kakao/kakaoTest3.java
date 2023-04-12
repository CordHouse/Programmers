package kakao;

public class kakaoTest3 {
    public static void main(String[] args) {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        int[] count = {1,1,1,1,1};
        for(int k = 0; k < 5; k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (k < 4 && places[k][i].split("")[j].equals("P") && (places[k][i+1].split("")[j+1].equals("P") || places[k][i+1].split("")[j].equals("P") || places[k][i].split("")[j+1].equals("P") || (places[k][i+2].split("")[j].equals("P") && !places[k][i+1].split("")[j].equals("X")) || (places[k][i].split("")[j+2].equals("P") && !places[k][i].split("")[j+1].equals("X")))) {
                        if(j > 1 && places[k][i].split("")[j+1].equals("P") && places[k][i+1].split("")[j].equals("P")) {
                            System.out.println("역");
                            count[k]--;
                        }
                    }
                    else if (k == 4 && places[i][j].equals("P") && (places[k][i].split("")[j+1].equals("P") || (places[k][i].split("")[j+2].equals("P") && !places[k][i].split("")[j+1].equals("X")))) {
                        count[k]--;
                    }
                }
            }
        }

        for(int i = 0; i<5; i++) {
            if (count[i] > 0)
                System.out.println("0");
            else if(count[i] == 0)
                System.out.println("1");
        }
    }
}
