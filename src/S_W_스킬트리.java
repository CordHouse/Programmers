import java.util.*;
public class S_W_스킬트리 {
    private static List<String> skillList = new LinkedList<>();
    private static int answer = 0;
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        System.out.println(solution(skill, skill_trees));
    }
    public static int solution(String skill, String[] skill_trees) {
        divSkill(skill.toCharArray());
        for(String value : skill_trees){
            checkSkill(value.toCharArray(), 0);
        }
        return answer;
    }

    public static void divSkill(char[] skill){
        for(char value : skill){
            skillList.add(String.valueOf(value));
        }
    }

    public static void checkSkill(char[] skill_trees, int count){
        for(char skill : skill_trees){
            if(skillList.contains(String.valueOf(skill))){
                if(!skillList.get(count).equals(String.valueOf(skill))){
                    return;
                }
                count++;
            }
        }
        answer++;
    }

}
