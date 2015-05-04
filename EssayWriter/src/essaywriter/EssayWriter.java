/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essaywriter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author atmarsh
 */
public class EssayWriter {

    private static String string = "Hi there, this is a really fucking long sentence and I don't like it. And this sentence is really important too.";
    private static List<Word> words;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            string = Reader.readFile("src/essaywriter/newDoc.txt");
        } catch (IOException ex) {
            Logger.getLogger(EssayWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(string);
        words = Parser.parse(string);
        Parser.printWordList(words);
        WritingCenter.generateEssay(1000);
        System.out.println(WritingCenter.getEssay());
    }
    
    public static List<Word> getWords() {
        return words;
    }
    
}
