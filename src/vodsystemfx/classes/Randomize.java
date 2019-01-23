/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author tomas
 */
public abstract class Randomize {
    
    public static int randomInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
