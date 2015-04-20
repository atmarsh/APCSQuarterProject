/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essaywriter;

import java.util.ArrayList;

/**
 *
 * @author atmarsh
 */
public class Word {
    
    private String word;
    private ArrayList<Word> followingWords;
    
    private Word(String word) {
        this.word = word;
        followingWords = new ArrayList<>();
    }
    
    public static Word generateNewWord(String newWord, Word wordAfter) {
        Word word = new Word(newWord);
        word.addWordAfter(wordAfter);
        return word;
    }
    
    public void addWordAfter(Word wordAfter) {
        followingWords.add(wordAfter);
    }

    public String getWord() {
        return word;
    }

    public ArrayList<Word> getFollowingWords() {
        return followingWords;
    }
}
