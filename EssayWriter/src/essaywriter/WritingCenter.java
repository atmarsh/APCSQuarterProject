package essaywriter;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Student
 */
public class WritingCenter {
    private static Random random = new Random();
    private static ArrayList<String> myEssay = new ArrayList<String>();

    public static ArrayList<String> getMyEssay() {
        return myEssay;
    }
    
    public static Word getNextWord(Word thisWord){
        int ourChoice = random.nextInt(thisWord.getFollowingWords().size());
        return thisWord.getFollowingWords().get(ourChoice);
    }
    
    public static void generateEssay(List<Word> words){
        int numberOfWords = words.size();
        int firstWord = random.nextInt(numberOfWords);
        Word currentWord = words.get(firstWord);
        myEssay.add(wordAsString(currentWord));
        while(myEssay.size() < numberOfWords){
            int nextWord = random.nextInt(currentWord.getFollowingWords().size());
            currentWord = currentWord.getFollowingWords().get(nextWord);
            myEssay.add(wordAsString(currentWord));
        }
    }
    
    public static String wordAsString(Word word){
        String output = word.getWord();
        if(word.getWord() == "\n"){
            output = "\n\t.";
        }
        if(myEssay.size() > 0){
            if(myEssay.get(myEssay.size() - 1) == "." || myEssay.get(myEssay.size() - 1) == "\n\t."){
                output = Character.toUpperCase(output.charAt(0)) + output.substring(1);
            }
        }else{
            output = Character.toUpperCase(output.charAt(0)) + output.substring(1);
        }
        output += " ";
        return output;
    }
    
    public static String getEssay(){
        String essay = "";
        for(int i = 0; i < myEssay.size(); i++){
            essay += myEssay.get(i);
        }
        return essay;
    }
}
