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
public class Parser {
    
    public static ArrayList<Word> parse(String text) {
        text = text.toLowerCase();
        String temp = "";
        Word lastWord = null;
        for(int i = text.length() - 1; i >= 0; i--) {
            if(Character.isLetter(text.charAt(i))) {
                temp += text.charAt(i);
            } else if(Character.isSpaceChar(text.charAt(i))) {
                lastWord = Word.generateNewWord(temp, lastWord);
                temp = "";
            } else if(Character.isDigit(text.charAt(i))) {
                
            } else if(Character.isWhitespace(text.charAt(i))) {
                
            }
        }
    }
}
