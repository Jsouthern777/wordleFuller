import java.io.File;
import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private String answer;
    private int turnCount;
    private String guess;
    private String board;
    private StringBuilder lineOne = new StringBuilder();
    private StringBuilder lineTwo = new StringBuilder();
    private StringBuilder lineThree = new StringBuilder();
    private StringBuilder lineFour = new StringBuilder();
    private StringBuilder lineFive = new StringBuilder();
    private String emptyLine = "_ _ _ _ _\n";

    private HashSet<Character> answerSet;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[42m";
    public static final String ANSI_YELLOW = "\u001B[43m";
    public static final String ANSI_WHITE = "\u001B[47m";

    public Game (String answer){
        this.answer = answer;
        answerSet = new HashSet<>();
        for (int i = 0; i < answer.length(); i++) {
            answerSet.add(answer.charAt(i));
        }
        System.out.println("New Game! ");
        System.out.println("You have five turns, and each word is only five letters. Good luck!");
        Scanner user = new Scanner(System.in);
        guess = "";
        while(!guess.equals(answer) || turnCount < 5){
            System.out.println("Please enter a guess of FIVE LETTERS:");
            guess = "";
            guess = user.next().toUpperCase();
            if(!inList(guess)){
                System.out.println("Not in word list!");
            }
            while(guess.length()!=5 || !inList(guess)) {
                System.out.println("Please enter a guess of FIVE LETTERS:");
                guess = "";
                guess = user.next().toUpperCase();
                if(!inList(guess)){
                    System.out.println("Not in word list!");
                }
            }
                turnCount++;

            if(turnCount == 1) {
                for (int i = 0; i < guess.length(); i++) {
                    if (guess.charAt(i) == answer.charAt(i)) {
                        lineOne.append(ANSI_GREEN + guess.charAt(i) + ANSI_RESET);
                    }
                    else if(wrongPosition(guess.charAt(i), guess)){
                        lineOne.append(ANSI_YELLOW + guess.charAt(i) + ANSI_RESET);
                    }
                    else if(notContains(guess.charAt(i))){
                        lineOne.append(ANSI_WHITE + guess.charAt(i) + ANSI_RESET);
                    }
                }
                lineOne.append("\n");
            }
            if(turnCount == 2) {
                for (int i = 0; i < guess.length(); i++) {
                    if (guess.charAt(i) == answer.charAt(i)) {
                        lineTwo.append(ANSI_GREEN + guess.charAt(i) + ANSI_RESET);
                    }
                    else if(wrongPosition(guess.charAt(i), guess)){
                        lineTwo.append(ANSI_YELLOW + guess.charAt(i) + ANSI_RESET);
                    }
                    else if(notContains(guess.charAt(i))){
                        lineTwo.append(ANSI_WHITE + guess.charAt(i) + ANSI_RESET);
                    }
                }
                lineTwo.append("\n");
            }
            if(turnCount == 3) {
                for (int i = 0; i < guess.length(); i++) {
                    if (guess.charAt(i) == answer.charAt(i)) {
                        lineThree.append(ANSI_GREEN + guess.charAt(i) + ANSI_RESET);
                    }
                    else if(wrongPosition(guess.charAt(i), guess)){
                        lineThree.append(ANSI_YELLOW + guess.charAt(i) + ANSI_RESET);
                    }
                    else if(notContains(guess.charAt(i))){
                        lineThree.append(ANSI_WHITE + guess.charAt(i) + ANSI_RESET);
                    }
                }
                lineThree.append("\n");
            }
            if(turnCount == 4) {
                for (int i = 0; i < guess.length(); i++) {
                    if (guess.charAt(i) == answer.charAt(i)) {
                        lineFour.append(ANSI_GREEN + guess.charAt(i) + ANSI_RESET);
                    }
                    else if(wrongPosition(guess.charAt(i), guess)){
                        lineFour.append(ANSI_YELLOW + guess.charAt(i) + ANSI_RESET);
                    }
                    else if(notContains(guess.charAt(i))){
                        lineFour.append(ANSI_WHITE + guess.charAt(i) + ANSI_RESET);
                    }

                }
                lineFour.append("\n");
            }
            if(turnCount == 5) {
                for (int i = 0; i < guess.length(); i++) {
                    if (guess.charAt(i) == answer.charAt(i)) {
                        lineFive.append(ANSI_GREEN + guess.charAt(i) + ANSI_RESET);
                    }
                    else if(wrongPosition(guess.charAt(i), guess)){
                        lineFive.append(ANSI_YELLOW + guess.charAt(i) + ANSI_RESET);
                    }
                    else if(notContains(guess.charAt(i))){
                        lineFive.append(ANSI_WHITE + guess.charAt(i) + ANSI_RESET);
                    }
                }
                lineFive.append("\n");
            }
            if(turnCount == 5){
                break;
            }
            boardString();
            if(guess.equals(answer)){
                break;
            }
        }
        if(guess.equals(answer) && turnCount == 1){
            System.out.println("Congratulations, you win! It only took you " + turnCount + " turn!");
        }
        else if(guess.equals(answer)){
            System.out.println("Congratulations, you win! It only took you " + turnCount + " turns!");
        }
        else if(turnCount > 4){
            boardString();
            System.out.println("You lose! You didn't guess the correct answer in 5 turns. The correct answer was " + answer);
        }
        System.out.println("Would you like to play again? Type 'a' for yes. Any other input will quit the game.");
        Scanner again = new Scanner(System.in);
        String play = again.next();
        if(play.equals("a")){
            Main.newGame();
        }
        else{
            System.out.println("Goodbye!");
        }

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

    public void boardString(){
        board = "";
        if(turnCount == 0){
            for(int i = turnCount; i < 5; i++){
                board += emptyLine;
            }
        }
        if(turnCount == 1){
            board += lineOne;
            for(int i = turnCount; i < 5; i++){
                board += emptyLine;
            }
        }
        if(turnCount == 2){
            board += lineOne;
            board += lineTwo;
            for(int i = turnCount; i < 5; i++){
                board += emptyLine;
            }
        }
        if(turnCount == 3){
            board += lineOne;
            board += lineTwo;
            board += lineThree;
            for(int i = turnCount; i < 5; i++){
                board += emptyLine;
            }
        }
        if(turnCount == 4){
            board += lineOne;
            board += lineTwo;
            board += lineThree;
            board += lineFour;
            for(int i = turnCount; i < 5; i++){
                board += emptyLine;
            }
        }
        if(turnCount == 5){
            board += lineOne;
            board += lineTwo;
            board += lineThree;
            board += lineFour;
            board += lineFive;
        }
        System.out.println(board);
    }
public boolean inList(String guess){
    try {
        Scanner in = new Scanner(new File("words.txt"));
        List<String> words = new LinkedList<>();
        while(in.hasNextLine()){
            words.add(in.nextLine().toUpperCase());
        }
        if(words.contains(guess)) {
            return true;
        }
        else{
            return false;
        }
    } catch (Exception e){
        System.out.println("An error occurred while reading the word list: " + e.getMessage());
    }
    return false;
}
}


