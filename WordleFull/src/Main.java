import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        newGame();
    }
    public static List readWords() {
        try {
            Scanner in = new Scanner(new File("words.txt"));
            List<String> words = new LinkedList<>();
            while(in.hasNextLine()){
                words.add(in.nextLine().toUpperCase());
            }
            return words;
        } catch (Exception e){
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        return null;
    }
    public static void newGame(){
        System.out.println("Welcome to Wordle!");
        System.out.println("Would you like to: ");
        System.out.println("a) Play by yourself");
        System.out.println("-or-");
        System.out.println("b) Have the AI play");
        Scanner usr = new Scanner(System.in);
        String response = usr.next();
        while(!response.equals("a")  && !response.equals("b")){
            System.out.println("Please enter an 'a' or a 'b'");
            response = usr.next();
        }
        if(response.equals("a")) {
            newUserGame();
        }
        else if (response.equals("b")){
            newAI();
        }
    }

    public static void newUserGame(){
        List<String> wordList = readWords();
        Random r = new Random();
        String answer = wordList.get(r.nextInt(wordList.size()));
        Game g = new Game(answer);
    }
    public static void newAI(){
        System.out.println("\nWould you like to:");
        System.out.println("  a)   Simulate a single game");
        System.out.println("-or-");
        System.out.println("  b)   Run statistics");
        Scanner r = new Scanner(System.in);
        String input = r.next().toLowerCase();
        while(!input.equals("a")  && !input.equals("b")){
            System.out.println("Please enter an 'a' or a 'b'");
            input = r.next().toLowerCase();
        }
        List<String> wordList = readWords();
        Random j = new Random();
        String answer = wordList.get(j.nextInt(wordList.size()));
        if(input.equals("a")){
            System.out.println(answer);
            new AI(wordList, answer);
        }
        else{
            int numGames = 0;
            System.out.println("How many games would you like to run? (Recommended: 1000");
            try {
                numGames = r.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a NUMBER greater than 0");
            }
            while(numGames <= 0){
                try{
                    numGames = r.nextInt();
                } catch (Exception e) {
                    System.out.println("Please enter a NUMBER GREATER THAN 0");
                }
            }
            new AIStats(answer, numGames);
        }


    }
}
