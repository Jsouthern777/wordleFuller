import java.util.*;

public class AI {

    private List<String> wordList;
    private String answer;
    private Set<Character> answerSet;
    private Set<Character> wrongPos;
    private Set<Character> rightPos;
    private Set<Character> notIn;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[42m";
    public static final String ANSI_YELLOW = "\u001B[43m";
    public static final String ANSI_WHITE = "\u001B[47m";


    public AI (List<String> wordList, String answer){
        this.wordList = wordList;
        this.answer = answer;
        answerSet = new HashSet<>();
        wrongPos = new HashSet<>();
        rightPos = new HashSet<>();
        notIn = new HashSet<>();
        for(int i = 0; i < answer.length(); i++){
            answerSet.add(answer.charAt(i));
        }
        newGuess();
    }
    public void newGuess(){
        StringBuilder str = new StringBuilder();
        Random r = new Random();
        String newGuess = wordList.get(r.nextInt(wordList.size()));
            for (int i = 0; i < newGuess.length(); i++) {
             if (newGuess.charAt(i) == answer.charAt(i)) {
                 str.append(ANSI_GREEN + newGuess.charAt(i) + ANSI_RESET);
                 rightPos.add(newGuess.charAt(i));
             }
                else if(wrongPosition(newGuess.charAt(i), newGuess)){
                    str.append(ANSI_YELLOW + newGuess.charAt(i) + ANSI_RESET);
                    wrongPos.add(newGuess.charAt(i));
                }
                else if(notContains(newGuess.charAt(i))){
                    str.append(ANSI_WHITE + newGuess.charAt(i) + ANSI_RESET);
                    notIn.add(newGuess.charAt(i));
                }
            }
        System.out.println(str);

    }

    public void updateGuess(List<String> words){
       setWordList(words);
    }
    public boolean isValidWord(String word){
        for(int i = 0; i < 5; i++){
            if(notIn.contains(word.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public boolean wrongPosition(Character guessLetter, String guess) {
        for (int i = 0; i < answerSet.size(); i++) {
            if (answerSet.contains(guessLetter) && answer.charAt(i) != guess.charAt(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean notContains(Character guessLetter) {
        return !answerSet.contains(guessLetter);
    }

    public List<String> getWordList() {
        return wordList;
    }

    public void setWordList(List<String> wordList) {
        this.wordList = wordList;
    }
}
