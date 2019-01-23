/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author tomas
 */
public abstract class FileWorm {

    public static String readFile(int line, String fileName) {
        BufferedReader reader;
        String text = "";
        try {
            reader = new BufferedReader(new java.io.FileReader("textfiles/" + fileName));
            while (line > 0) {
                reader.readLine();
                line--;
            }
            text = reader.readLine();
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found!");
        } catch (IOException ex) {
            System.out.println("In/Out Exception");
        }
        return text;
    }
}
