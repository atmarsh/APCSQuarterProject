/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essaywriter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author atmarsh
 */
public class Parser {
    
    private static char[] punctuationArray = {'.', ',', '!', '?'};
    private static ArrayList<Word> words; 
    
    public static ArrayList<Word> parse(String text) {
        text = text.toLowerCase();
        String temp = "";
        Word lastWord = null;
        words = new ArrayList<>();
        for(int i = text.length() - 1; i >= 0; i--) {
            if(Character.isLetterOrDigit(text.charAt(i))) {
                temp = text.charAt(i) + temp;
            } else if(Character.isSpaceChar(text.charAt(i))) {
                lastWord = updateList(temp, lastWord);
                temp = "";
            } else if(Character.isWhitespace(text.charAt(i))) {
                lastWord = updateList(temp, lastWord);
                temp = "";
                lastWord = updateList("PARAGRAPH_BREAK", lastWord);
                words.add(lastWord);
            } else if(isPunctuation(text.charAt(i))) {
                lastWord = updateList(temp, lastWord);
                lastWord = updateList(text.charAt(i) + "", lastWord);
                temp = "";
            }
        }
        lastWord = updateList(temp, lastWord);
        words.add(lastWord);
        temp = "";
        return words;
    }
    
    public static boolean isPunctuation(char ch) {
        for(char punct : punctuationArray) {
            if(punct == ch) {
                return true;
            }
        }
        return false;
    }
    
    public static char simplifyPunctuation(char ch) {
        switch(ch) {
            case '.': return ch;
            case ',': return ch;
            case '?': return '.';
            case '!': return '.';
            default : return '.';
        }
    }
    
    public static Word updateList(String wordString, Word lastWord) {
        boolean contains = false;
        Word prevWord = Word.getErrorWord();
        for(Word word : words) {
            if(word.getWord().equals(wordString)) {
                word.addWordAfter(lastWord);
                contains = true;
                prevWord = word;
            }
        }
        if(!contains) {
            prevWord = Word.generateNewWord(wordString, lastWord);
            words.add(prevWord);
        }
        return prevWord;
    }
    
    public static void printWordList(List<Word> words) {
        System.out.print("[");
        for(Word word : words) {
            System.out.print(word.getWord() + ", ");
        }
        System.out.println("END]");
    }
}
