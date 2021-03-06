package essaywriter;

import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author Student
 */
public class WritingCenter {
    private static Random random = new Random();
    private static ArrayList<String> myEssay = new ArrayList<String>();
    private static int capitalizeNow = 0;
    
    public static Word getNextWord(Word thisWord){
        int ourChoice = random.nextInt(thisWord.getFollowingWords().size());
        return thisWord.getFollowingWords().get(ourChoice);
    }
    
    public static void generateEssay(int numberOfWords){
        int firstWord = random.nextInt(EssayWriter.getWords().size());
        Word currentWord = EssayWriter.getWords().get(firstWord);
        myEssay.add(wordAsString(currentWord));
        while(myEssay.size() < numberOfWords || !myEssay.get(myEssay.size() - 1).contains(".")){
            int nextWord = random.nextInt(currentWord.getFollowingWords().size());
            while(currentWord.getFollowingWords().get(nextWord).getWord().equalsIgnoreCase(Word.getErrorWord().getWord())) {
                System.out.println(currentWord.getWord());
                nextWord = random.nextInt(currentWord.getFollowingWords().size());
            }
            currentWord = currentWord.getFollowingWords().get(nextWord);
            myEssay.add(wordAsString(currentWord));
        }
    }
    
    public static String wordAsString(Word word){
        String output = word.getWord();
        if(myEssay.size() > 0) {
            if(word.getWord() == "PARAGRAPH_BREAK"){
                output = ".\n\t";
                capitalizeNow = 2;
            }
        }
        if(myEssay.size() > 0){
            if(word.getWord().contains(".") || word.getWord().contains(".\n\t")){
                capitalizeNow = 2;
            }
            if(capitalizeNow > 0 || word.isProperNoun()){
                if(output.length() > 0){
                    output = Character.toUpperCase(output.charAt(0)) + output.substring(1);
                    capitalizeNow --;
                }
            }
        }else{
            output = Character.toUpperCase(output.charAt(0)) + output.substring(1);
        }
        return output;
    }
    
    public static String getEssay(){
        String essay = "";
        for(int i = 0; i < myEssay.size(); i++){
            essay += myEssay.get(i);
            if(i + 1 < myEssay.size()){
                if(!myEssay.get(i + 1).contains(",") && !myEssay.get(i + 1).contains(".") && !myEssay.get(i + 1).contains(".\n\t")){
                    essay += " ";
                }
            }
        }
        return essay;
    }
}
