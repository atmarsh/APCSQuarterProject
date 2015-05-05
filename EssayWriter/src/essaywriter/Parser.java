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
        String temp = "";
        Word lastWord = Word.getErrorWord();
        words = new ArrayList<>();
        for (int i = text.length() - 1; i >= 0; i--) {
            if (Character.isLetterOrDigit(text.charAt(i))) {
                temp = text.charAt(i) + temp;
            } else if (Character.isSpaceChar(text.charAt(i))) {
                if (temp != "") {
                    if (Character.isUpperCase(temp.charAt(0)) && Character.isLetterOrDigit(text.charAt(i - 1))) {
                        lastWord = updateList(temp.toLowerCase(), lastWord, true);
                    } else {
                        lastWord = updateList(temp.toLowerCase(), lastWord, false);
                    }
                }

                temp = "";
            } else if (Character.isWhitespace(text.charAt(i))) {
                if (temp != "") {
                    if (Character.isUpperCase(text.charAt(i + 1)) && Character.isLetterOrDigit(text.charAt(i - 1))) {
                        lastWord = updateList(temp.toLowerCase(), lastWord, true);
                    } else {
                        lastWord = updateList(temp.toLowerCase(), lastWord, false);
                    }
                }
                temp = "";
                lastWord = updateList("PARAGRAPH_BREAK", lastWord);
                words.add(lastWord);
            } else if (isPunctuation(text.charAt(i))) {
                if (temp != "") {
                    if (temp != "") {
                        if (Character.isUpperCase(temp.charAt(0)) && Character.isLetterOrDigit(text.charAt(i - 1))) {
                            lastWord = updateList(temp.toLowerCase(), lastWord, true);
                        } else {
                            lastWord = updateList(temp.toLowerCase(), lastWord, false);
                        }
                    }
                }
                lastWord = updateList(simplifyPunctuation(text.charAt(i)) + "", lastWord, false);
                temp = "";
            }
        }
        lastWord = updateList(temp.toLowerCase(), lastWord);
        temp = "";
        return words;
    }

    public static boolean isPunctuation(char ch) {
        for (char punct : punctuationArray) {
            if (punct == ch) {
                return true;
            }
        }
        return false;
    }

    public static char simplifyPunctuation(char ch) {
        switch (ch) {
            case '.':
                return ch;
            case ',':
                return ch;
            case '?':
                return '.';
            case '!':
                return '.';
            default:
                return '.';
        }
    }

    public static Word updateList(String wordString, Word lastWord) {
        boolean contains = false;
        Word prevWord = Word.getErrorWord();
        for (Word word : words) {
            if (word.getWord().equals(wordString)) {
                word.addWordAfter(lastWord);
                contains = true;
//                System.out.println(prevWord.getWord() + " = " + word.getWord());
                prevWord = word;
            }
        }
        if (!contains) {
            prevWord = Word.generateNewWord(wordString, lastWord);
            words.add(prevWord);
        }
        return prevWord;
    }

    public static Word updateList(String wordString, Word lastWord, boolean properNoun) {
        boolean contains = false;
        Word prevWord = Word.getErrorWord();
        for (Word word : words) {
            if (word.getWord().equals(wordString)) {
                word.setProperNoun(properNoun);
                word.addWordAfter(lastWord);
                contains = true;
//                System.out.println(prevWord.getWord() + " = " + word.getWord());
                prevWord = word;
            }
        }
        if (!contains) {
            prevWord = Word.generateNewWord(wordString, lastWord);
            prevWord.setProperNoun(properNoun);
            words.add(prevWord);
        }
        return prevWord;
    }

    public static void printWordList(List<Word> words) {
        System.out.print("[");
        for (Word word : words) {
            if(word.isProperNoun()) {
                System.out.print(Character.toUpperCase(word.getWord().charAt(0)) + word.getWord().substring(1) + ", ");
            } else {
                System.out.print(word.getWord() + ", ");
            }
        }
        System.out.println("END]");
    }
}
