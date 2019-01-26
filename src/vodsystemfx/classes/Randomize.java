/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.util.concurrent.ThreadLocalRandom;

/** Used to avoid code duplication
 *
 * @author Tomasz Jurek
 */
public abstract class Randomize {
    
    /** Generating random integer
     * @param min - bottom range of number
     * @param max - top range
     * @return integer number between min and max
     */
    public static int randomInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
