package essaywriter;

import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author Student
 */
public class WritingCenter {
    Random random = new Random();
    ArrayList<String> myEssay = new ArrayList<String>();
    
    public Word getNextWord(Word thisWord){
        int ourChoice = random.nextInt(thisWord.getFollowingWords().size());
        return thisWord.getFollowingWords().get(ourChoice);
    }
    
    public void generateEssay(int numberOfWords){
        int firstWord = random.nextInt(EssayWriter.getWords().size());
        Word currentWord = EssayWriter.getWords().get(firstWord);
        myEssay.add(wordAsString(currentWord));
        while(myEssay.size() < numberOfWords){
            int nextWord = random.nextInt(currentWord.getFollowingWords().size());
            while(currentWord.getFollowingWords().get(nextWord) == Word.getErrorWord()){
                nextWord = random.nextInt(currentWord.getFollowingWords().size());
            }
            currentWord = currentWord.getFollowingWords().get(nextWord);
            myEssay.add(wordAsString(currentWord));
        }
    }
    
    public String wordAsString(Word word){
        String output = word.getWord();
        if(myEssay.get(myEssay.size() - 1) == "PARAGRAPH_BREAK"){
            output = "\n\t.";
        }
        if(myEssay.size() > 0){
            if(myEssay.get(myEssay.size() - 1) == "." || myEssay.get(myEssay.size() - 1) == "\n\t."){
                output = Character.toUpperCase(output.charAt(0)) + output.substring(1);
            }
        }else{
            output = Character.toUpperCase(output.charAt(0)) + output.substring(1);
        }
        return output;
    }
    
    public String getEssay(){
        String essay = "";
        for(int i = 0; i < myEssay.size(); i++){
            essay += myEssay.get(i);
            if(myEssay.get(i) != "," && myEssay.get(i) != "." && myEssay.get(i) != "\n\t."){
                essay += myEssay.get(i);
            }
        }
        return essay;
    }
}
