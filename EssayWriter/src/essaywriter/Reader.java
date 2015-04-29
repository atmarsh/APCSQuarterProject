package essaywriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author atmarsh
 */
public class Reader {

    private static BufferedReader input;
    private final static Charset ENCODING = StandardCharsets.UTF_8;

    public static String readFile(String fileName) throws FileNotFoundException, IOException {
        input = new BufferedReader(new FileReader(new File(fileName)));
        //Variable to hold the one line data
        String line;
        String result = "";
        // Read file line by line and print on the console
        while ((line = input.readLine()) != null) {
            result += line;
//            System.out.println(line);
        }
//        System.out.println(result);
        input.close();
        return result;
    }
}
