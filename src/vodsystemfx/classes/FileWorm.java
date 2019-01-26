/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/** This class is used to avoid code duplication, because reading from file is often used in application.
 *
 * @author Tomasz Jurek
 */
public abstract class FileWorm {

    /** Method is used for getting data from different files. Useful when generating random names for objects.
     * 
     * @param line means line inside text file
     * @param fileName name of text file
     * @return single line from text file
     */
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
