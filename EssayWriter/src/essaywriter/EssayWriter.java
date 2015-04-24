/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essaywriter;

/**
 *
 * @author atmarsh
 */
public class EssayWriter {
    private static List<Word> words;
    private static String string = "Hi there, this is a really fucking long sentence and I don't like it. And this sentence is really important too.";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Parser.printWordList(Parser.parse(string));
    }
    
    public static List<Word> getWords() {
        return words;
    }
}
