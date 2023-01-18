public class S_W_멀쩡한_사각형 {
    public static void main(String[] args) {
        int w = 8;
        int h = 12;
        System.out.println(solution(w, h));
    }

    /**
     * @param w : 가로 길이
     * @param h : 세로 길이
     * @return 직사각형 속에서 사용 가능한 정사각형만 모으기
     */
    public static long solution(int w, int h) {
        long answer = 1;
        long longW = (long) w;
        long longH = (long) h;
        if(longW == longH)
            return (longW*longH)-longW;
        return longW * longH - (longW + longH - searchPattern(longW, longH));
    }

    /**
     * @param w : 가로 길이
     * @param h : 세로 길이
     * @return 유클리드 호제법을 이용한 패턴 갯수
     */
    public static long searchPattern(long w, long h){
        long tmp = 0;
        while(h != 0){
            tmp = w % h;
            w = h;
            h = tmp;
        }
        return w;
    }

}
