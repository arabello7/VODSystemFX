/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

/**
 *
 * @author Tomasz Jurek
 */

// Ta klasa powinna być bardziej rozbudowana... Postanowiłem zastąpić jej użyteczność Serialem
public class Episode {

    private final int number; 
    private final int duration;
    
    public Episode(int number, int duration) {
        this.number = number;
        this.duration = duration;
    }
}
